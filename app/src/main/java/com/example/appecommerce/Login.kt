package com.example.appecommerce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.appecommerce.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import java.util.*
import kotlin.time.Duration.Companion.milliseconds


class Login : AppCompatActivity() {

    private lateinit var mBinding: ActivityLoginBinding
    private lateinit var mActiveFragment: Fragment
    private var mFragmentManager: FragmentManager? = null
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener
    private var mFirebaseAuth: FirebaseAuth? = null
    val callBackManager = CallbackManager.Factory.create()

    private val authResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            Toast.makeText(this, R.string.main_auth_welcome, Toast.LENGTH_SHORT).show()
        } else {
            if (IdpResponse.fromResultIntent(it.data) == null) {
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        setupAuth()
        val loginButton = findViewById<View>(R.id.login_button) as LoginButton

        /*loginButton.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        }
        loginButton.registerCallback(callBackManager, object : FacebookCallback<LoginResult>{
            override fun onCancel() {
                Log.d("letsee: ", "Facebook Cancel")

            }

            override fun onError(error: FacebookException) {
                Log.d("letsee: ", "Facebook error" + error)
            }

            override fun onSuccess(loginResult: LoginResult) {
                Log.d("letsee: ", "Facebook token : " + loginResult.accessToken.token )

            }

        })*/


    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callBackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }*/

    private fun setupAuth() {

        mFirebaseAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener {
            if (it.currentUser == null) {
                authResult.launch(
                    AuthUI.getInstance().createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.GreenTheme)
                        .setAvailableProviders(
                            kotlin.collections.listOf(
                                AuthUI.IdpConfig.EmailBuilder().build(),
                                AuthUI.IdpConfig.GoogleBuilder().build(),
                                AuthUI.IdpConfig.FacebookBuilder().setPermissions(Arrays.asList("public_profile")).build(),
                                AuthUI.IdpConfig.AnonymousBuilder().build()
                            )
                        )
                        .build()
                )
                mFragmentManager = null

            } else {
                MyApplication.currentUser = it.currentUser!!
                it.uid

                val intent = Intent(this,CandyStore::class.java)
                intent.putExtra("Key",it.toString())
                startActivity(intent)
               /* val fragmentProfile = mFragmentManager?.findFragmentByTag(ProfileFragment::class.java.name)
                fragmentProfile?.let {
                  //  (it as FragmentAux).refresh()
                }*/

                if (mFragmentManager == null) {
                    mFragmentManager = supportFragmentManager
                  //  setupBottomNav(mFragmentManager!!)
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        mFirebaseAuth?.addAuthStateListener(mAuthListener)
    }

    override fun onPause() {
        super.onPause()
        mFirebaseAuth?.removeAuthStateListener(mAuthListener)
    }


}
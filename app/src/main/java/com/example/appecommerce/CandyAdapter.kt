package com.example.appecommerce

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.appecommerce.Model.Candy
import com.example.appecommerce.Model.CandyModel
import com.example.appecommerce.databinding.ItemStoreBinding
import org.w3c.dom.Text

class CandyAdapter (private var candyStore: MutableList<Candy>, private var listener: OnClickListener):
    RecyclerView.Adapter<CandyAdapter.ViewHolder>(){

    private lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context

        val view = LayoutInflater.from(mContext).inflate(R.layout.item_store,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = candyStore.get(position)

        with(holder){
            bindig.tvName.text = store.name
            bindig.cbFavorite.isChecked = false
            bindig.tvdescripcion.text = store.description
            bindig.tvprecio.text = store.price

        }
    }

    override fun getItemCount(): Int = candyStore.size

    fun setStores(stores: MutableList<Candy>) {
        this.candyStore = stores
        notifyDataSetChanged()
    }

    inner class ViewHolder (view:View):RecyclerView.ViewHolder(view){
        val bindig = ItemStoreBinding.bind(view)
    }
}
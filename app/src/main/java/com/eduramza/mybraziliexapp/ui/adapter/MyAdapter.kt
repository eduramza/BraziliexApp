package com.eduramza.mybraziliexapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Coins
import kotlinx.android.synthetic.main.item_list_crypto.view.*

class MyAdapter (private val context: Context?,
                 private val list: MutableList<Coins>,
                 private val adapter: MyAdapterListener
): RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(parent.inflate(R.layout.item_list_crypto))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun updateAdapter(items: MutableList<Coins>){
        this.list.clear()
        this.list.addAll(items)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: Coins

        fun bindView(item: Coins){
            this.item = item

            itemView.tv_nick.text = item.order
            itemView.tv_name.text = item.name

            //TODO Add action here
        }
    }

    interface MyAdapterListener{
        fun onClickItem(item: Coins)
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}


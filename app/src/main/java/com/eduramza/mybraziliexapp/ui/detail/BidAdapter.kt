package com.eduramza.mybraziliexapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.ui.inflate
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.item_list_order_history.view.*

class BidAdapter (private val orderList: MutableList<Orderbook.Bid>
): RecyclerView.Adapter<BidAdapter.ViewHolder>(){

    override fun getItemCount() = orderList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_order_history))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(orderList[position])
    }

    fun updateList(listItem: MutableList<Orderbook.Bid>){
        this.orderList.clear()
        this.orderList.addAll(listItem)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: Orderbook.Bid

        fun bindViewHolder(item: Orderbook.Bid){
            this.item = item

            itemView.tv_trd_hist_price.textSize = 16F
            itemView.tv_trd_hist_amount.textSize = 16F
            itemView.tv_trd_hist_total.textSize = 16F

            itemView.tv_trd_hist_price.text = item.price.transformDoubleInBRL()
            itemView.tv_trd_hist_amount.text = item.amount.toString()
            itemView.tv_trd_hist_total.text = item.total?.transformDoubleInBRL() ?: ""

        }
    }
}
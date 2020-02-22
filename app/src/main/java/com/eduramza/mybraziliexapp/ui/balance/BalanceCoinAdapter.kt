package com.eduramza.mybraziliexapp.ui.balance

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.BuyOrders
import com.eduramza.mybraziliexapp.ui.inflate
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.item_list_balance.view.*

class BalanceCoinAdapter(private val orderList: MutableList<BuyOrders>
): RecyclerView.Adapter<BalanceCoinAdapter.ViewHolder>(){

    override fun getItemCount() = orderList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(orderList[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_balance))
    }

    fun updateList(listItem: MutableList<BuyOrders>){
        this.orderList.clear()
        this.orderList.addAll(listItem)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: BuyOrders

        @SuppressLint("SetTextI18n")
        fun bindViewHolder(item: BuyOrders){
            this.item = item

            itemView.tv_trd_balance_coin.text = item.coin
            itemView.tv_trd_balance_amount.text = item.amount.toString()
            itemView.tv_trd_balance_total.text = item.total.transformDoubleInBRL()

        }
    }
}
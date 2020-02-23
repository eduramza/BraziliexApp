package com.eduramza.mybraziliexapp.ui.balance

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.Balance
import com.eduramza.mybraziliexapp.ui.inflate
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.item_list_balance.view.*

class BalanceCoinAdapter(private val orderList: MutableList<Balance>
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

    fun updateList(listItem: MutableList<Balance>){
        this.orderList.clear()
        this.orderList.addAll(listItem)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: Balance

        @SuppressLint("SetTextI18n")
        fun bindViewHolder(item: Balance){
            this.item = item

            itemView.tv_trd_balance_coin.text = item.coin
            itemView.tv_trd_balance_amount.text = item.amount.toString()
            if (item.amount != null) {
                itemView.tv_trd_balance_total.text = (item.amount * item.unit_price).transformDoubleInBRL()
            } else {
                itemView.tv_trd_balance_total.text = "R$ 0,00"
            }

        }
    }
}
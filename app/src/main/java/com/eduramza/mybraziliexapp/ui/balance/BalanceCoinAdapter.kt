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

class BalanceCoinAdapter(private val orderList: MutableList<Balance>,
                         private val listener: BalanceListener
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
            val valueAm: Double = item.amount ?: 0.0
            itemView.tv_trd_balance_amount.text = valueAm.toString()

            if (item.amount != null) {
                val total = item.amount * item.unit_price
                itemView.tv_trd_balance_total.text = total.transformDoubleInBRL()
                listener.incrementBalance(total)
            } else {
                itemView.tv_trd_balance_total.text = "R$ 0,00"
            }

            itemView.linear_trd_hist_content.setOnClickListener {
                listener.onItemClick(item.coin, item.amount)
            }

        }
    }

    interface BalanceListener{
        fun onItemClick(coin: String, amount: Double?)
        fun incrementBalance(value: Double)
    }
}
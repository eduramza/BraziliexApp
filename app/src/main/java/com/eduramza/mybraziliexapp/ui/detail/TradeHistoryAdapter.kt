package com.eduramza.mybraziliexapp.ui.detail

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import com.eduramza.mybraziliexapp.ui.inflate
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.item_list_order_history.view.*

class TradeHistoryAdapter (private val tradeList: MutableList<TradeHistory>,
                           private val context: Context
): RecyclerView.Adapter<TradeHistoryAdapter.ViewHolder>(){

    override fun getItemCount() = tradeList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_list_order_history))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(tradeList[position])
    }

    fun updateList(listItem: MutableList<TradeHistory>){
        this.tradeList.clear()
        this.tradeList.addAll(listItem)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: TradeHistory

        fun bindViewHolder(item: TradeHistory){
            this.item = item

            itemView.tv_trd_hist_price.text = item.price.toDouble().transformDoubleInBRL()
            itemView.tv_trd_hist_amount.text = item.amount
            itemView.tv_trd_hist_total.text = item.total.toDouble().transformDoubleInBRL()

            if (item.type == "sell"){
                setSellStyle(itemView)
            } else {
                setBuyStyle(itemView)
            }
        }

        private fun setSellStyle(itemView: View) {
            itemView.tv_trd_hist_price.setTextColor(
                ContextCompat.getColor(context, R.color.percent_negative)
            )
            itemView.tv_trd_hist_amount.setTextColor(
                ContextCompat.getColor(context, R.color.percent_negative)
            )
            itemView.tv_trd_hist_total.setTextColor(
                ContextCompat.getColor(context, R.color.percent_negative)
            )
        }

        private fun setBuyStyle(itemView: View){
            itemView.tv_trd_hist_price.setTextColor(
                ContextCompat.getColor(context, R.color.percent_positive)
            )
            itemView.tv_trd_hist_amount.setTextColor(
                ContextCompat.getColor(context, R.color.percent_positive)
            )
            itemView.tv_trd_hist_total.setTextColor(
                ContextCompat.getColor(context, R.color.percent_positive)
            )
        }
    }
}
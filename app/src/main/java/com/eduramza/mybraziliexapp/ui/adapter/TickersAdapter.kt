package com.eduramza.mybraziliexapp.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.ui.inflate
import kotlinx.android.synthetic.main.item_list_tickers.view.*

class TickersAdapter (private val tickerList: MutableList<Tickers.TickerUnit>,
                      private val listener: TickerListener
): RecyclerView.Adapter<TickersAdapter.TickerViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TickerViewHolder {
        return TickerViewHolder(parent.inflate(R.layout.item_list_tickers))
    }

    override fun getItemCount() = tickerList.size

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        holder.bindViewHolder(tickerList[position])
    }

    fun updateList(item: MutableList<Tickers.TickerUnit>){
        this.tickerList.clear()
        this.tickerList.addAll(item)
        notifyDataSetChanged()
    }

    inner class TickerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private lateinit var item: Tickers.TickerUnit

        fun bindViewHolder(item: Tickers.TickerUnit){
            this.item = item

            itemView.tv_list_ticker_name.text = item.market
            itemView.tv_list_ticker_last_price.text = item.last.toString()
            itemView.tv_list_ticker_percent_change.text = item.percentChange.toString()

            itemView.content_item_ticker.setOnClickListener {
                listener.onClickItem(item)
            }

        }
    }

    interface TickerListener{
        fun onClickItem(item: Tickers.TickerUnit)
    }
}

fun String.isBrlTicker() = this.substring(this.lastIndexOf("_") + 1) == "brl"

package com.eduramza.mybraziliexapp.ui.detail


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import com.eduramza.mybraziliexapp.ui.adapter.TradeHistoryAdapter
import com.eduramza.mybraziliexapp.ui.listcrypto.CurrenciesViewModel
import com.eduramza.mybraziliexapp.ui.marketForUppercase
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.drawer_view_header.*
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    companion object{
        private lateinit var ticker: Tickers.TickerUnit

        fun newInstance(tickerUnit: Tickers.TickerUnit): DetailFragment {
            this.ticker = tickerUnit
            return DetailFragment()
        }
    }

    private val viewModel: CurrenciesViewModel by viewModel()
    private lateinit var adapter: TradeHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configList()
        showTickerDatas()
        setupViewModel()
    }

    private fun configList(){
        adapter = TradeHistoryAdapter(mutableListOf(), context!!)
        rv_list_orderhistory.layoutManager = LinearLayoutManager(context)
        rv_list_orderhistory.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun showTickerDatas(){
        tv_value_last_price.text = ticker.last!!.transformDoubleInBRL()
        tv_value_quote_vol24h.text = ticker.quoteVolume24!!.transformDoubleInBRL()

        tv_value_base_vol24h.text =
            "${ticker.baseVolume24!!.toString().replace(".", ",")} " +
                    ticker.market!!.marketForUppercase()

        tv_value_highest_bid.text =
            "${ticker.highestBid24!!.transformDoubleInBRL()} (max.)"

        tv_value_lowest_ask.text =
            "${ticker.lowestAsk24!!.transformDoubleInBRL()} (min.)"

        trd_hist_header_amount.text = context!!.getString(R.string.trade_history_header_qtde) +
                " (${ticker.market!!.marketForUppercase()})"
    }

    private fun setupViewModel(){
        viewModel.getMarketTradeHistory(ticker.market!!)

        viewModel.getLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                showLoading()
            } else {
                hideLoading()
            }
        })

        viewModel.getTradeHistoryLiveData().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it as MutableList<TradeHistory>)
        })
    }

    private fun showLoading() { pb_order_history.visibility = VISIBLE }
    private fun hideLoading() {pb_order_history.visibility = GONE}

}

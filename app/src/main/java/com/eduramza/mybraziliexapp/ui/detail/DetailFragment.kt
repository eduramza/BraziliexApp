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
import com.eduramza.mybraziliexapp.data.model.orderbook.Orderbook
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.data.model.tradehistory.TradeHistory
import com.eduramza.mybraziliexapp.ui.CurrenciesViewModel
import com.eduramza.mybraziliexapp.ui.marketForUppercase
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.drawer_view_header_list.*
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
    private lateinit var tradeHistoryAdapter: TradeHistoryAdapter
    private lateinit var askAdapter: AskAdapter
    private lateinit var bidAdapter: BidAdapter

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
        tradeHistoryAdapter =
            TradeHistoryAdapter(
                mutableListOf(),
                context!!
            )
        rv_list_orderhistory.layoutManager = LinearLayoutManager(context)
        rv_list_orderhistory.adapter = tradeHistoryAdapter

        askAdapter =
            AskAdapter(mutableListOf())
        rv_list_sell_orders.layoutManager = LinearLayoutManager(context)
        rv_list_sell_orders.adapter = askAdapter

        bidAdapter =
            BidAdapter(mutableListOf())
        rv_list_buy_orders.layoutManager = LinearLayoutManager(context)
        rv_list_buy_orders.adapter = bidAdapter
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

        setupOrdersBookHeaaders()
    }

    @SuppressLint("SetTextI18n")
    private fun setupOrdersBookHeaaders(){
        tv_header_sell_orders.text = context!!.getString(R.string.title_sell_orders).toUpperCase() +
                " (${ticker.market!!.marketForUppercase()})"
        tv_header_buy_orders.text = context!!.getString(R.string.title_buy_orders).toUpperCase() +
                " (${ticker.market!!.marketForUppercase()})"
    }

    private fun setupViewModel(){
        viewModel.getMarketTradeHistory(ticker.market!!)
        viewModel.getAllOrderbook(ticker.market!!)

        observeLoadings()

        viewModel.getTradeHistoryLiveData().observe(viewLifecycleOwner, Observer {
            tradeHistoryAdapter.updateList(it as MutableList<TradeHistory>)
        })

        viewModel.getAsksData().observe(viewLifecycleOwner, Observer {
            askAdapter.updateList(it as MutableList<Orderbook.Ask>)
        })

        viewModel.getBidsData().observe(viewLifecycleOwner, Observer {
            bidAdapter.updateList(it as MutableList<Orderbook.Bid>)
        })
    }

    private fun observeLoadings(){
        viewModel.getLoadingOrderbook().observe(viewLifecycleOwner, Observer {
            if (it){
                showLoading()
            } else {
                hideLoading()
            }
        })
        viewModel.getLoadingAsk().observe(viewLifecycleOwner, Observer {
            if (it) showAskLoading()  else hideAskLoading()
        })
        viewModel.getLoadingBids().observe(viewLifecycleOwner, Observer {
            if (it) showBidsLoading()  else hideBidsLoading()
        })
    }

    private fun showLoading() { pb_order_history.visibility = VISIBLE }
    private fun hideLoading() {pb_order_history.visibility = GONE}
    private fun showAskLoading() { pb_sell_orders.visibility = VISIBLE }
    private fun hideAskLoading() {pb_sell_orders.visibility = GONE}
    private fun showBidsLoading() { pb_buy_orders.visibility = VISIBLE }
    private fun hideBidsLoading() {pb_buy_orders.visibility = GONE}

}

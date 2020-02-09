package com.eduramza.mybraziliexapp.ui.listcrypto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.tickers.Tickers
import com.eduramza.mybraziliexapp.ui.adapter.TickersAdapter
import kotlinx.android.synthetic.main.list_crypto_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListCryptoFragment : Fragment(), TickersAdapter.TickerListener {

    companion object {
        fun newInstance() = ListCryptoFragment()
    }

    private val viewModel: ListCryptoViewModel by viewModel()
    private lateinit var adapter: TickersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.list_crypto_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configList()
        observerViewModel()
    }

    private fun configList(){
        adapter = TickersAdapter(mutableListOf(), this, context!!)
        rv_list_crypto.layoutManager = LinearLayoutManager(context)
        rv_list_crypto.itemAnimator = DefaultItemAnimator()
        rv_list_crypto.adapter = adapter
    }

    private fun observerViewModel(){
        viewModel.getTickerResponse().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it as MutableList<Tickers.TickerUnit>)
        })
        viewModel.getLoading().observe(viewLifecycleOwner, Observer {
            if (it){
                showLoading()
            } else {
                hideLoading()
            }
        })
    }

    private fun showLoading(){
        pb_list_ticker.visibility = VISIBLE
    }

    private fun hideLoading(){
        pb_list_ticker.visibility = GONE
    }

    override fun onClickItem(item: Tickers.TickerUnit) {
        Toast.makeText(context, "Clicked in ${item.market}", Toast.LENGTH_SHORT).show()
    }
}

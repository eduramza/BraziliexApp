package com.eduramza.mybraziliexapp.ui.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.local.Balance
import kotlinx.android.synthetic.main.balance_fragment.*
import kotlinx.android.synthetic.main.list_crypto_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class BalanceFragment : Fragment() {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private val balanceViewModel: BalanceViewModel by viewModel()
    private lateinit var adapter : BalanceCoinAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.balance_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        configlist()
        setupObservers()
    }

    private fun configlist(){
        adapter = BalanceCoinAdapter(mutableListOf())
        rv_coin_balance.layoutManager = LinearLayoutManager(context)
        rv_coin_balance.itemAnimator = DefaultItemAnimator()
        rv_coin_balance.adapter = adapter
    }

    private fun setupObservers(){
        balanceViewModel.getAllbalance()
        balanceViewModel.getBuyOrdersLiveData().observe(viewLifecycleOwner, Observer {
            adapter.updateList(it.sortedByDescending { it.amount } as MutableList<Balance>)
        })
    }

}

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
import com.eduramza.mybraziliexapp.ui.transformDoubleInBRL
import kotlinx.android.synthetic.main.balance_fragment.*
import kotlinx.android.synthetic.main.list_crypto_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class BalanceFragment : Fragment(), BalanceCoinAdapter.BalanceListener,
    BalanceDialog.BalanceDialogInterface {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private val balanceViewModel: BalanceViewModel by viewModel()
    private lateinit var adapter : BalanceCoinAdapter
    private var myBalance: Double = 0.0

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
        adapter = BalanceCoinAdapter(mutableListOf(), this)
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

    override fun onItemClick(coin: String, amount: Double?) {
        val valueAm: Double = amount ?: 0.0
        val balanceDialog = BalanceDialog.newInstance(coin, valueAm, this@BalanceFragment)
        balanceDialog.show(activity!!.supportFragmentManager, "dialog_balance")

    }

    override fun saveAmount(coin: String, amount: Double) {
        balanceViewModel.updateAmount(coin, amount)
    }

    override fun incrementBalance(value: Double) {
        //Essa logica está errada e deve ser levada para a viewmodel para criação de testes unitários
        myBalance += value
        tv_balance_brl.text = myBalance.transformDoubleInBRL()
    }

}

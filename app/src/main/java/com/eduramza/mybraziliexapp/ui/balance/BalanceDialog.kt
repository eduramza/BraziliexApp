package com.eduramza.mybraziliexapp.ui.balance

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.eduramza.mybraziliexapp.R
import kotlinx.android.synthetic.main.dialog_new_balance_amount.*

@Suppress("UNREACHABLE_CODE")
class BalanceDialog : DialogFragment() {

    companion object{
        lateinit var coin: String
        var amount: Double = 0.0
        lateinit var listener: BalanceDialogInterface

        fun newInstance(coin: String, amount: Double, listener: BalanceDialogInterface): BalanceDialog{
            this.coin = coin
            this.amount = amount
            this.listener = listener
            return BalanceDialog()
        }
    }

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val myView: View = activity!!.layoutInflater.inflate(R.layout.dialog_new_balance_amount, null)
        androidx.appcompat.app.AlertDialog.Builder(activity!!).create()
        return myView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureLayout()
        bt_dialog_cancel.setOnClickListener { dismiss() }

        bt_dialog_save.setOnClickListener {
            listener.saveAmount(tv_dialog_coin_balance.text.toString(),
                ti_amount.text.toString().toDouble())
            dismiss()
        }
    }

    private fun configureLayout(){
        val qtde: String = amount.toString()
        tv_dialog_coin_balance.text = coin
        ti_amount.hint = qtde
    }

    interface BalanceDialogInterface{
        fun saveAmount(coin: String, amount: Double)
    }

}
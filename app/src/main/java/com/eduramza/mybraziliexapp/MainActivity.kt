package com.eduramza.mybraziliexapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.eduramza.mybraziliexapp.app.constants.FragmentTags.Companion.DETAIL
import com.eduramza.mybraziliexapp.app.constants.FragmentTags.Companion.LIST
import com.eduramza.mybraziliexapp.ui.balance.BalanceActivity
import com.eduramza.mybraziliexapp.ui.listcrypto.ListCryptoFragment
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListCryptoFragment.newInstance(), LIST)
                .commitNow()
        }

        ic_back.setOnClickListener { backTo() }
        ic_balance.setOnClickListener { sendToBalance() }
    }

    private fun backTo(){
        supportFragmentManager.findFragmentByTag(DETAIL)?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListCryptoFragment.newInstance(), LIST)
                .commitNow()
        }
    }

    private fun sendToBalance(){
        val intent = Intent(this, BalanceActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() { backTo() }

}

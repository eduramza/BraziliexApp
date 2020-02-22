package com.eduramza.mybraziliexapp.ui.balance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eduramza.mybraziliexapp.R


class BalanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.balance_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BalanceFragment.newInstance())
                .commitNow()
        }
    }



}

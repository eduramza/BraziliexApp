package com.eduramza.mybraziliexapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eduramza.mybraziliexapp.ui.listcrypto.ListCryptoFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListCryptoFragment.newInstance())
                .commitNow()
        }
    }

}

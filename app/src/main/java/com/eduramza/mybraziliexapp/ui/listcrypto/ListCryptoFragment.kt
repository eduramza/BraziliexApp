package com.eduramza.mybraziliexapp.ui.listcrypto

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.eduramza.mybraziliexapp.R
import com.eduramza.mybraziliexapp.data.model.Coins
import com.eduramza.mybraziliexapp.ui.adapter.MyAdapter
import kotlinx.android.synthetic.main.list_crypto_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListCryptoFragment : Fragment(), MyAdapter.MyAdapterListener {

    companion object {
        fun newInstance() = ListCryptoFragment()
    }

    private val viewModel: ListCryptoViewModel by viewModel()
    private lateinit var adapter: MyAdapter

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
        adapter = MyAdapter(context, mutableListOf(), this)
        rv_list_crypto.layoutManager = LinearLayoutManager(context)
        rv_list_crypto.itemAnimator = DefaultItemAnimator()
        rv_list_crypto.adapter = adapter
    }

    private fun observerViewModel(){
        viewModel.getResponse().observe(viewLifecycleOwner, Observer {
            adapter.updateAdapter(it as MutableList<Coins>)
        })
    }

    override fun onClickItem(item: Coins) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

package com.eduramza.mybraziliexapp.ui.privater

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.eduramza.mybraziliexapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class PrivateFragment : Fragment() {

    companion object {
        fun newInstance() = PrivateFragment()
    }

    private val viewModel: PrivateViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.private_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        callViewmodel()
    }

    private fun callViewmodel(){
        viewModel.callBalance()
    }

}

package com.alex.carexpenses4.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.alex.carexpenses4.R
import com.alex.carexpenses4.databinding.FragmentInfoBinding
import com.alex.carexpenses4.ui.factory

class InfoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoFragment()
    }
    private lateinit var binding: FragmentInfoBinding
    private val viewModel: InfoViewModel by viewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InfoViewModel::class.java)
    }

}
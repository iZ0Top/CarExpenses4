package com.alex.carexpenses4.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alex.carexpenses4.App
import com.alex.carexpenses4.ui.list.ListViewModel
import java.lang.IllegalStateException

class ViewModelFactory(private val app: App): ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when(modelClass){
            ListViewModel::class.java -> {
                 ListViewModel(app.expenseService)
            }
            else -> {
                throw IllegalStateException("Unknown ViewModelClas")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)
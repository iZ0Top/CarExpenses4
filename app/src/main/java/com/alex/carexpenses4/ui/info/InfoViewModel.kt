package com.alex.carexpenses4.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.carexpenses4.model.Expense

class InfoViewModel : ViewModel() {

    private val _expense = MutableLiveData<Expense>()
    val expense = _expense


    fun setExpense(expense: Expense){
        _expense.postValue(expense)
    }


}
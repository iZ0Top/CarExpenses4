package com.alex.carexpenses4.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.model.ExpenseService
import com.alex.carexpenses4.model.MyListeners
import java.util.Collections

class ListViewModel (private val expenseService: ExpenseService) : ViewModel() {

    private val _listLD = MutableLiveData<List<Expense>>()
    val listLD = _listLD

    private var list = mutableListOf<Expense>()

    fun getExpensesList(): List<Expense>{
        return listLD.value!!
    }

    init {
        loadExpenses()
    }

    fun loadExpenses(){
        expenseService.addListeners(listener)
    }

    fun deleteExpense(expense: Expense){
        expenseService.deleteExpense(expense)
    }

    fun moveExpense(expense: Expense, moveBy: Int){
        expenseService.moveExpense(expense, moveBy)
    }

    private val listener: MyListeners = {
        _listLD.value = it
    }

    override fun onCleared() {
        super.onCleared()
        expenseService.removeListeners(listener)
    }
}
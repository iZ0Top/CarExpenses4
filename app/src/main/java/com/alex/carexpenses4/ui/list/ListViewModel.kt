package com.alex.carexpenses4.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.model.ExpenseService
import java.util.Collections

class ListViewModel (private val expenseService: ExpenseService) : ViewModel() {

    private val _listLD = MutableLiveData<List<Expense>>()
    val listLD = _listLD

    private var list = mutableListOf<Expense>()

    fun getExpensesList(): List<Expense>{
        return listLD.value!!
    }

    fun loadExpenses(){

    }


    fun deleteExpense(expense: Expense){
        expenseService.deleteExpense(expense)
    }

    fun moveExpense(expense: Expense, moveBy: Int){
        expenseService.moveExpense(expense, moveBy)
    }
}
package com.alex.carexpenses4.ui.info

import android.content.res.Resources.NotFoundException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.model.ExpenseDetails
import com.alex.carexpenses4.model.ExpenseService

class InfoViewModel(private val expenseService: ExpenseService) : ViewModel() {

    private val _expenseDetails = MutableLiveData<ExpenseDetails>()
    val expenseDetails = _expenseDetails


    fun loadExpense(expenseId: Int){
        if (_expenseDetails.value != null) return
        try {
            _expenseDetails.value = expenseService.getExpenseById(expenseId)
        }
        catch (e: NotFoundException) {
            e.printStackTrace()
        }

    }

    fun deleteExpense(){
        val expenseDetails = this.expenseDetails.value ?: return
        expenseService.deleteExpense(expenseDetails.expense)

    }


}
package com.alex.carexpenses4.model

import java.util.Collections


typealias MyListeners = (expenses: List<Expense>) -> Unit

class ExpenseService {
    private var expensesList = mutableListOf<Expense>()
    private var listeners = mutableSetOf<MyListeners>()




    list.add(Expense(0, 1,100000,"01.01.0001", "Detail one", "Description detail one", "PARTNUM123456789-1", 1, 55.0 ))
    list.add(Expense(1, 1,100500,"02.01.0001", "Detail two", "Description detail two", "PARTNUM123456789-2", 2, 350.0 ))
    list.add(Expense(2, 1,100750,"03.01.0001", "Detail three", "Description detail three", "PARTNUM123456789-3", 1, 89.0 ))
    list.add(Expense(3, 1,101000,"04.01.0001", "Detail four", "Description detail four", "PARTNUM123456789-4", 1, 750.0 ))
    list.add(Expense(4, 1,102000,"05.01.0001", "Detail five", "Description detail five", "PARTNUM123456789-5", 4, 100.0 ))
    list.add(Expense(5, 1,103000,"06.01.0001", "Detail six", "Description detail six", "PARTNUM123456789-6", 1, 55.0 ))
    list.add(Expense(6, 1,104500,"07.01.0001", "Detail seven", "Description detail seven", "PARTNUM123456789-7", 2, 350.0 ))
    list.add(Expense(7, 1,106750,"08.01.0001", "Detail eight", "Description detail eight", "PARTNUM123456789-8", 1, 89.0 ))
    list.add(Expense(8, 1,108000,"09.01.0001", "Detail nine", "Description detail nine", "PARTNUM123456789-9", 1, 750.0 ))
    list.add(Expense(9, 1,109200,"10.01.0001", "Detail ten", "Description detail ten", "PARTNUM123456789-10", 4, 100.0 ))



    fun moveExpense(expense: Expense, moveBy: Int){
        val oldIndex = expensesList.indexOfFirst { it.id == expense.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex > expensesList.size) return
        val listNew = ArrayList(expensesList)
        Collections.swap(expensesList, oldIndex, newIndex)
    }

    fun deleteExpense(expense: Expense){
        val indexToDelete = expensesList.indexOfFirst { it.id == expense.id }
        if (indexToDelete != -1){
            val listNew = ArrayList(expensesList)
            listNew.removeAt(indexToDelete)
        }
    }


}
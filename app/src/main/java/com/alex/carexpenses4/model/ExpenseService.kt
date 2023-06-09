package com.alex.carexpenses4.model


typealias MyListeners = (expenses: List<Expense>) -> Unit

class ExpenseService {

    private var expensesList = mutableListOf<Expense>()
    private var listeners = mutableSetOf<MyListeners>()
}
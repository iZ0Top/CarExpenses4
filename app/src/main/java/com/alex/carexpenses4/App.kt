package com.alex.carexpenses4

import android.app.Application
import com.alex.carexpenses4.model.ExpenseService

class App : Application() {

    val expenseService = ExpenseService()
}
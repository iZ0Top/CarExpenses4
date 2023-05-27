package com.alex.carexpenses4.model


data class Expense(
    val id: Int,
    val parentId: Int,
    val date: String,
    val name: String,
    val description: String,
    val originalPartNum: String,
    val quantity: Int,
    val prise: Double
)

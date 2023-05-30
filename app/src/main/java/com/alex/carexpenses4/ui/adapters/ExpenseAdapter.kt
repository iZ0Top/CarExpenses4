package com.alex.carexpenses4.ui.adapters

import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses4.R
import com.alex.carexpenses4.databinding.ItemExpenseBinding
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.utils.APP_ACTIVITY



class ExpenseAdapter(
    private val userActionListener: UserActionListener
    ): RecyclerView.Adapter<ExpenseAdapter.MyHolder>(), View.OnClickListener {

    var listExpenses = emptyList<Expense>()
        set(value) {
            val diffCallback = MDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback, true)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemCount(): Int = listExpenses.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemExpenseBinding.inflate(layoutInflater, parent, false)

        binding.itemRoot.setOnClickListener(this)
        binding.itemIcInfo.setOnClickListener(this)

        return MyHolder(binding)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val expense = listExpenses[position]
        with(holder.binding){
            holder.itemView.tag = expense
            itemIcInfo.tag = expense
            itemDate.text = expense.date
            itemOdometer.text = expense.odometer.toString()
            itemSum.text = APP_ACTIVITY.getString(R.string.sum_template, (expense.quantity * expense.prise))
            itemName.text = expense.name
            itemDescription.text = expense.description
            itemPartnum.text = expense.originalPartNum
            itemQuantity.text = APP_ACTIVITY.getString(R.string.quantity_template, expense.quantity)
            itemPrice.text = APP_ACTIVITY.getString(R.string.price_template, expense.prise)
        }
    }

    override fun onClick(v: View) {
        val expense = v.tag as Expense
        when(v.id){
            R.id.item_ic_info -> {
                showPopupMenu(v)
            }
            else -> {
                userActionListener.onDetail(expense)
            }
        }
    }

    private fun showPopupMenu(view: View){
        val expense = view.tag as Expense
        val position = listExpenses.indexOfFirst { it.id == expense.id }
        val popUpMenu = PopupMenu(view.context, view)
        popUpMenu.menu.add(0, ID_MOVE_UP, Menu.NONE, APP_ACTIVITY.getString(R.string.text_move_up)).apply {
            isEnabled = position > 0
        }
        popUpMenu.menu.add(0, ID_MOVE_DOWN, Menu.NONE, APP_ACTIVITY.getString(R.string.text_move_down)).apply {
            isEnabled = position < listExpenses.size -1
        }
        popUpMenu.menu.add(0, ID_DELETE, Menu.NONE, APP_ACTIVITY.getString(R.string.text_delete))
        popUpMenu.setOnMenuItemClickListener {
            when(it.itemId){
                ID_MOVE_UP -> {
                    userActionListener.onMove(expense, -1)
                }
                ID_MOVE_DOWN -> {
                    userActionListener.onMove(expense, 1)
                }
                ID_DELETE -> {
                    userActionListener.onDelete(expense)
                }
            }
            return@setOnMenuItemClickListener true
        }
        popUpMenu.show()
    }

    class MyHolder(var binding: ItemExpenseBinding): RecyclerView.ViewHolder(binding.root)

    companion object{
        private const val ID_MOVE_UP = 1
        private const val ID_MOVE_DOWN = 2
        private const val ID_DELETE = 3
    }
}

class MDiffCallback(private val listOld: List<Expense>, private val listNew: List<Expense>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = listOld.size
    override fun getNewListSize(): Int = listNew.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldExpense = listOld[oldItemPosition]
        val newExpense = listNew[newItemPosition]
        return oldExpense.id == newExpense.id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldExpense = listOld[oldItemPosition]
        val newExpense = listNew[newItemPosition]
        return oldExpense == newExpense
    }
}

interface UserActionListener{
    fun onMove(expense: Expense, moveBy: Int)
    fun onDelete(expense: Expense)
    fun onDetail(expense: Expense)
}

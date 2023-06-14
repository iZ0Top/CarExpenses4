package com.alex.carexpenses4.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses4.databinding.FragmentListBinding
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.ui.adapters.ExpenseAdapter
import com.alex.carexpenses4.ui.adapters.UserActionListener
import com.alex.carexpenses4.ui.factory
import com.alex.carexpenses4.utils.APP_ACTIVITY

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListViewModel by viewModels{ factory() }
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false )
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        mAdapter = ExpenseAdapter(object : UserActionListener {
            override fun onMove(expense: Expense, moveBy: Int) {
                viewModel.moveExpense(expense, moveBy)
            }
            override fun onDelete(expense: Expense) {
                viewModel.deleteExpense(expense)
            }
            override fun onDetail(expense: Expense) {
                Toast.makeText(APP_ACTIVITY, "selected expense id: ${expense.id}, name: ${expense.name}", Toast.LENGTH_SHORT).show()
            }
        })
        mRecyclerView = binding.listRecyclerView
        mRecyclerView.adapter = mAdapter

        viewModel.listLD.observe(viewLifecycleOwner, Observer {
            mAdapter.listExpenses = it
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
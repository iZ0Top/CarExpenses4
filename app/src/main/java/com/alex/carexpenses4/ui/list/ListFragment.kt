package com.alex.carexpenses4.ui.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.alex.carexpenses4.R
import com.alex.carexpenses4.databinding.FragmentListBinding
import com.alex.carexpenses4.model.Expense
import com.alex.carexpenses4.ui.adapters.ExpenseAdapter
import com.alex.carexpenses4.ui.adapters.UserActionListener
import com.alex.carexpenses4.utils.APP_ACTIVITY

class ListFragment : Fragment() {



    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var expenseListObserver: Observer<List<Expense>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
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

        val mAdapter = ExpenseAdapter(object : UserActionListener {
            override fun onMove(expense: Expense, moveBy: Int) {
                TODO("Not yet implemented")
            }

            override fun onDelete(expense: Expense) {

            }

            override fun onDetail(expense: Expense) {
                Toast.makeText(APP_ACTIVITY, "selected expense id: ${expense.id}, name: ${expense.name}", Toast.LENGTH_SHORT).show()
            }

        })
        mRecyclerView = binding.listRecyclerView
        mRecyclerView.adapter = mAdapter

        expenseListObserver = Observer {
            if (it.isNotEmpty()) binding.listProgressBar.visibility = View.INVISIBLE
            mAdapter.listExpenses = it
        }


        initObservers()
    }

    private fun initObservers() {
        viewModel.listLD.observe(viewLifecycleOwner, expenseListObserver)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        viewModel.listLD.removeObserver(expenseListObserver)
    }

}
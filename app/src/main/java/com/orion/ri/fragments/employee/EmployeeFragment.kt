package com.orion.ri.fragments.task

import DataStoreHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.orion.ri.activities.employee.AddEmployeeActivity
import com.orion.ri.activities.employee.EmployeeDetailsActivity
import com.orion.ri.api.APIRepository
import com.orion.ri.databinding.FragmentEmployeeBinding
import com.orion.ri.fragments.employee.EmployeeClickedListener
import com.orion.ri.fragments.employee.EmployeesAdapter
import com.orion.ri.model.response.EmployeesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeFragment : Fragment() {
    private lateinit var binding : FragmentEmployeeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        init()
    }
    private fun init() {

        setupAdapter()
        binding.toolbar.heading.text = "Employees List"
        binding.toolbar.add.visibility = View.VISIBLE

        binding.toolbar.add.setOnClickListener{
            AddEmployeeActivity.launchActivity(requireActivity())
        }


    }

    private fun setupAdapter() {
        CoroutineScope(Dispatchers.IO).launch {
           val employees = DataStoreHelper.getInstance().getAllUsers()
            withContext(Dispatchers.Main)
            {
                setData(employees)
            }
        }
    }
fun setData(employees: List<EmployeesResponse>)
{
    val adapter = EmployeesAdapter(context,employees, object :EmployeeClickedListener{
        override fun clickEmployee(employee: EmployeesResponse) {
            EmployeeDetailsActivity.launchActivity(requireActivity(), employee)
        }
    })
    binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
    binding.rvEmployees.adapter = adapter
}
    companion object {


    }
}
package com.orion.ri.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.orion.ri.activities.employee.AddEmployeeActivity
import com.orion.ri.activities.employee.EmployeeDetailsActivity
import com.orion.ri.databinding.FragmentEmployeeBinding
import com.orion.ri.fragments.employee.EmployeeClickedListener
import com.orion.ri.fragments.employee.EmployeesAdapter
import com.orion.ri.model.employee.EmployeeDataClass

class EmployeeFragment : Fragment() {
    private lateinit var binding : FragmentEmployeeBinding

    val employees = listOf(
        EmployeeDataClass(name = "John Doe", id =  101, designation =  "Software Engineer", contactNumber="+1234567890"),
        EmployeeDataClass(name = "Jane Smith", id =  102, designation =  "UI/UX Designer", contactNumber="+1987654321"),
        EmployeeDataClass(name = "Michael Johnson", id =  103, designation =  "Project Manager", contactNumber="+1765432109"),
        EmployeeDataClass(name = "Emily Brown", id =  104, designation =  "HR Manager", contactNumber="+1456789023"),
        EmployeeDataClass(name = "David Lee", id =  105, designation =  "Senior Developer", contactNumber="+1654321890"),
        EmployeeDataClass(name = "Sarah Wilson", id =  106, designation =  "Marketing Specialist", contactNumber="+1890765432"),
        EmployeeDataClass(name = "Alex Turner", id =  107, designation =  "Business Analyst", contactNumber="+1345678901"),
        EmployeeDataClass(name = "Olivia Parker", id =  108, designation =  "Data Scientist", contactNumber="+1567890123"),
        EmployeeDataClass(name = "Daniel Martinez", id =  109, designation =  "Product Manager", contactNumber="+1876543210"),
        EmployeeDataClass(name = "Sophia White", id =  110, designation =  "QA Engineer",contactNumber= "+1987654321")
    )

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

    private fun init() {
        setupAdapter()
        binding.toolbar.heading.text = "Employees List"
        binding.toolbar.add.visibility = View.VISIBLE

        binding.toolbar.add.setOnClickListener{
            AddEmployeeActivity.launchActivity(requireActivity())
        }


    }

    private fun setupAdapter() {
        val adapter = EmployeesAdapter(context,employees, object :EmployeeClickedListener{
            override fun clickEmployee(employee: EmployeeDataClass) {
                EmployeeDetailsActivity.launchActivity(requireActivity(),employee)
            }

        })
        binding.rvEmployees.layoutManager = LinearLayoutManager(requireContext())
        binding.rvEmployees.adapter = adapter

    }

    companion object {


    }
}
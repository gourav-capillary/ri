package com.orion.ri.fragments.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemEmployeeBinding
import com.orion.ri.model.employee.EmployeeDataClass

class EmployeesAdapter(
    context: Context?,
    private val employeesList: List<EmployeeDataClass>,
    private val clickListener: EmployeeClickedListener
) : RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val itemBinding =
            ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeesViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return employeesList.size
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        val card: EmployeeDataClass = employeesList[position]
        holder.bind(card, clickListener)
    }

    class EmployeesViewHolder(private val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeeDataClass, clickListener: EmployeeClickedListener) {

            binding.root.setOnClickListener {
                clickListener.clickEmployee(employee)
            }
            binding.empName.text = employee.name
            binding.empDesignation.text = employee.designation
            binding.empContactNumber.text = employee.contactNumber
            binding.empID.text = employee.id.toString()

        }

    }

}

interface EmployeeClickedListener {
    fun clickEmployee(ongoingProject: EmployeeDataClass)
}


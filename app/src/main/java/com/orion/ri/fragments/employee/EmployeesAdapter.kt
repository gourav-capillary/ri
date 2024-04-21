package com.orion.ri.fragments.employee

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.R
import com.orion.ri.databinding.ItemOngoingProjectsBinding
import com.orion.ri.model.response.EmployeesResponse

class EmployeesAdapter(
    context: Context?,
    private val employeesList: List<EmployeesResponse>,
    private val clickListener: EmployeeClickedListener
) : RecyclerView.Adapter<EmployeesAdapter.EmployeesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesViewHolder {
        val itemBinding =
            ItemOngoingProjectsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeesViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return employeesList.size
    }

    override fun onBindViewHolder(holder: EmployeesViewHolder, position: Int) {
        val card: EmployeesResponse = employeesList[position]
        holder.bind(card, clickListener)
    }

    class EmployeesViewHolder(private val binding: ItemOngoingProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeesResponse, clickListener: EmployeeClickedListener) {

            binding.root.setOnClickListener {
                clickListener.clickEmployee(employee)
            }
            binding.tvTitle.text = employee.name
            binding.tvSubtitle.text = employee.designation
            binding.tvSubtitle.visibility = View.VISIBLE
            binding.ivIcon.setImageResource(R.drawable.ic_employee_circular)
//            binding.empName.text = employee.name
//            binding.empDesignation.text = employee.designation
//            binding.empContactNumber.text = employee.contactNumber
//            binding.empID.text = employee.id.toString()

        }

    }

}

interface EmployeeClickedListener {
    fun clickEmployee(ongoingProject: EmployeesResponse)
}


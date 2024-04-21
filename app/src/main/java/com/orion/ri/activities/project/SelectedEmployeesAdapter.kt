package com.orion.ri.activities.project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.R
import com.orion.ri.databinding.ItemOngoingProjectsBinding
import com.orion.ri.databinding.ItemSelectedEmployeeForProjectBinding
import com.orion.ri.fragments.employee.EmployeeClickedListener
import com.orion.ri.fragments.employee.EmployeesAdapter
import com.orion.ri.model.response.EmployeesResponse

class SelectedEmployeesAdapter(private var employeesList: List<EmployeesResponse>): RecyclerView.Adapter<SelectedEmployeesAdapter.SelectedEmployeesViewHolder>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectedEmployeesViewHolder {
        val itemBinding = ItemSelectedEmployeeForProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SelectedEmployeesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SelectedEmployeesViewHolder, position: Int) {
        holder.bind(employeesList.get(position))
    }

    override fun getItemCount(): Int {
        return employeesList.size
    }

    fun updateArray(selectedItems: List<EmployeesResponse>) {
        employeesList = selectedItems
        notifyDataSetChanged()
    }

    class SelectedEmployeesViewHolder(private val binding: ItemSelectedEmployeeForProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employee: EmployeesResponse) {
            binding.tvName.text = employee.name
        }

    }
}
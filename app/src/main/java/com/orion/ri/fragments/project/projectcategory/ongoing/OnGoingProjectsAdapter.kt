package com.orion.ri.fragments.project.projectcategory.ongoing

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemOngoingProjectsBinding
import com.orion.ri.model.project.OnGoingProjectData

class OnGoingProjectsAdapter(
    context: Context?,
    private val ongoingProjectsList: Array<OnGoingProjectData>,
    val clickListener: ProjectClickedListener,
) : RecyclerView.Adapter<OnGoingProjectsAdapter.OnGoingProjectsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnGoingProjectsViewHolder {
        val itemBinding =
            ItemOngoingProjectsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OnGoingProjectsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return ongoingProjectsList.size
    }

    override fun onBindViewHolder(holder: OnGoingProjectsViewHolder, position: Int) {

        holder.bind(ongoingProjectsList[position],clickListener)
    }


    class OnGoingProjectsViewHolder(private val binding: ItemOngoingProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(project: OnGoingProjectData, clickListener: ProjectClickedListener) {

            binding.root.setOnClickListener{
                clickListener.clickProject(project)
            }
            binding.projectID.text = project.projectId
            binding.projectName.text = project.projectName
            binding.projectClient.text = project.clientName
            binding.projectDeadline.text = project.deadline
            binding.projectDescription.text = project.description
        }

    }

}

interface ProjectClickedListener {
    fun clickProject(ongoingProject: OnGoingProjectData)
}

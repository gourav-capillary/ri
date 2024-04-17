package com.orion.ri.fragments.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemTaskBannerBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.task.TaskItem

class TasksAdapter(
    private val tasksList: List<TaskItem>,
    val clickListener: TaskClickedListener
) : RecyclerView.Adapter<TasksAdapter.TasksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val itemBinding =
            ItemTaskBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return tasksList.size
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val card: TaskItem = tasksList[position]
        holder.bind(card, position,clickListener)
    }

    class TasksViewHolder(private val binding: ItemTaskBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskItem, position: Int, clickListener: TaskClickedListener) {

            binding.root.setOnClickListener{
                clickListener.clickTask(task)
            }

            binding.container.setBackgroundResource(Utils.getBackgroundByPosition(position))
            binding.taskName.text = task.name
            binding.taskDescription.text = task.description
            binding.progressBarHorizontal.progress = 50
            binding.tvProgress.text = "${task.progress}%"
            binding.taskDeadline.text = task.deadline

        }


    }

}

interface TaskClickedListener {
    fun clickTask(task: TaskItem)
}


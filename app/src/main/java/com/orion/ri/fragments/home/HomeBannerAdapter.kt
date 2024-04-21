package com.orion.ri.fragments.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.activities.task.TaskDetailsActivity
import com.orion.ri.databinding.ItemHomeBannerBinding
import com.orion.ri.model.response.TaskResponse

class HomeBannerAdapter(val activity: FragmentActivity, private val bannerList: List<TaskResponse>):RecyclerView.Adapter<HomeBannerAdapter.BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(itemBinding)
    }



    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val card: TaskResponse = bannerList[position]
        holder.bind(card,activity)
    }

    class BannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskResponse, activity: FragmentActivity) {
            binding.bannerTitle.text = task.name
            binding.bannerSubtitle.text = task.description

            binding.btnOpenTask.setOnClickListener {
                TaskDetailsActivity.launchActivity(activity,task)
            }
        }

    }

}

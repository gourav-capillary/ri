package com.orion.ri.fragments.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.R
import com.orion.ri.databinding.ItemProfileBinding
import com.orion.ri.model.employee.EmployeeDataClass
import com.orion.ri.model.profile.ProfileData

class ProfileAdapter(
    private val profileItemList: List<ProfileData>,
    private val clickListener: ProfileItemClickListener
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemBinding =
            ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(itemBinding)
    }


    override fun getItemCount(): Int {
        return profileItemList.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profileData: ProfileData = profileItemList[position]
        holder.bind(profileData, clickListener)
    }

    class ProfileViewHolder(private val binding: ItemProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profileData: ProfileData, clickListener: ProfileItemClickListener) {
            binding.root.setOnClickListener {
                clickListener.clickProfileItem(profileData)
            }
            binding.tvTitle.text = profileData.title
            profileData.icon?.let { binding.ivIcon.setImageResource(it) }

        }

    }

}

interface ProfileItemClickListener {
    fun clickProfileItem(profileData: ProfileData)
}


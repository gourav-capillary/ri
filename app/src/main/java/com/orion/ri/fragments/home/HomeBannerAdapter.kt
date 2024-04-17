package com.orion.ri.fragments.home

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemHomeBannerBinding
import com.orion.ri.model.home.HomeBanner

class HomeBannerAdapter(private val bannerList: List<HomeBanner>):RecyclerView.Adapter<HomeBannerAdapter.BannerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val itemBinding = ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(itemBinding)
    }



    override fun getItemCount(): Int {
        return bannerList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val card: HomeBanner = bannerList[position]
        holder.bind(card)
    }

    class BannerViewHolder(private val binding: ItemHomeBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(banner: HomeBanner) {
            binding.bannerTitle.text = banner.title
            binding.bannerSubtitle.text = banner.subtitle
        }

    }

}

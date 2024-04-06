package com.orion.ri.fragments.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemHomeGridBinding
import com.orion.ri.model.HomeCard

class HomeCardsAdapter(context: Context?, private val cardsList: List<HomeCard>):RecyclerView.Adapter<HomeCardsAdapter.CardsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemBinding = ItemHomeGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardsViewHolder(itemBinding)
    }



    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card: HomeCard = cardsList[position]
        holder.bind(card)
    }

    class CardsViewHolder(private val binding: ItemHomeGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: HomeCard) {
            binding.cardText.text = card.text
            binding.endIcon.setImageResource(card.drawable)
        }

    }

}

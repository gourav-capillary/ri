package com.orion.ri.fragments.home

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.databinding.ItemHomeGridBinding
import com.orion.ri.model.home.HomeCard

class HomeCardsAdapter(private val cardsList: List<HomeCard>,val clickListener: HomeCardClickListener):RecyclerView.Adapter<HomeCardsAdapter.CardsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder {
        val itemBinding = ItemHomeGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardsViewHolder(itemBinding)
    }



    override fun getItemCount(): Int {
        return cardsList.size
    }

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val card: HomeCard = cardsList[position]
        holder.bind(card,clickListener)
    }

    class CardsViewHolder(private val binding: ItemHomeGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(card: HomeCard, clickListener: HomeCardClickListener) {

            binding.root.setOnClickListener {
                clickListener.onHomeCardClicked()
            }
            binding.cardText.text = card.text
            binding.endIcon.setImageResource(card.drawable)
            binding.container.backgroundTintList = ColorStateList.valueOf (Color.parseColor(card.backgroundColor))
        }

    }

}

interface HomeCardClickListener{
    fun onHomeCardClicked()
}
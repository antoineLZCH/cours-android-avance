package com.antoinelzch.kg.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antoinelzch.kg.R
import com.antoinelzch.kg.models.Game
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.layout_game.view.*


class GamesAdapter(private val games: List<Game>): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_game, parent, false)
        )
    }

    override fun getItemCount() = games.size

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]

        holder.view.textGameName.text = game.name
        Glide.with(holder.view.context)
            .load(game.img)
            .into(holder.view.imageGameImage)
    }


    class GameViewHolder(val view: View): RecyclerView.ViewHolder(view)
}
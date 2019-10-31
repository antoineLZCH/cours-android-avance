package com.antoinelzch.kg.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.antoinelzch.kg.R
import com.antoinelzch.kg.models.Game
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment(game: Game) : Fragment() {

    private val game = game

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        textGameName.text = game.name
        textGameDescription.text = game.description
        Glide.with(activity).load(game.img)
            .into(imageDetailedImage)
    }

}

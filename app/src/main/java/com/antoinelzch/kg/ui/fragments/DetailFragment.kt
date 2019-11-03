package com.antoinelzch.kg.ui.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView

import com.antoinelzch.kg.R
import com.antoinelzch.kg.models.Game
import com.antoinelzch.kg.ui.WebViewActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.textGameName
import kotlinx.android.synthetic.main.layout_game.*

class DetailFragment(game: Game) : Fragment() {

    private val game = game
    private var isWV: Boolean? = null

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val preference = this.activity!!.getSharedPreferences("navigator", Context.MODE_PRIVATE)
        this.isWV = preference.getBoolean("value", false)

        gameButton.setOnClickListener{
            this.clickForMore()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    fun clickForMore() {
        if(this.isWV!!) {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(game.link)
            )
            startActivity(browserIntent)
        } else {
            val browserIntent = Intent(
                this.context,
                WebViewActivity::class.java
            )
            browserIntent.putExtra("url", Uri.parse(game.link).toString())

            startActivity(browserIntent)
        }

        this.isWV = !this.isWV!!

        val preference = this.activity!!.getSharedPreferences("navigator", Context.MODE_PRIVATE);
        preference.edit().putBoolean("value",!this.isWV!!).apply();
    }

}

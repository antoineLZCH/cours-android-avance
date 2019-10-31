package com.antoinelzch.kg.ui.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.antoinelzch.kg.R
import com.antoinelzch.kg.models.Game
import com.antoinelzch.kg.network.GamesApi
import com.antoinelzch.kg.ui.GamesAdapter
import kotlinx.android.synthetic.main.games_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesFragment : Fragment() {

    companion object {
        fun newInstance() = GamesFragment()
    }

    private lateinit var viewModel: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GamesViewModel::class.java)


        refreshLayout.setOnRefreshListener{
            fetchGames()
        }

        fetchGames()
    }

    private fun fetchGames() {
        refreshLayout.isRefreshing = true
        GamesApi().getGames().enqueue(object: Callback<List<Game>> {
            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(activity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                refreshLayout.isRefreshing = false
                val games = response.body()

                games?.let {
                    showGames(it)
                }
            }

        })
    }

    private fun showGames(games: List<Game>) {
        recyclerViewGames.layoutManager = LinearLayoutManager(activity)
        recyclerViewGames.adapter = GamesAdapter(games)
    }

}

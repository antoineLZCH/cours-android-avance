package com.antoinelzch.kg.network

import com.antoinelzch.kg.models.Game

interface Communicator {
    val game: List<Game>
    fun open(game: Game): Unit
}
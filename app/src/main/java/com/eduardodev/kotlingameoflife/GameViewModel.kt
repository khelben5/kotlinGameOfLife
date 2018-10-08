package com.eduardodev.kotlingameoflife

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.eduardodev.engine.Game
import org.jetbrains.anko.doAsync


class GameViewModel : ViewModel() {

    val game: Game = Game(150, 150)
    private val aliveCells = MutableLiveData<Collection<Pair<Int, Int>>>()

    init {
        doAsync {
            while (true) {
                aliveCells.postValue(game.alivePositions)
                Thread.sleep(64)
                game.evolve()
            }
        }
    }

    fun getAliveCells(): LiveData<Collection<Pair<Int, Int>>> = aliveCells
}
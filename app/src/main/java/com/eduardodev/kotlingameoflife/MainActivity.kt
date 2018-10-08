package com.eduardodev.kotlingameoflife

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val model by lazy { ViewModelProviders.of(this)[GameViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView.post {
            gameView.setup(model.game.width, model.game.height)
            model.getAliveCells().observe(this, Observer { it?.let { cells -> updateUI(cells) } })
        }
    }

    private fun updateUI(aliveCells: Collection<Pair<Int, Int>>) {
        gameView.updateLiveCells(aliveCells)
    }
}

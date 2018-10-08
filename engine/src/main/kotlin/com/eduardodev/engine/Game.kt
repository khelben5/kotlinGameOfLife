package com.eduardodev.engine

import com.eduardodev.engine.util.BooleanMatrix
import com.eduardodev.engine.util.evolve
import java.util.*


class Game(val width: Int, val height: Int) {

    val alivePositions: Collection<Pair<Int, Int>>
        get() = (0 until matrix.width).fold(emptyList()) { accX, x ->
            accX.plus((0 until matrix.height).fold(emptyList<Pair<Int, Int>>()) { accY, y ->
                if (matrix.valueAt(x, y)) accY.plus(x to y)
                else accY
            })
        }

    private val random = Random(Date().time)
    private var matrix: BooleanMatrix = BooleanMatrix(width, height) { _, _ ->
        random.nextFloat() < 0.1f
    }

    fun evolve() {
        matrix = matrix.evolve()
    }
}
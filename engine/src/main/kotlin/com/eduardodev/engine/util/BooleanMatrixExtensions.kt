package com.eduardodev.engine.util


fun BooleanMatrix.trueNeighboursAt(x: Int, y: Int) = listOf(
        x - 1 to y - 1, x to y - 1, x + 1 to y - 1,
        x - 1 to y, x + 1 to y,
        x - 1 to y + 1, x to y + 1, x + 1 to y + 1
).fold(0) { acc, it ->
    try {
        if (valueAt(it.first, it.second)) acc + 1 else acc
    } catch (exception: IndexOutOfBoundsException) {
        acc // Counts as zero.
    }
}

fun BooleanMatrix.evolve() = BooleanMatrix(width, height) { x, y ->
    val trueNeighbours = trueNeighboursAt(x, y)
    when (valueAt(x, y)) {
        true -> trueNeighbours in 2..3
        false -> trueNeighbours == 3
    }
}
package com.eduardodev.engine.util

import java.util.*


class BooleanMatrix(val width: Int, val height: Int, initialise: (x: Int, y: Int) -> Boolean) {

    private val values: BooleanArray = BooleanArray(width * height) {
        initialise(it % width, it / width)
    }

    fun valueAt(x: Int, y: Int): Boolean {
        if (x !in 0 until width || y !in 0 until height) throw IndexOutOfBoundsException()
        return values[x + y * width]
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BooleanMatrix

        if (width != other.width) return false
        if (height != other.height) return false
        if (!Arrays.equals(values, other.values)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + Arrays.hashCode(values)
        return result
    }

    override fun toString() = (0 until height).fold(StringBuilder()) { acc, y ->
        acc.append(values.copyOfRange(y, y + width).toReadableString()).append('\n')
    }.toString()

    private fun BooleanArray.toReadableString() =
            fold(StringBuilder()) { acc, it -> acc.append(if (it) 1 else 0).append('\t') }.toString()
}
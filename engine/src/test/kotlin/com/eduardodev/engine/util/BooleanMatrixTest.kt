package com.eduardodev.engine.util

import org.junit.Assert.*
import org.junit.BeforeClass
import org.junit.Test

private const val WIDTH = 3
private const val HEIGHT = 4

class BooleanMatrixTest {

    private companion object {
        private val truePositions = listOf(1 to 2, 2 to 0, 1 to 1, 2 to 3)
        private lateinit var matrix: BooleanMatrix

        @BeforeClass
        @JvmStatic
        fun setup() {
            matrix = createMatrixWithPositions(WIDTH, HEIGHT, truePositions)
        }

        private fun createMatrixWithPositions(
                width: Int,
                height: Int,
                truePositions: Collection<Pair<Int, Int>>
        ) = BooleanMatrix(width, height) { x, y ->
            when (x to y) {
                in truePositions -> true
                else -> false
            }
        }
    }

    @Test
    fun checkSize() {
        assertEquals("wrong width", WIDTH, matrix.width)
        assertEquals("wrong height", HEIGHT, matrix.height)
    }

    @Test
    fun testValues() {
        (0 until HEIGHT).forEach { y ->
            (0 until WIDTH).forEach { x ->
                if (x to y in truePositions)
                    assertTrue("true expected at position ${x to y}", matrix.valueAt(x, y))
                else
                    assertFalse("false expected at position ${x to y}", matrix.valueAt(x, y))
            }
        }
    }

    @Test
    fun testEquality() {
        val matrixB = createMatrixWithPositions(WIDTH, HEIGHT, truePositions)
        assertEquals("matrices are supposed to be equal", matrix, matrixB)
    }

    @Test
    fun testNonEquality() {
        val matrixB = createMatrixWithPositions(WIDTH, HEIGHT, emptyList())
        assertNotEquals("matrices are not supposed to be equal", matrix, matrixB)
    }

    @Test
    fun testXOutOfBounds() {
        try {
            matrix.valueAt(WIDTH, 1)
            fail("${IndexOutOfBoundsException::class.java.simpleName} expected")
        } catch (exception: IndexOutOfBoundsException) {
            // Everything OK.
        }
    }

    @Test
    fun testYOutOfBounds() {
        try {
            matrix.valueAt(0, HEIGHT)
            fail("${IndexOutOfBoundsException::class.java.simpleName} expected")
        } catch (exception: IndexOutOfBoundsException) {
            // Everything OK.
        }
    }

    @Test
    fun testNegativeX() {
        try {
            matrix.valueAt(-1, 1)
            fail("${IndexOutOfBoundsException::class.java.simpleName} expected")
        } catch (exception: IndexOutOfBoundsException) {
            // Everything OK.
        }
    }

    @Test
    fun testNegativeY() {
        try {
            matrix.valueAt(0, -1)
            fail("${IndexOutOfBoundsException::class.java.simpleName} expected")
        } catch (exception: IndexOutOfBoundsException) {
            // Everything OK.
        }
    }
}
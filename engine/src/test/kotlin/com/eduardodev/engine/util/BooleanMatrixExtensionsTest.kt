package com.eduardodev.engine.util

import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test

class BooleanMatrixExtensionsTest {

    private companion object {
        private lateinit var matrix: BooleanMatrix

        @BeforeClass
        @JvmStatic
        fun setup() {
            matrix = BooleanMatrix(5, 4) { x, y ->
                when (x to y) {
                    0 to 0, 3 to 0, 4 to 0,
                    0 to 1, 2 to 1, 3 to 1, 4 to 1,
                    2 to 2 -> true
                    else -> false
                }
            }
        }
    }

    @Test
    fun testTrueNeighboursAt() {
        val expectedNeighbours = HashMap<Pair<Int, Int>, Int>(matrix.width * matrix.height)
                .apply {
                    put(0 to 0, 1)
                    put(1 to 0, 3)
                    put(2 to 0, 3)
                    put(3 to 0, 4)
                    put(4 to 0, 3)

                    put(0 to 1, 1)
                    put(1 to 1, 4)
                    put(2 to 1, 3)
                    put(3 to 1, 5)
                    put(4 to 1, 3)

                    put(0 to 2, 1)
                    put(1 to 2, 3)
                    put(2 to 2, 2)
                    put(3 to 2, 4)
                    put(4 to 2, 2)

                    put(0 to 3, 0)
                    put(1 to 3, 1)
                    put(2 to 3, 1)
                    put(3 to 3, 1)
                    put(4 to 3, 0)
                }

        expectedNeighbours.keys.forEach {
            val expected = expectedNeighbours[it]
            val actual = matrix.trueNeighboursAt(it.first, it.second)
            assertEquals("neighbours for position $it must be $expected", expected, actual)
        }
    }

    @Test
    fun testEvolve() {
        val expectedMatrix = BooleanMatrix(matrix.width, matrix.height) { x, y ->
            when (x to y) {
                1 to 0, 2 to 0, 4 to 0,
                2 to 1, 4 to 1,
                1 to 2, 2 to 2 -> true
                else -> false
            }
        }

        assertEquals("unexpected alive positions", expectedMatrix, matrix.evolve())
    }
}
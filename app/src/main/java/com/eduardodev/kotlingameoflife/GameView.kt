package com.eduardodev.kotlingameoflife

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import org.jetbrains.anko.backgroundColor


class GameView(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int)
    : View(context, attrs, defStyleAttr, defStyleRes) {

    private var cellWidth: Int? = null
    private var cellHeight: Int? = null
    private var liveCells: Collection<Pair<Int, Int>> = emptyList()

    constructor(context: Context)
            : this(context, null, 0, 0)

    constructor(context: Context, attrs: AttributeSet)
            : this(context, attrs, 0, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    init {
        backgroundColor = ContextCompat.getColor(context, android.R.color.black)
    }

    fun setup(gameWidth: Int, gameHeight: Int) {
        cellWidth = width / gameWidth
        cellHeight = height / gameHeight
    }

    fun updateLiveCells(liveCells: Collection<Pair<Int, Int>>) {
        this.liveCells = liveCells
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cellWidth = this.cellWidth
        val cellHeight = this.cellHeight

        if (cellWidth == null || cellHeight == null) return

        liveCells.forEach {
            canvas.drawRect(
                    Rect(
                            it.first * cellWidth,
                            it.second * cellHeight,
                            it.first * cellWidth + cellWidth,
                            it.second * cellHeight + cellHeight
                    ),
                    Paint().apply {
                        color = ContextCompat.getColor(context, android.R.color.white)
                        style = Paint.Style.FILL
                    }
            )
        }
    }
}
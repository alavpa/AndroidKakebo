package com.alavpa.androidkakebo.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.alavpa.androidkakebo.R
import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.math.absoluteValue

class KakeboHistogram : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private var startX = 0f
    private var stopX = 0f
    private var startY = 0f
    private var stopY = 0f
    private var hh = 0f
    private var ww = 0f
    private val rectf = RectF()
    private var colorBlue = ContextCompat.getColor(context, R.color.colorPrimary)
    private var colorRed = ContextCompat.getColor(context, R.color.red)
    private var textSize = 40f
    private var textBounds = Rect()

    var data = mutableListOf<Pair<String, Float>>()

    init {
        data.add(Pair("ene.", -12.5f))
        data.add(Pair("feb", -32.5f))
        data.add(Pair("mar.", 50.5f))
    }

    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = colorBlue
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val histogramBluePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = colorBlue
        style = Paint.Style.FILL_AND_STROKE
        textSize = this@KakeboHistogram.textSize
    }

    private val histogramRedPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = colorRed
        style = Paint.Style.FILL_AND_STROKE
        textSize = this@KakeboHistogram.textSize
    }

    init {
        setBackgroundResource(R.color.white)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {
            drawLine(startX, startY, stopX, stopY, linePaint)

            val max = data.map { it.second.absoluteValue }.max() ?: 0f

            data.forEachIndexed { i, value ->

                histogramRedPaint.getTextBounds(data[i].first, 0, data[i].first.length, textBounds)
                val offseth = (textBounds.bottom - textBounds.top).toFloat()
                val hhh = hh - offseth

                val offset = ww / (data.size.toFloat() * 2)
                val divisor = (i) / data.size.toFloat()
                val valueData = (value.second * (hhh / 2) / max)
                rectf.left = offset + paddingLeft + (ww * divisor) - 10
                rectf.right = offset + paddingLeft + (ww * divisor) + 10

                if (valueData > 0) {
                    rectf.top = startY - valueData
                    rectf.bottom = startY
                    drawRect(rectf, histogramBluePaint)
                    drawText(data[i].first, rectf.left - 20, rectf.top - 10, histogramBluePaint)
                } else {
                    rectf.top = startY
                    rectf.bottom = startY - valueData
                    drawRect(rectf, histogramRedPaint)
                    drawText(data[i].first, rectf.left - 20, rectf.bottom + 40, histogramRedPaint)
                }
            }

        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {

        hh = height.toFloat() - (paddingTop + paddingBottom).toFloat()
        ww = width.toFloat() - (paddingLeft + paddingRight).toFloat()

        startX = paddingLeft.toFloat()
        stopX = width.toFloat() - paddingRight.toFloat()
        startY = paddingTop + (hh / 2f)
        stopY = paddingTop + (hh / 2f)
    }
}
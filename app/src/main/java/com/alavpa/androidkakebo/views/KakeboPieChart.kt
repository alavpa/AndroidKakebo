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

class KakeboPieChart : View {

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    private val dimensions = RectF()
    private val bounds = Rect()

    var amount = ""
    var angle = 0f
    var strokeSize = 0f
    var textSize = context.resources.getDimension(R.dimen.font_big)
    var color = ContextCompat.getColor(context, R.color.red)

    private val piePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.white)
        style = Paint.Style.FILL_AND_STROKE
    }

    private val slicePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
        textSize = this@KakeboPieChart.textSize
    }

    init {
        setBackgroundResource(R.color.white)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.apply {

            // Draw the shadow
            dimensions.bottom = cy + radius
            dimensions.top = cy - radius
            dimensions.left = cx - radius
            dimensions.right = cx + radius

            slicePaint.color = color

            drawArc(
                dimensions,
                0f,
                angle,
                true,
                slicePaint
            )

            drawCircle(cx, cy, radius - strokeSize, piePaint)

            slicePaint.getTextBounds(amount, 0, amount.length, bounds)
            val diameter = (radius - strokeSize - 20) * 2
            while ((bounds.right - bounds.left) > diameter) {
                slicePaint.textSize = slicePaint.textSize - 2
                slicePaint.getTextBounds(amount, 0, amount.length, bounds)
            }

            drawText(amount,
                cx - bounds.centerX(),
                cy - bounds.centerY(),
                slicePaint
            )
        }
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {

        // Account for padding
        val xpad = (paddingLeft + paddingRight).toFloat()
        val ypad = (paddingTop + paddingBottom).toFloat()

        val ww = width.toFloat() - xpad
        val hh = height.toFloat() - ypad

        cx = paddingLeft + (ww / 2f)
        cy = paddingTop + (hh / 2f)

        // Figure out how big we can make the pie.
        radius = Math.min(ww, hh) / 2f
    }
}
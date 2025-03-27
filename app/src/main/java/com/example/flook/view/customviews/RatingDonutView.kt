package com.example.flook.view.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.example.flook.R

class RatingDonutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : View(context, attributeSet) {

    private val oval = RectF()

    private var radius: Float = 0f
    private var centerX: Float = 0f
    private var centerY: Float = 0f

    private var stroke = 10f
    private var progress = 70
    private var scaleSize = 60f

    private lateinit var strokePaint: Paint
    private lateinit var digitPaint: Paint
    private lateinit var circlePaint: Paint

    private var backgroundColor = Color.DKGRAY

    init {
        val a = context.theme.obtainStyledAttributes(attributeSet, R.styleable.Custom, 0, 0)

        try {
            backgroundColor = a.getColor(R.styleable.Custom_backgroundColor, backgroundColor)
            stroke = a.getFloat(R.styleable.Custom_stroke_width, stroke)
            progress = a.getInt(R.styleable.Custom_progress, progress)
        } finally {
            a.recycle()
        }

        initPaint()
    }

    override fun onDraw(canvas: Canvas) {
        drawRatting(canvas)
        drawText(canvas)
    }

    private fun drawRatting(canvas: Canvas) {  //  кольцо рейтинга
        val scale = radius * 0.8f

        canvas.save()
        canvas.translate(centerX, centerY)

        oval.set(0f - scale, 0f - scale, scale, scale)
        canvas.drawCircle(0f, 0f, radius, circlePaint)  //фон
        canvas.drawArc(
            oval,
            -90f,
            convertProgressToDegrees(progress),
            false,
            strokePaint
        )  // параметр заполнености

        canvas.restore()
    }

    private fun drawText(canvas: Canvas) {  //текст
        val message = String.format("%.1f", progress / 10f)
        val widths = FloatArray(message.length)
        digitPaint.getTextWidths(message, widths)
        var advance = 0f
        for (width in widths) advance += width
        canvas.drawText(message, centerX - advance / 2, centerY + advance / 4, digitPaint)
    }

    private fun convertProgressToDegrees(progress: Int): Float = progress * 3.6f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val chosenWith = chooseDimension(widthMode, widthSize)
        val chosenHeight = chooseDimension(heightMode, heightSize)

        val minSide = Math.min(chosenWith, chosenHeight)
        centerX = minSide.div(2f)
        centerY = minSide.div(2f)

        setMeasuredDimension(minSide, minSide)
    }

    private fun chooseDimension(mode: Int, size: Int) = when (mode) {
        MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> size
        else -> 300
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = if (width > height) {
            height.div(2f)
        } else {
            width.div(2f)
        }
    }

    private fun initPaint() {
        strokePaint = Paint().apply {       // кольца
            style = Paint.Style.STROKE
            strokeWidth = stroke
            color = getColorStat(progress)
            isAntiAlias = true
        }
        digitPaint = Paint().apply {        //цифры
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 2f
            setShadowLayer(5f, 0f, 0f, Color.DKGRAY)
            textSize = scaleSize
            typeface = Typeface.SANS_SERIF
            color = getColorStat(progress)
            isAntiAlias = true
        }
        circlePaint = Paint().apply {         //Фоновый круг
            style = Paint.Style.FILL
            color = backgroundColor
        }
    }

    private fun getColorStat(progress: Int): Int = when (progress) {
        in 0..24 -> Color.RED
        in 25..49 -> Color.parseColor("#FF845C")
        in 50..74 -> Color.YELLOW
        else -> Color.GREEN
    }

    fun setProgress(pr: Int) {
        progress = pr
        initPaint()
        invalidate()
    }
}
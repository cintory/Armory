package com.cintory.armory.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet

/**
 * Created by Cintory on 2018/8/1 15:11
 * Email：Cintory@gmail.com
 */
class ItemView : android.support.v7.widget.AppCompatImageView {

    private var itemLevel = -1

    private val tagPaddingHorizontal = 15
    private val tagPaddingVertical = 10
    private var textColor = "#ffffff"


    internal var paint = Paint(Paint.ANTI_ALIAS_FLAG)


    constructor(context: Context) : super(context)


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        paint.textSize = 30f
    }


    fun setItemLevel(itemLevel: Int) {
        this.itemLevel = itemLevel
        invalidate()
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (itemLevel == -1) return

        val metrics = paint.fontMetrics

        paint.color = Color.parseColor("#AF000000")
        val bounds = Rect()
        paint.getTextBounds(
            itemLevel.toString(), 0, itemLevel.toString().length,
            bounds
        )

        val offsetX = width - (bounds.right - bounds.left) - tagPaddingHorizontal
        val offsetY = height - tagPaddingVertical
        bounds.left += offsetX - tagPaddingHorizontal
        bounds.right += offsetX + tagPaddingHorizontal
        bounds.top += offsetY - tagPaddingVertical
        bounds.bottom += offsetY + tagPaddingVertical
        canvas.drawRect(bounds, paint)
        paint.color = Color.parseColor(textColor)
        canvas.drawText(itemLevel.toString(), offsetX.toFloat(), offsetY.toFloat(), paint)
    }


    fun setTextColor(color: String) {
        this.textColor = color
        invalidate()
    }


    override fun onDrawForeground(canvas: Canvas) {
        super.onDrawForeground(canvas)
    }
}

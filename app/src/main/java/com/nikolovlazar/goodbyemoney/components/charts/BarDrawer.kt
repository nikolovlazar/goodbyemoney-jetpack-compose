package com.nikolovlazar.goodbyemoney.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData
import com.nikolovlazar.goodbyemoney.models.Recurrence
import com.nikolovlazar.goodbyemoney.ui.theme.SystemGray04

class BarDrawer constructor(recurrence: Recurrence) :
  com.github.tehras.charts.bar.renderer.bar.BarDrawer {
  private val barPaint = Paint().apply {
    this.isAntiAlias = true
  }

  private val rightOffset = when(recurrence) {
    Recurrence.Weekly -> 24f
    Recurrence.Monthly -> 6f
    Recurrence.Yearly -> 18f
    else -> 0f
  }

  override fun drawBar(
    drawScope: DrawScope,
    canvas: Canvas,
    barArea: Rect,
    bar: BarChartData.Bar
  ) {
    canvas.drawRoundRect(
      barArea.left,
      0f,
      barArea.right + rightOffset,
      barArea.bottom,
      16f,
      16f,
      barPaint.apply {
        color = SystemGray04
      },
    )
    canvas.drawRoundRect(
      barArea.left,
      barArea.top,
      barArea.right + rightOffset,
      barArea.bottom,
      16f,
      16f,
      barPaint.apply {
        color = bar.color
      },
    )
  }
}
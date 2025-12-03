package br.edu.ifpe.alvarium.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

@Composable
fun CoinChartWithGrid(points: List<Pair<Long, Double>>) {

    if (points.isEmpty()) {
        Text("Carregando gráfico...", color = Color.White, modifier = Modifier.padding(16.dp))
        return
    }

    val maxY = points.maxOf { it.second }.toFloat()
    val minY = points.minOf { it.second }.toFloat()
    val rangeY = if (maxY - minY == 0f) 1f else maxY - minY

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val stepX = if (points.size > 1) size.width / (points.size - 1) else size.width / 2f
        val stepY = size.height / 6f

        // Grid horizontal
        repeat(6) { i ->
            drawLine(
                color = Color.White.copy(alpha = 0.15f),
                start = Offset(0f, stepY * i),
                end = Offset(size.width, stepY * i),
                strokeWidth = 1.dp.toPx()
            )
        }

        // Linha do gráfico
        val path = Path()
        points.forEachIndexed { i, pair ->
            val x = i * stepX
            val y = size.height - ((pair.second.toFloat() - minY) / rangeY * size.height)

            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
        }

        drawPath(
            path = path,
            color = Color(0xFF00FF80),
            style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}
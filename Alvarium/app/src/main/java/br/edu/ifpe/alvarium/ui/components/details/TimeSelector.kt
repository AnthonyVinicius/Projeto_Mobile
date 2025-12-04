package br.edu.ifpe.alvarium.ui.components.details


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimeSelector(
    selectedDays: Int,
    onClick: (Int) -> Unit
) {
    val items = listOf(
        1 to "24h",
        7 to "7d",
        30 to "30d"
    )

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEach { (days, label) ->
            val selected = selectedDays == days

            Button(
                onClick = { onClick(days) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selected) Color(0xFF6C63FF) else Color(0xFF0F1A34)
                ),
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = label,
                    color = if (selected) Color.White else Color.Gray
                )
            }
        }
    }
}
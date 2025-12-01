package br.edu.ifpe.alvarium.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CriptoCard(
    name: String,
    acronym: String,
    price: String,
    imageUrl: String? = null,
    onClick: () -> Unit = {}
) {

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(20.dp), // igual ao print
        color = Color(0xFF152342).copy(alpha = 0.65f), // azul translúcido
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.10f)), // borda suave
        tonalElevation = 0.dp
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    modifier = Modifier.size(42.dp)
                )
            }

            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Text(
                    text = acronym,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF8E9BBF) // cinza-lavanda igual ao print
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = price,
                style = MaterialTheme.typography.titleSmall,
                color = Color(0xFF6F7CF6) // azul destaque do print
            )
        }
    }
}

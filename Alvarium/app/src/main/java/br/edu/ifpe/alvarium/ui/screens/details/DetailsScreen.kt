package br.edu.ifpe.alvarium.ui.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.edu.ifpe.alvarium.ui.components.details.CoinChartWithGrid
import br.edu.ifpe.alvarium.viewmodel.DetailsViewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.viewmodel.factory.AppViewModelFactory
import android.content.Context
import br.edu.ifpe.alvarium.ui.components.details.TimeSelector
import coil.compose.AsyncImage

@Composable
fun DetailsScreen(
    coinId: String,
    context: Context,
    onBack: () -> Unit = {}
) {
    val factory = AppViewModelFactory(context)
    val coinViewModel: CoinViewModel = viewModel(factory = factory)
    val detailsViewModel: DetailsViewModel = viewModel(factory = factory)

    val coins by coinViewModel.coins.collectAsState()
    val chartPoints by detailsViewModel.chartData.collectAsState()
    val selectedDays by detailsViewModel.selectedDays.collectAsState()

    val coin = coins.find { it.id == coinId }

    if (coin == null) {
        Text("Carregando...", color = Color.White)
        return
    }

    LaunchedEffect(coinId) {
        detailsViewModel.startAutoUpdate(coinId, 1)
    }

    val currentPrice = chartPoints.lastOrNull()?.second ?: coin.currentPrice
    val priceChange = (coin.currentPrice ?: 0.0) / 100
    val isUp = priceChange >= 0
    val changeColor = if (isUp) Color(0xFF4CAF50) else Color(0xFFFF5252)
    val changeIcon = if (isUp) "▲" else "▼"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            IconButton(onClick = onBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }

            Spacer(Modifier.width(12.dp))

            AsyncImage(
                model = coin.image,
                contentDescription = coin.name,
                modifier = Modifier.size(40.dp)
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(
                    text = coin.name,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = coin.symbol.uppercase(),
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "US$ ${String.format("%.2f", currentPrice)}",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "$changeIcon ${String.format("%.2f", priceChange)}%",
                color = changeColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(Modifier.height(20.dp))

        TimeSelector(
            selectedDays = selectedDays,
            onClick = { days -> detailsViewModel.startAutoUpdate(coinId, days) }
        )

        Spacer(Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1A34)),
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
        ) {
            CoinChartWithGrid(points = chartPoints, days = selectedDays)
        }
    }
}
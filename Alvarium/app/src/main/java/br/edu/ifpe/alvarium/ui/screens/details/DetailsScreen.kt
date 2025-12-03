package br.edu.ifpe.alvarium.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.edu.ifpe.alvarium.ui.components.CoinChartWithGrid
import br.edu.ifpe.alvarium.viewmodel.DetailsViewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.viewmodel.factory.AppViewModelFactory
import android.content.Context

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
    val coin = coins.find { it.id == coinId }

    if (coin == null) {
        // fallback enquanto carrega
        Text("Carregando...", color = Color.White)
        return
    }

    val chartPoints by detailsViewModel.chartData.collectAsState()

    LaunchedEffect(coinId) {
        detailsViewModel.startAutoUpdate(coinId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        // Cabeçalho com botão de voltar
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
            }

            Spacer(Modifier.width(12.dp))

            Box(
                Modifier
                    .size(40.dp)
                    .background(Color(0xFFFFA726), shape = CircleShape)
            )

            Spacer(Modifier.width(12.dp))

            Column {
                Text(coin.name, color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text(coin.symbol.uppercase(), color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
        }

        Spacer(Modifier.height(30.dp))

        Text(
            text = "US$ ${coin.currentPrice}",
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(Modifier.height(25.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1A34)),
            modifier = Modifier.fillMaxWidth().height(280.dp)
        ) {
            CoinChartWithGrid(points = chartPoints)
        }
    }
}
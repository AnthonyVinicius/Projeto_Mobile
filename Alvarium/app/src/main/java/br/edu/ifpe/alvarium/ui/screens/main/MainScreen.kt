package br.edu.ifpe.alvarium.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.ui.components.CriptoCard
import br.edu.ifpe.alvarium.viewmodel.CoinViewModelFactory

@Composable
fun MainScreen(
    onNavigateToDetails: (String) -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel: CoinViewModel =
        viewModel(factory = CoinViewModelFactory(context))

    val coins by viewModel.coins.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Text(
            text = "Bem-vindo ao Alvarium!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Crypto Tracker!",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )

        Button(
            onClick = { viewModel.loadCoins() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Atualizar")
        }

        Text(
            text = "Principais Criptomoedas",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        if (coins.isEmpty()) {
            Text("Carregando moedas...")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(coins) { coin ->
                    CriptoCard(
                        name = coin.name,
                        acronym = coin.symbol.uppercase(),
                        price = "US$ ${coin.currentPrice}",
                        imageUrl = coin.image
                    ) {
                        onNavigateToDetails(coin.id)
                    }
                }
            }
        }
    }
}
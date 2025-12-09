@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.ifpe.alvarium.ui.screens.converter

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme
import br.edu.ifpe.alvarium.viewmodel.CoinViewModel
import br.edu.ifpe.alvarium.viewmodel.factory.AppViewModelFactory

@Composable
fun ConverterScreen(
    viewModel: CoinViewModel = viewModel(factory = AppViewModelFactory(LocalContext.current))
) {
    val coins by viewModel.coins.collectAsState()

    var fromCoin by remember { mutableStateOf("BTC") }
    var toCoin by remember { mutableStateOf("ETH") }
    var quantity by remember { mutableStateOf("1") }

    val fromPrice = coins.find { it.symbol.equals(fromCoin, true) }?.currentPrice ?: 0.0
    val toPrice = coins.find { it.symbol.equals(toCoin, true) }?.currentPrice ?: 1.0

    val convertedValue = remember(fromCoin, toCoin, quantity, coins) {
        val q = quantity.toDoubleOrNull() ?: 0.0
        if (fromPrice > 0 && toPrice > 0) {
            (q * fromPrice) / toPrice
        } else {
            0.0
        }
    }

    val exchangeRate = remember(fromPrice, toPrice) {
        if (fromPrice > 0 && toPrice > 0) {
            fromPrice / toPrice
        } else {
            0.0
        }
    }

    val alvariumGradient = Brush.verticalGradient(
        listOf(Color(0xFF0A0F2A), Color(0xFF12203F))
    )

    Surface(
        modifier = Modifier.fillMaxSize().background(alvariumGradient),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(WindowInsets.systemBars.asPaddingValues())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text("Conversor", style = MaterialTheme.typography.headlineMedium, color = Color.White)
            Text("Converta criptomoedas instantaneamente", color = Color.White.copy(alpha = 0.7f))

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF152342).copy(alpha = 0.65f),
                border = ButtonDefaults.outlinedButtonBorder,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(Modifier.padding(20.dp)) {

                    CryptoSelector(
                        title = "De",
                        selected = fromCoin,
                        items = coins.map { it.symbol.uppercase() }
                    ) { fromCoin = it }

                    Spacer(Modifier.height(20.dp))

                    QuantityInput(quantity) { quantity = it }

                    Spacer(Modifier.height(20.dp))

                    Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        SwapButton {
                            val aux = fromCoin
                            fromCoin = toCoin
                            toCoin = aux
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    CryptoSelector(
                        title = "Para",
                        selected = toCoin,
                        items = coins.map { it.symbol.uppercase() }
                    ) { toCoin = it }

                    Spacer(Modifier.height(24.dp))

                    ConvertedCard(
                        value = String.format("%.8f %s", convertedValue, toCoin),
                        rate = "1 $fromCoin = ${String.format("%.8f", exchangeRate)} $toCoin"
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptoSelector(
    title: String,
    selected: String,
    items: List<String>,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column {
        Text(
            text = title,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = selected,
                onValueChange = {},
                readOnly = true,
                shape = RoundedCornerShape(20.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF152342).copy(0.80f),
                    unfocusedContainerColor = Color(0xFF152342).copy(0.65f),
                    focusedBorderColor = Color(0xFF6F7CF6),
                    unfocusedBorderColor = Color.White.copy(0.15f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF1A2342), Color(0xFF24345C))
                        ),
                        RoundedCornerShape(20.dp)
                    )
                    .border(
                        1.dp,
                        Color(0xFF6F7CF6),
                        RoundedCornerShape(20.dp)
                    )
                    .heightIn(max = 260.dp) // Máximo para listas grandes
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp) // Melhor toque
                            .background(
                                if (item == selected)
                                    Color.White.copy(0.08f)
                                else Color.Transparent
                            ),
                        text = {
                            Text(
                                item,
                                color = if (item == selected)
                                    Color(0xFF9DB5FF)
                                else Color.White,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        },
                        onClick = {
                            onSelect(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun QuantityInput(quantity: String, onChange: (String) -> Unit) {
    Column {
        Text(
            "Quantidade",
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = quantity,
            onValueChange = { if (it.isEmpty() || it.matches(Regex("""\d*\.?\d*"""))) onChange(it) },
            placeholder = { Text("0.00", color = Color.White.copy(0.5f)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF6F7CF6),
                unfocusedBorderColor = Color.White.copy(0.10f),
                focusedContainerColor = Color(0xFF152342).copy(0.65f),
                unfocusedContainerColor = Color(0xFF152342).copy(0.65f),
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    }
}

@Composable
fun SwapButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .background(
                Brush.linearGradient(listOf(Color(0xFF7B42F6), Color(0xFF5EADF9))),
                shape = CircleShape
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.SwapVert, contentDescription = "Trocar moedas", tint = Color.White)
    }
}

@Composable
fun ConvertedCard(value: String, rate: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(
                Brush.linearGradient(listOf(Color(0xFF1E1F38), Color(0xFF2A2D4A))),
                shape = RoundedCornerShape(16.dp)
            )
            .padding(20.dp)
    ) {
        Text(
            "Valor Convertido",
            color = Color.White.copy(0.8f),
            style = MaterialTheme.typography.labelMedium
        )
        Spacer(Modifier.height(12.dp))
        Text(
            value,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White
        )
        Spacer(Modifier.height(6.dp))
        Text(
            rate,
            color = Color.White.copy(0.7f),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun ConverterScreenPreview() {
    AlvariumTheme { ConverterScreen() }
}
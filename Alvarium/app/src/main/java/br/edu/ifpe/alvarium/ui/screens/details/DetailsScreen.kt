package br.edu.ifpe.alvarium.ui.screens.details

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme
@Composable
fun DetailsScreen(acronym: String) {
    Column() {

    }
}
@Preview
@Composable
fun DetailsScreenPreview() {
    AlvariumTheme { DetailsScreen("BTC") }
}
package br.edu.ifpe.alvarium.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.ifpe.alvarium.R
import br.edu.ifpe.alvarium.viewmodel.SplashEvent
import br.edu.ifpe.alvarium.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = viewModel(),
    onNavigateToHome: () -> Unit
) {
    val eventFlow = viewModel.event

    LaunchedEffect(key1 = true) {
        eventFlow.collect { event ->
            when(event) {
                is SplashEvent.NavigateToHome -> onNavigateToHome()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(  painter = painterResource(id = R.drawable.ic_launcher_round),
                contentDescription = "Logo do App",

                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
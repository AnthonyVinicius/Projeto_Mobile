package br.edu.ifpe.alvarium

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.edu.ifpe.alvarium.ui.navigation.AlvariumApp
import br.edu.ifpe.alvarium.ui.navigation.AppNavHost
import br.edu.ifpe.alvarium.ui.theme.AlvariumTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AlvariumTheme {
                AlvariumApp()
            }
        }

    }
}
@Composable
@Preview
fun MainActivityPreview() {
    AlvariumTheme {
        AppNavHost()
    }
}


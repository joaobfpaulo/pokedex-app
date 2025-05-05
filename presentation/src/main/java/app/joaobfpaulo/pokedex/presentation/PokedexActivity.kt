package app.joaobfpaulo.pokedex.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import app.joaobfpaulo.pokedex.presentation.navigation.drawer.DrawerNavigation
import app.joaobfpaulo.pokedex.presentation.ui.splashscreen.SplashViewModel
import app.joaobfpaulo.pokedex.presentation.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : ComponentActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            PokedexTheme {
                val menus = viewModel.menus.value
                val hasError = viewModel.hasError.value
                if ( menus != null || hasError)
                    DrawerNavigation(
                        menus = menus,
                        hasError = hasError,
                        onFinish = this::finish
                    )
            }
        }
    }
}

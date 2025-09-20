package br.com.brunocarvalhs.howmuch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import br.com.brunocarvalhs.howmuch.app.foundation.analytics.firstOpen
import br.com.brunocarvalhs.howmuch.app.foundation.analytics.trackNavigation
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.isFirstAppOpen
import br.com.brunocarvalhs.howmuch.app.foundation.theme.HowMuchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            navController.trackNavigation()
            HowMuchTheme {
                Surface(
                    modifier = Modifier
                        .imePadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    MainApp(activity = this, navController = navController)
                }
            }
        }

        lifecycleScope.launch {
            if (isFirstAppOpen()) {
                firstOpen()
            }
        }
    }
}

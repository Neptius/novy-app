import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.novy.app.modules.phoenix.presentation.viewmodel.PhoenixViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun ConnectionBanner(
    phoenixViewModel: PhoenixViewModel = koinViewModel(),
) {
    val phoenixState = phoenixViewModel.phoenixState.collectAsState()

    val backgroundColor = when (phoenixState.value.connected) {
        true -> Color(0xFF4CAF50) // Vert
        false -> Color(0xFFF44336) // Rouge
    }
    val text = when (phoenixState.value.connected) {
        true -> "Connecté !"
        false -> "Reconnexion en cours..."
    }

    LaunchedEffect(phoenixState.value.connected) {
        if (phoenixState.value.connected) {
            delay(3000)
        }
    }

    AnimatedVisibility(
        visible = !phoenixState.value.connected,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { -it })
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

enum class ConnectionState {
    Hidden, Connected, Disconnected
}

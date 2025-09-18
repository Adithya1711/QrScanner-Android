package util.aurorus.qrscanner

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun QRScannerApp() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Scanner) }
    var scannedResult by remember { mutableStateOf("") }

    when (currentScreen) {
        is Screen.Scanner -> {
            QRScannerScreen(
                onQRCodeScanned = { result ->
                    scannedResult = result
                    currentScreen = Screen.Result
                }
            )
        }
        is Screen.Result -> {
            ResultScreen(
                qrResult = scannedResult,
                onBackClick = {
                    currentScreen = Screen.Scanner
                }
            )
        }
    }
}

sealed class Screen {
    object Scanner : Screen()
    object Result : Screen()
}

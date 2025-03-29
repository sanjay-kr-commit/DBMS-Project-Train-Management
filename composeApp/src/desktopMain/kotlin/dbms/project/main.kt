package dbms.project

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.jetbrains.compose.reload.DevelopmentEntryPoint

fun main() {

    path = System.getProperty("user.dir")+"/"
    inMemory = false

    val context = Context()

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Train Management",
        ) {
            DevelopmentEntryPoint {
                MaterialTheme(colors =
                    if ( context.isDarkTheme.value ) darkColors() else lightColors()
                ) {
                    Scaffold {
                        Navigator(
                            context
                        )
                    }
                }
            }
        }
    }
}

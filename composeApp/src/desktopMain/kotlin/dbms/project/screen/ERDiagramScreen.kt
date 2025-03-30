package dbms.project.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import dbms.composeapp.generated.resources.ER_MODEL
import dbms.composeapp.generated.resources.ER_MODEL_night
import dbms.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun ERDiagramScreen(
    isDarkMode : MutableState<Boolean>
) {
    Box( Modifier.fillMaxSize()
        , contentAlignment = Alignment.Center ) {
        AnimatedVisibility( isDarkMode.value , enter = fadeIn() , exit = fadeOut() ) {
            Image(
                painter = painterResource( Res.drawable.ER_MODEL_night ) ,
                contentDescription = null ,
                modifier = Modifier.fillMaxSize() ,
                contentScale = ContentScale.Fit
            )
        }
        AnimatedVisibility(
            !isDarkMode.value,
            enter = fadeIn() , exit = fadeOut()
        ) {
            Image(
                painter = painterResource( Res.drawable.ER_MODEL ) ,
                contentDescription = null ,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        }
    }
}
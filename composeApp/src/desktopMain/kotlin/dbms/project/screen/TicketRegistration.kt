package dbms.project.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dbms.project.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

private val listOfScreens : List<@Composable (context:Context,nextButton:()->Unit,prevButton:()->Unit) -> Unit> = listOf(
    { context , nextButton , prevButton -> TrainInsert(context,nextButton,prevButton) }


    ,
    { context , _ , _ ->
        Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
            Button( {
                context.navigationController.back()
            } ) {
                Text( "Ticket Registration Complete" )
            }
        }
    }
)

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun TicketRegistration(
    context : Context
) {
    val cancelTicket = remember { Stack<()->Unit>() }
    var activeIndex by remember { mutableStateOf(0) }
    Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
        listOfScreens.forEachIndexed { index, s ->
            if ( activeIndex == index ) s(context, { activeIndex++ } , { activeIndex-- } )
        }
    }
    DisposableEffect(null) {
        onDispose {
            println( "Exited $activeIndex : ${listOfScreens.size}" )
            if ( activeIndex < listOfScreens.size-1 ) {
                println( "Ticket Registration Cancelled" )
                GlobalScope.launch {
                    while ( cancelTicket.isNotEmpty() ) cancelTicket.pop().invoke()
                }
            }
        }
    }
}

@Composable
private fun TrainInsert(
    context: Context,
    nextButton : () -> Unit ,
    prevButton : () -> Unit
) {

    Button(
        onClick = {
            nextButton
        }
    ) {
        Text( "Next Screen" )
    }
}
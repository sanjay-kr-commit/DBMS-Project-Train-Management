package dbms.project.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dbms.project.Context
import dbms.project.Screen

@Composable
fun HomeScreen(
    context : Context
) {
    Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
        Column ( Modifier.wrapContentSize(), Arrangement.Center , Alignment.CenterHorizontally ) {
            listOf(
                "Ticket List" to {
                    context.navigationController.navigateTo( Screen.TicketList )
                } ,
                "Register Ticket" to {
                    context.navigationController.navigateTo( Screen.RegisterTicket )
                }
            ).forEach { ( name , func ) ->
                    Box( Modifier
                        .width(100.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(MaterialTheme.colors.primary)
                        .padding(5.dp)
                        .clickable(onClick = func) ,
                        contentAlignment = Alignment.Center
                    ) {
                        Text(name,color = MaterialTheme.colors.onPrimary)
                    }
                    Spacer( Modifier.height(30.dp) )
                }

        }
    }
}

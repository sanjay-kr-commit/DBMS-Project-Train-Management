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
fun MainScreen(
    context: Context
) {
    Box( Modifier.fillMaxSize().padding( 50.dp ) , contentAlignment = Alignment.TopCenter ) {
        Text( "Welcome to Train Management" , color = MaterialTheme.colors.onSurface )
    }
    Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
        Column ( Modifier.wrapContentSize(), Arrangement.Center , Alignment.CenterHorizontally ) {
            listOf( "Login" to {
                context.navigationController.navigateTo( Screen.LoginScreen )
            } , "Register" to {
                context.navigationController.navigateTo( Screen.RegisterScreen )
            },  "User List" to {
                context.navigationController.navigateTo( Screen.UserListScreen )
            }, "ER Diagram" to {
                context.navigationController.navigateTo( Screen.ERDiagramScreen )
            } )
                .forEach { ( name , func ) ->
                    Box( Modifier
                        .width(115.dp)
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

            Spacer(modifier = Modifier.height(50.dp))
            Box( Modifier
                .width(150.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(MaterialTheme.colors.primary)
                .padding(5.dp)
                .clickable(onClick = {
                    context.isDarkTheme.value = !context.isDarkTheme.value
                }) ,
                contentAlignment = Alignment.Center
            ) {
                Text( "Toggle Theme" ,color = MaterialTheme.colors.onPrimary)
            }

        }
    }
}
package dbms.project

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dbms.project.navigation.CreateScreenCatalog
import dbms.project.navigation.Screen
import dbms.project.screen.*

@Composable
fun Navigator(
    context : Context = Context()
) = CreateScreenCatalog(
    navigationController = context.navigationController,
) {

    Screen( Screen.MainScreen ) {
        MainScreen(context)
    }

    Screen( Screen.LoginScreen ) {
        LoginOrRegisterScreen(
            context ,
            login = true
        )
    }

    Screen( Screen.RegisterScreen ) {
        LoginOrRegisterScreen(
            context ,
            login = false
        )
    }

    Screen( Screen.UserListScreen ) {
        UserList(context)
    }

    Screen( Screen.HomeScreen ) {
        LogoutButton(
            context = context,
            buttonText = "Logout",
            content = {
                HomeScreen(context)
            }
        )
    }

    Screen( Screen.TicketList ) {
        LogoutButton(
            context = context,
            buttonText = "Back",
            content = {
                TicketList(context)
            }
        )
    }

    Screen( Screen.RegisterTicket ) {
        LogoutButton(
            context = context,
            buttonText = "Back",
            content = {
                TicketRegistration(context)
            }
        )
    }

}

@Composable
private fun LogoutButton(
    content : @Composable () -> Unit ,
    context: Context ,
    buttonText: String
) {
    Scaffold (
        topBar = {
            Box( Modifier.wrapContentSize().padding(20.dp) ) {
                Box( Modifier
                    .width(100.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colors.primary)
                    .padding(5.dp)
                    .clickable(onClick = {
                        context.navigationController.back()
                    }) ,
                    contentAlignment = Alignment.Center
                ) {
                    Text( buttonText ,color = MaterialTheme.colors.onPrimary)
                }
                Spacer( Modifier.height(30.dp) )
            }
        }
    ) {
        Box(Modifier.fillMaxSize().padding(it) , content = {content()} )
    }
}
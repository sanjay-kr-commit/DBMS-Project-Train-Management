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
import dbms.project.screen.HomeScreen
import dbms.project.screen.LoginOrRegisterScreen
import dbms.project.screen.MainScreen
import dbms.project.screen.UserList

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
            content = {
                HomeScreen(context)
            }
        )
    }

}

@Composable
private fun LogoutButton(
    content : @Composable () -> Unit ,
    context: Context
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
                    Text( "Logout" ,color = MaterialTheme.colors.onPrimary)
                }
                Spacer( Modifier.height(30.dp) )
            }
        }
    ) {
        Box(Modifier.fillMaxSize().padding(it) , content = {content()} )
    }
}
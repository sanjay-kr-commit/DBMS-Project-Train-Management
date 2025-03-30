package dbms.project

import androidx.compose.runtime.Composable
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
        HomeScreen(context)
    }

}

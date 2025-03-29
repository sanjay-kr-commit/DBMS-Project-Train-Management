package dbms.project

import androidx.compose.runtime.Composable
import dbms.project.navigation.CreateScreenCatalog
import dbms.project.navigation.NavigationController
import dbms.project.navigation.Screen
import dbms.project.screen.MainScreen

data class Context(
    val screen: String = "" ,
)

@Composable
fun Navigator(
    navigationController: NavigationController ,
    contextObj : Context = Context()
) = CreateScreenCatalog(
    navigationController = navigationController
) {
    Screen( Screen.MainScreen ) {
        MainScreen()
    }
}

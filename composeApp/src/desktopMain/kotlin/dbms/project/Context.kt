package dbms.project

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dbms.project.database.DriverFactory
import dbms.project.database.Login_credential
import dbms.project.navigation.NavigationController

data class Context(
    val navigationController: NavigationController = NavigationController( EntryScreen ) ,
    val trainDatabase: TrainDatabase = TrainDatabase(DriverFactory().createDriver()) ,
    var isDarkTheme : MutableState<Boolean> = mutableStateOf(true),
    var loginCredential : Login_credential? = null
)
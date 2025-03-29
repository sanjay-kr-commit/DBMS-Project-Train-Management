package dbms.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dbms.project.database.DriverFactory
import dbms.project.navigation.NavigationController

fun main() {

    path = System.getProperty("user.dir")+"/"
    inMemory = false

    val navigationController = NavigationController(
        EntryScreen
    )

    val driver = DriverFactory()
        .createDriver()

    val trainDatabase = TrainDatabase(driver)

    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Train Management",
        ) {
            Navigator(
                navigationController = navigationController
            )
        }
    }
}
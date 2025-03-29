package dbms.project

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dbms.project.database.DriverFactory
import dbms.project.navigation.NavigationController
import org.jetbrains.compose.reload.DevelopmentEntryPoint

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
            DevelopmentEntryPoint {
                Navigator(
                    navigationController = navigationController
                )
            }
        }
    }
}
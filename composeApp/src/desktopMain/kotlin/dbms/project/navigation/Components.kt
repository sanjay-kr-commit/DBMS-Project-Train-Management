package dbms.project.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import dbms.project.Screen
import dbms.project.navigation.BackLambdaStack
import kotlin.reflect.KProperty

// This Component file is made so
// That all the function that are needed to make navigation stack
// can be imported by one import statement
// i.e import tanoshi.multiplatform.shared.naviagtion.*

// All the Nav Controller Component

fun navController(
    startScreen: Screen ,
    stack : MutableSet<Screen> = mutableSetOf()
) : NavigationController = NavigationController( startScreen , stack )

@Composable
fun NavController (
    startScreen : Screen ,
    stack : MutableSet<Screen> = mutableSetOf()
) : MutableState<NavigationController> = rememberSaveable {
    mutableStateOf( NavigationController( startScreen, stack ) )
}
operator fun NavigationController.getValue( thisObj : Any? , kProperty: KProperty<*>) : NavigationController = this
infix fun NavigationController.backStackLambdaPush( lambda : BackLambdaStack.() -> Any ) = backLambdaStackObject.push(lambda)
fun NavigationController.backStackLambdaPeek() = backLambdaStackObject.peek()
fun NavigationController.backStackLambdaPop() = backLambdaStackObject.pop()
val NavigationController.back : Unit
    get() {
        back()
    }

// All The Navigation Host Component
@Composable
fun CreateScreenCatalog(
    navigationController: NavigationController ,
    screen : @Composable NavigationHost.NavigationGraphBuilder.() -> Unit
) = NavigationHost( navigationController , screen ).also { it.renderView() }

@Composable
fun NavigationHost.NavigationGraphBuilder.Screen(
    route: Screen,
    content: @Composable () -> Unit
) {
    AnimatedVisibility( navigationController.currentScreen == route ,
        enter = fadeIn() , exit = fadeOut()
        , content = {content()} )
}
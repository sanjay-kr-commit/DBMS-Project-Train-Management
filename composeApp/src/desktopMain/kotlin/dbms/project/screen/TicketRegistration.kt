package dbms.project.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dbms.project.Context
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun TicketRegistration(
    context : Context
) {
    val dataStore : Array<MutableList<Pair<String,MutableState<String>>>> = remember { Array(listOfScreens.size) { mutableStateListOf() } }
    var activeIndex by remember { mutableStateOf(0) }
    Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
        listOfScreens.forEachIndexed { index, s ->
            if ( activeIndex == index ) s(context, { activeIndex++ } , { activeIndex-- } , dataStore[index] )
        }
    }
    DisposableEffect(null) {
        onDispose {
            println( "Exited $activeIndex : ${listOfScreens.size}" )
            if ( activeIndex < listOfScreens.size-1 ) {
                println( "Ticket Registration Cancelled" )
                GlobalScope.launch {
                    processTransaction.forEach {
                        it.invoke(context)
                    }
                }
            }
        }
    }
}

private val listOfScreens : List<@Composable (context:Context,nextButton:()->Unit,prevButton:()->Unit,dataStore : MutableList<Pair<String,MutableState<String>>>) -> Unit> = listOf(
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, showPrevButton = false , entries = listOf(
            "Train_code",
            "Train_name",
            "Distance",
            "Frequency",
            "Start_station_code",
            "End_station_code",
            "Start_time",
            "End_time"
        ), name = "Train Name")
    }
    ,  { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Zone_id", "Zone_name"
        ) , name = "Zone Id")
    } ,{ context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Station_code", "Station_name"
        ) , name = "Station : From")
    } ,{ context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Station_code", "Station_name"
        ) , name = "Station : To")
    } ,
    { context , _ , prevButton , _ ->
        Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
            Column {
                Button( {
                    context.navigationController.back()
                } ) {
                    Text( "Ticket Registration Complete" )
                }
                Button( {
                    prevButton()
                } ) {
                    Text( "Prev" )
                }
            }
        }
    }
)

private val processTransaction : List<(context:Context)->Unit> = listOf(
    {

    } ,
    {

    }
)

@Composable
private fun generic(
    context: Context,
    nextButton : () -> Unit ,
    prevButton : () -> Unit ,
    dataStore : MutableList<Pair<String,MutableState<String>>> ,
    entries : List<String> ,
    showPrevButton : Boolean = true,
    showNextButton : Boolean = true,
    name : String
) {

    Scaffold (
        topBar = {
            Box( Modifier.fillMaxWidth() , contentAlignment = Alignment.Center ) {
                Text( name )
            }
        },
        bottomBar = {
            Row {
                if ( showPrevButton ) Button(
                    onClick = prevButton ,
                    modifier = Modifier.padding( start = 10.dp , bottom = 5.dp)
                ) {
                    Text( "Prev" )
                }
                if ( showNextButton ) Button(
                    onClick = nextButton ,
                    modifier = Modifier.padding( start = 10.dp , bottom = 5.dp)
                ) {
                    Text( "Next" )
                }
            }
        }
    ){ it ->
        LazyColumn( Modifier.fillMaxSize().padding( it ), verticalArrangement = Arrangement.Center ) {
            dataStore.forEach { field ->
                item {
                    TextField(
                        value = field.second.value,
                        onValueChange = { field.second.value = it },
                        modifier = Modifier.fillMaxWidth()
                            .padding( 10.dp )
                            .clip(RoundedCornerShape(2.dp)) ,
                        label = { Text(field.first) },
                    )
                }
            }

        }
    }

    LaunchedEffect(null) {
        println( "Fill Default value of TrainInsert" )
        if ( dataStore.isEmpty() ) {
            entries.forEach {
                dataStore.add(it to mutableStateOf(""))
            }
        }
    }

}
package dbms.project.screen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.IntOffset
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
    var previousIndex by remember { mutableStateOf(0) }
    var activeIndex by remember { mutableStateOf(0) }
    Box( Modifier.fillMaxSize() , contentAlignment = Alignment.Center ) {
        listOfScreens.forEachIndexed { index, screen ->
            AnimatedVisibility( activeIndex == index ,
                enter = if ( previousIndex < activeIndex ) slideInHorizontally( initialOffsetX = { it * 2 } ) else slideInHorizontally( initialOffsetX = { -it }) ,
                exit = if ( previousIndex < activeIndex ) slideOutHorizontally( targetOffsetX = { -it }) else slideOutHorizontally( targetOffsetX = { it * 2 }) ,
            ) {
                screen(context, {
                    previousIndex = activeIndex++
                } , {
                    previousIndex = activeIndex--
                } , dataStore[index] )
            }
        }
    }
    Row ( Modifier.fillMaxSize().padding( 15.dp ) , horizontalArrangement = Arrangement.Center , verticalAlignment = Alignment.Bottom ) {
        listOfScreens.forEachIndexed { index, _ ->
            AnimatedVisibility( index == activeIndex ) {
                Text( "*" , color = MaterialTheme.colors.primary )
            }
            AnimatedVisibility( index != activeIndex ) {
                Text( "*" , color = MaterialTheme.colors.secondary )
            }
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
    // Train
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
    // Zone Id
    ,  { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Zone_id", "Zone_name"
        ) , name = "Zone Id")
    } ,
    // Station From
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Station_code", "Station_name"
        ) , name = "Station : From")
    } ,
    // Station To
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Station_code", "Station_name"
        ) , name = "Station : To")
    } ,
    // Class
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Class_id", "Class_code", "Seat_per_coach"
        ) , name = "Class")
    } ,
    // Ticket Reservation
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "PNR_no", "Train_code", "From_date", "To_date", "From_Km", "To_Km", "From_Station", "To_Station", "Login_id"
        ) , name = "Ticket Reservation")
    } ,
    // Pax Info
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "PAX_name", "PAX_age", "PAX_sex", "Seat_no", "Login_id"
        ) , name = "Pax Info")
    } ,
    // Refund Rule
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "From_time", "To_time", "Refundable_amt", "Login_id"
        ) , name = "Refund Rule")
    } ,
    // Pay Info
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Payment_id", "Inst_type", "Inst_amt", "Pay_mode", "Amount", "Pay_date", "PNR_no"
        ) , name = "Pay Info")
    } ,
    // Train Fare
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Train_code", "Class_id", "From_Km", "To_Km", "From_Date", "To_Date", "Fare"
        ) , name = "Train Fare")
    } ,
    // Seat Availability
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Train_code", "Details_id", "No_of_seats"
        ) , name = "Seat Availability")
    } ,
    // Via Details
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Train_code", "Via_station_code", "Via_station_name", "Reach_time", "Km_from_origin"
        ) , name = "Via Details")
    } ,
    // Train Class
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Train_code", "Class_id"
        ) , name = "Train Class")
    } ,
    // Train Station
    { context , nextButton , prevButton , dataStore ->
        generic(context,nextButton,prevButton,dataStore, entries = listOf(
            "Train_code", "Station_code"
        ) , name = "Train Station")
    } ,
    // End Page
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
            Row ( modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween ) {
                if ( showPrevButton ) Button(
                    onClick = prevButton ,
                    modifier = Modifier
                        .padding( start = 10.dp , end = 10.dp , bottom = 5.dp)
                ) {
                    Text( "Prev" )
                }
                if ( showNextButton ) Button(
                    onClick = nextButton ,
                    modifier = Modifier.padding( start = 10.dp , end = 10.dp , bottom = 5.dp)
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
        if ( dataStore.isEmpty() ) {
            println( "Fill Default value of \"$name\"" )
            entries.forEach {
                dataStore.add(it to mutableStateOf(""))
            }
        }
    }

}
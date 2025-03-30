package dbms.project.screen

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
import dbms.project.Context

@Composable
fun HomeScreen(
    context : Context
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
        Box(Modifier.fillMaxSize().padding(it)) {
            Text("Home Screen : Logged In")
        }
    }
}

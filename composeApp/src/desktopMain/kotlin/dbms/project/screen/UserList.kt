package dbms.project.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dbms.project.Context

@Composable
fun UserList(
    context : Context
) {
    val userList = remember { context.trainDatabase.loginCredentialQueries.read().executeAsList() }
    Box( modifier = Modifier.fillMaxSize().padding( top = 70.dp )
        .padding( start = 25.dp ) , contentAlignment = Alignment.TopStart ) {
        LazyColumn {
            userList.forEach { ( name , _ ) ->
                item {
                    Text( name )
                }
            }
        }
    }
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
            Text( "Back" ,color = MaterialTheme.colors.onPrimary)
        }
        Spacer( Modifier.height(30.dp) )

    }
}
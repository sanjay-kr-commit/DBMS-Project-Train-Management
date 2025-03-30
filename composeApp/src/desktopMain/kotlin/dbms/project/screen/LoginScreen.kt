package dbms.project.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dbms.project.Context
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun LoginOrRegisterScreen(
    context : Context ,
    login : Boolean
) {

    var loginId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    Scaffold(
        modifier = Modifier.fillMaxSize() ,
        bottomBar = {
            Text(message, modifier = Modifier.padding(8.dp))
        }
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(it), contentAlignment = Alignment.Center) {
            Column {
                TextField(
                    value = loginId,
                    onValueChange = { loginId = it },
                    label = { Text("Login") },
                    singleLine = true,
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                if ( login ) Button(onClick = {
                    message = "Looking got account"
                    val user = context.trainDatabase.loginCredentialQueries.findUser(
                        loginId
                    ).executeAsOneOrNull()
                    when {
                        user == null -> {
                            message = "User Does not exist"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        user.Login_id == loginId && user.Password != password -> {
                            message = "Wrong password"
                            GlobalScope.launch {
                                delay(2000)
                                message = "Wrong password"
                            }
                        }
                        user.Login_id == loginId && user.Password == password -> {
                            message = "Credentials Matched"
                            context.loginId = loginId.trim()
                            context.password = password
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                    }
                }, modifier = Modifier.wrapContentSize()) {
                    Text("Login")
                } else Button(onClick = {
                    when {
                        loginId.isEmpty() -> {
                            message = "Login Id is Empty"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        loginId.trim().contains(" ") -> {
                            message = "Login Id cannot contains spaces"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        context.trainDatabase.loginCredentialQueries.findUser(loginId.trim()).executeAsOneOrNull() != null -> {
                            message = "User Already exists"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        password.isEmpty() -> {
                            message = "Password is empty"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        password.trim().contains(" ") -> {
                            message = "Password cannot contains spaces"
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                        else -> {
                            context.loginId = loginId.trim()
                            context.password = password
                            try {
                                context.trainDatabase.loginCredentialQueries.insert(
                                    loginId.trim(),password
                                )
                                message = "Done"
                            } catch ( _ : Exception ) {
                                message = "Something went wrong"
                            }
                            GlobalScope.launch {
                                delay(2000)
                                message = ""
                            }
                        }
                    }
                }, modifier = Modifier.wrapContentSize()) {
                    Text("Register")
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
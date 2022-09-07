package com.gamecrawl.crudfirestore.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gamecrawl.crudfirestore.util.SharedViewModel
import com.gamecrawl.crudfirestore.util.UserData

@Composable
fun AddDataScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    var userId: String by remember {
        mutableStateOf("")
    }
    var name: String by remember {
        mutableStateOf("")
    }
    var profession: String by remember {
        mutableStateOf("")
    }
    var age: String by remember {
        mutableStateOf("")
    }
    var ageInt: Int by remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Add Data Screen")
        Row(
            modifier = Modifier
                .padding(start = 15.dp, end = 15.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back button")
            }
        }
        Column(
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp, bottom = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(value = userId, onValueChange = {
                userId = it
            }, label = { Text(text = "UserId") })
            OutlinedTextField(value = name, onValueChange = {
                name = it
            }, label = { Text(text = "Name") })
            OutlinedTextField(value = profession, onValueChange = {
                profession = it
            }, label = { Text(text = "Profession") })
            OutlinedTextField(value = age, onValueChange = {
                age = it
                ageInt = age.toInt()
            }, label = { Text(text = "Age") })

            //Save Button
            Button(onClick = {
                sharedViewModel.saveData(
                    UserData(userId, name, profession, ageInt),
                    context = context
                )
            }) {
                Text(text = "Save Data")
            }

        }
    }

}
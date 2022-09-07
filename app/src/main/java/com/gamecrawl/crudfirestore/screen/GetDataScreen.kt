package com.gamecrawl.crudfirestore.screen

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gamecrawl.crudfirestore.util.SharedViewModel
import com.gamecrawl.crudfirestore.util.UserData
import androidx.compose.foundation.layout.*
import androidx.compose.material.*

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp


@Composable
fun GetDataScreen(navController: NavController, sharedViewModel: SharedViewModel) {


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

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Get Data Screen 2")
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
                .padding(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceEvenly) {
                OutlinedTextField(modifier = Modifier.width(100.dp),value = userId, onValueChange = {
                    userId = it
                }, label = { Text(text = "UserId") })
                //button to get data
                Button(modifier = Modifier.width(100.dp),onClick = {
                    sharedViewModel.retrieveData(userId = userId, context = context) {
                        name = it.name
                        profession = it.profession
                        age = it.age.toString()
                    }
                }) {
                    Text(text = "Get Data")
                }
            }
            

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
                    UserData(userId, name, profession, age.toInt()),
                    context = context
                )
            }) {
                Text(text = "Update Data")
            }

            //delete button
            Button(onClick = {
                sharedViewModel.deleteData(userId = userId, context = context, navController = navController)
            }) {
                Text(text = "Delete Data")
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
    }



}
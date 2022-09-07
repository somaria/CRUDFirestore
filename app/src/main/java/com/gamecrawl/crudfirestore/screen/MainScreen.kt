package com.gamecrawl.crudfirestore.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gamecrawl.crudfirestore.Screens
import com.gamecrawl.crudfirestore.util.SharedViewModel

@Composable
fun MainScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    Column(
        modifier = Modifier
            .padding(start = 50.dp, end = 50.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text("Main Screen")
        //get user data button
        Button(
            modifier = Modifier.fillMaxWidth(), onClick = {
                navController.navigate(Screens.GetDataScreen.route)
            }) {
            Text(text = "Get User Data")
        }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { navController.navigate(Screens.AddDataScreen.route) }) {
            Text(text = "Add Data Screen")
        }

    }

}
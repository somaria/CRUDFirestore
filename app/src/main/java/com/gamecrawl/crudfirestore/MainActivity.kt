package com.gamecrawl.crudfirestore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gamecrawl.crudfirestore.screen.AddDataScreen
import com.gamecrawl.crudfirestore.screen.GetDataScreen
import com.gamecrawl.crudfirestore.screen.MainScreen
import com.gamecrawl.crudfirestore.ui.theme.CRUDFirestoreTheme
import com.gamecrawl.crudfirestore.util.SharedViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

sealed class Screens(val route: String) {
    object MainScreen : Screens("main_screen")
    object GetDataScreen : Screens("get_data_screen")
    object AddDataScreen : Screens("add_data_screen")
}

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CRUDFirestoreTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    navController = rememberNavController()
                    NavGraph(navController = navController, sharedViewModel = sharedViewModel)
                }
            }
        }
    }
}

@Composable
fun NavGraph(navController: NavHostController, sharedViewModel: SharedViewModel) {
    NavHost(navController = navController, startDestination = Screens.MainScreen.route) {
        composable(Screens.MainScreen.route) {
            MainScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(Screens.GetDataScreen.route) {
            GetDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
        composable(Screens.AddDataScreen.route) {
            AddDataScreen(navController = navController, sharedViewModel = sharedViewModel)
        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CRUDFirestoreTheme {
        Greeting("Android")
    }
}
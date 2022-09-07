package com.gamecrawl.crudfirestore.util

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


data class UserData(
    var userId: String = "",
    var name: String = "",
    var profession: String = "",
    var age: Int = 0
)

class SharedViewModel() : ViewModel() {

    fun saveData(userData: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val db = Firebase.firestore.collection("users").document(userData.userId)

        try {
            db.set(userData)
                .addOnSuccessListener {
                    Toast.makeText(
                        context,
                        "Data saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("TAG", "Data saved successfully")
                }
        } catch (e: Exception) {
            //toast
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(userId: String, context: Context, data: (UserData) -> Unit) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = Firebase.firestore.collection("users").document(userId)

            try {
                db.get()
                    .addOnSuccessListener {
                        if (it.exists()) {
                            val userData = it.toObject(UserData::class.java)
                            data(userData!!)
                        } else {
                            Toast.makeText(
                                context,
                                "No data found",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } catch (e: Exception) {
                //toast
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    fun deleteData(
        userId: String,
        context: Context,
        navController: NavController,
    ) =
        CoroutineScope(Dispatchers.IO).launch {
            val db = Firebase.firestore.collection("users").document(userId)

            try {
                db.delete()
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Data deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.popBackStack()
                    }
            } catch (e: Exception) {
                //toast
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

}
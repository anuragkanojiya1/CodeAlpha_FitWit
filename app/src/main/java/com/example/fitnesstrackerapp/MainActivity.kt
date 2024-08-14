package com.example.fitnesstrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.Navigation.NavGraph
import com.example.fitnesstrackerapp.ui.theme.FitnessAppTheme
import com.example.fitnesstrackerapp.ui.theme.FitnessTrackerAppTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var navController : NavController

    val auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db: FirebaseFirestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            FitnessAppTheme {
                val navController = rememberNavController()

                NavGraph(navController = navController)
            }
        }
    }
}

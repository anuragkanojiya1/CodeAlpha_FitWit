package com.example.fitnesstrackerapp.Navigation

import GoalScreen
import WorkoutScreen
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.fitnesstrackerapp.Screens.AppScreen
import com.example.fitnesstrackerapp.Screens.Login
import com.example.fitnesstrackerapp.Screens.MainScreen
import com.example.fitnesstrackerapp.Screens.SignUpScreen
import com.example.fitnesstrackerapp.ViewModels.FitnessViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavGraph(navController: NavController) {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()

    NavHost(navController, startDestination = Screen.LogIn.route) {

        composable(Screen.LogIn.route) {
            Login(navController = navController, auth = auth)
        }
        composable(Screen.SignUp.route){
            SignUpScreen(navController = navController, auth = auth)
        }
        composable(Screen.AppScreen.route){
            AppScreen(navController = navController, fitnessViewModel = FitnessViewModel())
        }
        composable(
            route = "mainscreen/{name}",
            arguments = listOf(navArgument("name") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name")
            MainScreen(navController = navController, name = name, fitnessViewModel = FitnessViewModel())
        }
        composable(Screen.WorkoutScreen.route){
            WorkoutScreen(navController)
        }
        composable(Screen.GoalScreen.route){
            GoalScreen(navController)
        }



//        composable(Screen.AddDataScreen.route){
//            AddDataScreen(navController = navController, sharedViewModel = sharedViewModel)
//        }
    }
}
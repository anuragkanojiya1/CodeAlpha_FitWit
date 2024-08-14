package com.example.fitnesstrackerapp.Navigation

sealed class Screen(val route: String){
    object LogIn: Screen("login")
    object SignUp: Screen("signup")
    object AppScreen: Screen("appscreen")
    object MainScreen: Screen("mainscreen/{name}")
    object WorkoutScreen: Screen("workoutscreen")
    object GoalScreen: Screen("goalscreen")
}
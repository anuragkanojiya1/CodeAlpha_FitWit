package com.example.fitnesstrackerapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fitnesstrackerapp.Navigation.Screen
import com.example.fitnesstrackerapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.coroutineContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF00BCD4), Color(0xFF1E88E5))
    )

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .paddingFromBaseline(top = 10.dp, bottom = 10.dp),
//            color = MaterialTheme.colorScheme.background
//        ) {
//            Box {
//                com.example.myhealthpassport.SignInSignUp.AnimatedPatientSignIn(modifier = Modifier
//                    .size(500.dp, 400.dp)
//                    .align(Alignment.Center)
//                    // .scale(scaleX = 1.3f, scaleY = 1.6f)
//                )
//            }
//        }

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 10.dp, bottom = 10.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box {
                AnimatedLogIn(modifier = Modifier
                    .size(500.dp, 400.dp)
                    .align(Alignment.Center)
                    // .scale(scaleX = 1.3f, scaleY = 1.6f)
                )
            }
        }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Email") },
            singleLine = true,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "Email icon", tint = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            ),
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password icon", tint = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            ),
        )
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
        ExtendedFloatingActionButton(onClick = {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navController.navigate(Screen.AppScreen.route)
                    } else {
                        errorMessage = task.exception?.message
                    }
                }
        },
            modifier = Modifier
                .padding(16.dp)
                .background(gradient, shape = RoundedCornerShape(8.dp))
                .height(50.dp),

            ) {
            Text(text = "Log In", color = Color.White)
        }
        TextButton(onClick = { navController.navigate(Screen.SignUp.route) }) {
            Text(text = "Don't have an account? Sign Up")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LogInPreview(){
    Login(navController = rememberNavController(), auth = Firebase.auth)
}

@Composable
fun AnimatedLogIn(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation1
        )
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )


    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}
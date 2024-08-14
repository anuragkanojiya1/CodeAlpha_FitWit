package com.example.fitnesstrackerapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.fitnesstrackerapp.R
import com.example.fitnesstrackerapp.ViewModels.FitnessViewModel
import com.example.fitnesstrackerapp.data.PersonalData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(navController: NavController, fitnessViewModel: FitnessViewModel){

    var name by remember {
        mutableStateOf("")
    }
    var age by remember {
        mutableStateOf("")
    }
    var weight by remember {
        mutableStateOf("")
    }
    var height by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF00BCD4), Color(0xFF1E88E5))
    )

    val scrollView = rememberScrollState()

    Column {
        TopAppBar(title = {
            Text(text = "FitWit",
                fontWeight = FontWeight.W500,
                fontSize = 24.sp)
        },
            colors = TopAppBarColors(containerColor = Color.Black, scrolledContainerColor = Color.Black, navigationIconContentColor = Color.White, titleContentColor = Color.White, actionIconContentColor = Color.White),

            )
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollView)
        .padding(10.dp)) {

        Text(
            text = "Enter your details",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Light,
            color = Color.Black,
            fontSize = 28.sp,
            textDecoration = TextDecoration.Underline
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .paddingFromBaseline(top = 10.dp, bottom = 10.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            Box {
                AnimatedAppScreen(
                    modifier = Modifier
                        .size(500.dp, 284.dp)
                        .align(Alignment.Center)
                    // .scale(scaleX = 1.3f, scaleY = 1.6f)
                )
            }
        }

        OutlinedTextField(
            value = name, onValueChange = { name = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Name") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Name icon",
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            )
        )
        OutlinedTextField(
            value = age, onValueChange = { age = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Age") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = "Age icon",
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            )
        )
        OutlinedTextField(
            value = weight, onValueChange = { weight = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Weight in kg") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Weight icon",
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            )
        )
        OutlinedTextField(
            value = height, onValueChange = { height = it },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            label = { Text(text = "Height in cm") },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Height icon",
                    tint = Color.Black
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Blue,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.Blue,
                focusedTextColor = Color.Black,
                focusedPlaceholderColor = Color.Gray,
                errorTextColor = Color.Red
            )
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(top = 30.dp, bottom = 40.dp)
                .align(Alignment.CenterHorizontally)
                .background(brush = gradient, shape = RoundedCornerShape(8.dp)),
            onClick = {
                val personalData = PersonalData(
                    name = name,
                    age = age,
                    weight = weight,
                    height = height
                )
                fitnessViewModel.savePersonalData(personalData = personalData, context = context)
                navController.navigate("mainscreen/{$name}")
            }
        ) {
            Text(text = "Save Data", color = Color.White)
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun AppScreenPreview(){
    AppScreen(navController = rememberNavController(), fitnessViewModel = FitnessViewModel())
}

@Composable
fun AnimatedAppScreen(modifier: Modifier = Modifier) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.animation3
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
package com.example.fitnesstrackerapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.Navigation.Screen
import com.example.fitnesstrackerapp.R
import com.example.fitnesstrackerapp.ViewModels.FitnessViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, name: String?, fitnessViewModel: FitnessViewModel) {

    val context = LocalContext.current
    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF00BCD4), Color(0xFF1E88E5))
    )

    var weight by remember {
        mutableStateOf("")
    }
    var height by remember {
        mutableStateOf("")
    }
    var bmi by remember {
        mutableStateOf(0f)
    }
    var originalname by remember {
        mutableStateOf("ak")
    }

        fitnessViewModel.retrievePersonalData(
            name = originalname,
            context = context
        ){ data ->
            weight = data.weight
            height = data.height
        }

    if (weight.isNotEmpty() && height.isNotEmpty()) {
        bmi = calculateBMI(weight.toFloat(), height.toFloat())
    }

    val gradient2 = Brush.linearGradient(
        colors = listOf(
            Color(0xFF181818), Color(0xFF2E2E2E)
        )
    )


    Column {
        TopAppBar(title = {
            Text(text = "FitWit",
                fontWeight = FontWeight.W500,
                fontSize = 24.sp)
        },
            colors = TopAppBarColors(containerColor = Color.Black, scrolledContainerColor = Color.Black, navigationIconContentColor = Color.White, titleContentColor = Color.White, actionIconContentColor = Color.White),

        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {

            Row(modifier = Modifier.fillMaxWidth()) {

                Column(
                    modifier = Modifier
                        .align(Alignment.Top)
                        .padding(8.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.gym),
                        contentDescription = "Profile photo",
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .size(180.dp)
                            .align(Alignment.End)
                    )
                    Text(
                        text = "Welcome, $originalname!",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.End),
                        fontStyle = FontStyle.Italic,
                        fontSize = 28.sp,
                        textDecoration = TextDecoration.Underline
                    )
                }
                Column {

                    ExtendedFloatingActionButton(
                        onClick = { navController.navigate(Screen.WorkoutScreen.route) },
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "View Workout",
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White
                        )
                    }

                    ExtendedFloatingActionButton(modifier = Modifier.padding(8.dp),
                        onClick = { navController.navigate(Screen.GoalScreen.route) }) {
                        Text(
                            text = "View Goals",
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace,
                            color = Color.White
                        )
                    }
                }
            }

            Text(
                text = "Your Body Mass Index -",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp),
                fontSize = 24.sp
            )
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(320.dp)
                    .background(Color.White),
                colors = CardColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContainerColor = Color.White,
                    disabledContentColor = Color.White
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.gym2),
                    contentDescription = "gym2",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
                Text(
                    text = "B.M.I. : $bmi",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .weight(0.1f),
                    fontSize = 24.sp
                )
            }
        }
    }
}

// Helper function to calculate BMI
fun calculateBMI(weight: Float, height: Float): Float {
    return if (height > 0) {
        (weight * 10000) / (height * height)  // Assuming height is in cm
    } else {
        0f
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    MainScreen(navController = rememberNavController(), name = "", fitnessViewModel = FitnessViewModel())
}
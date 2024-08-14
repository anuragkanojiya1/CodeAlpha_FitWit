import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoalScreen(navController: NavController) {
    var goals by remember { mutableStateOf(listOf<String>()) }
    var currentGoal by remember { mutableStateOf("") }

    var gradient = Brush.horizontalGradient(
        colors = listOf(
            Color(0xFF00BCD4), Color(0xFF1E88E5)
        )
    )

    val scrollView = rememberScrollState()

    Column {
    TopAppBar(
        title = {
            Text(
                text = "FitWit",
                fontWeight = FontWeight.W500,
                fontSize = 24.sp
            )
        },
        colors = TopAppBarColors(
            containerColor = Color.Black,
            scrolledContainerColor = Color.Black,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollView)
            .padding(8.dp)
    ) {
        Text(
            text = "Goal Screen",
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 28.sp
        )

        Row(modifier = Modifier.padding(4.dp)) {
            OutlinedTextField(
                value = currentGoal,
                onValueChange = { currentGoal = it },
                modifier = Modifier.weight(1f)
            )
            ExtendedFloatingActionButton(
                onClick = {
                    if (currentGoal.isNotBlank()) {
                        goals = goals + currentGoal
                        currentGoal = ""
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text(text = "Add", color = Color.White)
            }
        }

        for ((index, goal) in goals.withIndex()) {
            Goal(goal = goal, onRemove = {
                goals = goals.filterIndexed { i, _ -> i != index }
            })
        }
    }
    }
}

@Composable
fun Goal(goal: String, onRemove: () -> Unit) {
    Row(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()) {

        Card(
            modifier = Modifier
                .padding(8.dp)
                .weight(1f)
                .align(Alignment.CenterVertically)
                .border(
                    width = 2.dp, brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF48cae4), Color(0xFF90e0ef))
                    ),
                    shape = RoundedCornerShape(8.dp)
                ),
            colors = CardColors(
                containerColor = Color.White,
                contentColor = Color.Black,
                disabledContentColor = Color.Black,
                disabledContainerColor = Color.Red
            )
        ) {
            Text(
                text = goal,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 16.sp,
                fontStyle = FontStyle.Normal
            )
        }

        ExtendedFloatingActionButton(
            onClick = { onRemove() },
            modifier = Modifier
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "✔", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GoalScreenPreview() {
    GoalScreen(rememberNavController())
}

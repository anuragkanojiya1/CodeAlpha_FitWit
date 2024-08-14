import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.fitnesstrackerapp.R
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseWithTimer(exerciseName: String, imageBitmap: ImageBitmap) {
    var timeInput by remember { mutableStateOf(TextFieldValue("")) }
    var timeInSeconds by remember { mutableStateOf(0L) }

    var gradient= Brush.horizontalGradient(
    colors = listOf(Color(0xFF48cae4), Color(0xFF90e0ef))
    )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.Start),
                text = exerciseName,
                fontWeight = FontWeight.Light,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = timeInput,
                onValueChange = {
                    timeInput = it
                    timeInSeconds = it.text.toLongOrNull() ?: 0L
                },
                label = { Text("Time (seconds)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            ) {

                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Gym",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 16.dp)
                        .size(164.dp)
                        .clip(shape = RoundedCornerShape(44.dp))
                )
                Timer(
                    totalTime = timeInSeconds * 1000L,
                    handleColor = Color.Green,
                    inactiveBarColor = Color.DarkGray,
                    activeBarColor = Color(0xFF37B900),
                    modifier = Modifier.size(170.dp)
                )
        }
    }
}

@Composable
fun Timer(
    totalTime: Long,
    handleColor: Color,
    inactiveBarColor: Color,
    activeBarColor: Color,
    modifier: Modifier = Modifier,
    initialValue: Float = 1f,
    strokeWidth: Dp = 5.dp
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    var value by remember { mutableStateOf(initialValue) }
    var currentTime by remember { mutableStateOf(totalTime) }
    var isTimerRunning by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = currentTime, key2 = isTimerRunning) {
        if (currentTime > 0 && isTimerRunning) {
            kotlinx.coroutines.delay(1000L)
            currentTime -= 1000L
            value = currentTime / totalTime.toFloat()
        }
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.onSizeChanged {
            size = it
        }
    ) {
        Canvas(modifier = modifier) {
            drawArc(
                color = inactiveBarColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeBarColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
            val center = Offset(size.width / 2f, size.height / 2f)
            val beta = (250f * value + 145f) * (PI / 180f).toFloat()
            val r = size.width / 2f
            val a = cos(beta) * r
            val b = sin(beta) * r
            drawPoints(
                listOf(Offset(center.x + a, center.y + b)),
                pointMode = PointMode.Points,
                color = handleColor,
                strokeWidth = (strokeWidth * 3f).toPx(),
                cap = StrokeCap.Round
            )
        }
        Text(
            text = (currentTime / 1000L).toString(),
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Button(
            onClick = {
                if (currentTime <= 0L) {
                    currentTime = totalTime
                    isTimerRunning = true
                } else {
                    isTimerRunning = !isTimerRunning
                }
            },
            modifier = Modifier.align(Alignment.BottomCenter),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (!isTimerRunning || currentTime <= 0L) {
                    Color.Green
                } else {
                    Color.Red
                }
            )
        ) {
            Text(
                text = if (isTimerRunning && currentTime >= 0L) "Stop"
                else if (!isTimerRunning && currentTime >= 0L) "Start"
                else "Restart"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreen(navController: NavController) {

    var gradient= Brush.horizontalGradient(
        colors = listOf(Color(0xFFe9c46a), Color(0xFFfcbf49))
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
                .padding(0.dp)
                .padding(bottom = 24.dp)
                .background(brush = gradient)
                .verticalScroll(scrollView)
        ) {

            Text(
                text = "Workouts",
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally),
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 28.sp
            )

            ExerciseWithTimer(
                exerciseName = "Bench-press",
                imageBitmap = ImageBitmap.imageResource(id = R.drawable.gym)
            )
            ExerciseWithTimer(
                exerciseName = "Push-up",
                imageBitmap = ImageBitmap.imageResource(id = R.drawable.gym5)
            )
            ExerciseWithTimer(
                exerciseName = "Cardio",
                imageBitmap = ImageBitmap.imageResource(id = R.drawable.gym6)
            )

//        ExerciseCompletionGraph(
//            completedExercises = listOf(
//                "Push-Ups" to 10,
//                "Plank" to 7,
//                "Cardio" to 5
//            ),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//                .padding(horizontal = 16.dp)
//        )
        }
    }
}

//@Composable
//fun ExerciseCompletionGraph(
//    completedExercises: List<Pair<String, Int>>,
//    modifier: Modifier = Modifier
//) {
//    val maxCompletion = completedExercises.maxOfOrNull { it.second } ?: 0
//
//    Canvas(modifier = modifier) {
//        val barWidth = size.width / (completedExercises.size * 2)
//        completedExercises.forEachIndexed { index, pair ->
//            val barHeight = (size.height * (pair.second / maxCompletion.toFloat()))
//            drawRect(
//                color = Color(0xFF37B900),
//                topLeft = Offset(x = barWidth * (index * 2 + 1), y = size.height - barHeight),
//                size = Size(width = barWidth, height = barHeight)
//            )
//            drawContext.canvas.nativeCanvas.apply {
//                drawText(
//                    pair.first,
//                    barWidth * (index * 2 + 1.5f),
//                    size.height - barHeight - 10f,
//                    android.graphics.Paint().apply {
//                        textSize = 30f
//                        textAlign = android.graphics.Paint.Align.CENTER
//                        color = android.graphics.Color.BLACK
//                    }
//                )
//            }
//        }
//    }
//}
//

@Preview(showBackground = true)
@Composable
fun WorkoutScreenPreview(){
    WorkoutScreen(navController = rememberNavController())
}
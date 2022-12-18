package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodelabTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyApp(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier) {
        if (shouldShowOnboarding) {
            OnBoardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var expanded by remember { mutableStateOf(false) }
    var extraPadding = if (expanded) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            Button(
                onClick = { expanded = !expanded },
                border = BorderStroke(width = 1.dp, color = Color.White)
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }

    }

}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) {"$it"}
) {
    LazyColumn(
        modifier = modifier
            .padding(vertical = 4.dp)
    ) {
        items(items = names) {
            name -> Greeting(name = name)
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basic Codelab!")
        Button(
            modifier = Modifier
                .padding(bottom = 24.dp),
            onClick = onContinueClicked ) {
            Text(text = "Continue")

        }
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicCodelabTheme {
        Greetings(modifier = Modifier.fillMaxSize())
    }
}

//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun OnBoardingPreview() {
//    BasicCodelabTheme {
//        OnBoardingScreen(onContinueClicked = {})
//    }
//}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    BasicCodelabTheme {
        MyApp(Modifier.fillMaxSize())
    }
}
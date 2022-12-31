package com.example.basiccodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

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
    var expanded by rememberSaveable { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                //アニメーション作成プロセスを自動化する。
                .animateContentSize()
        ) {
            Column(
                modifier = Modifier.weight(1f)
                // クラッシュしないようにパディングに負数が入らないようにする。
                //RpwにanimateContentSize修飾子を適用させて代用
//                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(text = name)
                if (expanded) Text(text = "お試しテキスト。")
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess
                    else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(id = (R.string.show_less))
                    } else {
                        stringResource(id = R.string.show_more)
                    }
                )
            }
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier, names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun OnBoardingScreen(
    onContinueClicked: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to the Basic Codelab!")
        Button(
            modifier = Modifier.padding(bottom = 24.dp), onClick = onContinueClicked
        ) {
            Text(text = "Continue")

        }
    }
}

@Preview(
    showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES, name = "Dark"
)
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
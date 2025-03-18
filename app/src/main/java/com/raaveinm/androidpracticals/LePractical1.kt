package com.raaveinm.androidpracticals

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raaveinm.androidpracticals.ui.theme.AndroidPracticalsTheme


// change preview to emulate a common width of a small phone, 320dp. Add a widthDp parameter

@Preview(showBackground = true)
@Composable
fun App(
    modifier: Modifier = Modifier,
    LazyColumnList: List<String> = List(1000){"$it"},
    names: List<String> = listOf("Java", "Kotlin")){

    var clicks = rememberSaveable { mutableIntStateOf(0) }
    var shouldShowOnboard by rememberSaveable { mutableStateOf(true) }
    var shouldShowLazy by remember { mutableStateOf(false) }

    AndroidPracticalsTheme {
        if (!shouldShowOnboard) {
            if (!shouldShowLazy) {
                Surface(
                    modifier = modifier,
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(modifier = modifier.padding(24.dp)) {
                        for (name in names) {
                            Greeting(name)
                        }
                    }
                    ClickCounter(clicker = {
                        clicks.intValue++
                        if (clicks.intValue == 10) {
                            shouldShowLazy = true
                        }else{
                            shouldShowLazy = false
                        }
                    }, clicks = clicks.intValue, modifier = modifier)
                }
            }else{
                LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
                    items(count = LazyColumnList.size) { index ->
                        val name = LazyColumnList[index]

                        LazyList(name = name)
                    }
                }
            }
        } else {
            OnboardingScreen(modifier = modifier, onContinueClicked = { shouldShowOnboard = false })
        }
    }
}



// Modifiers are used extensively in Compose
// replicating the following layout using the fillMaxWidth and padding modifiers.

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expanded by rememberSaveable { mutableStateOf(false) } // saving state using `remember`
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 14.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row (modifier = modifier.padding(24.dp)){
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)) {
                Text(text = "Hello ")
                Text(text = name,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ))

                if (expanded){
                    Text(
                        text = ("im so tired doing this"+"But its so interesting! ").repeat(2)
                    )
                }

            }
            IconButton(
                //modifier = modifier.align(Alignment.CenterVertically)
                onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    }
                )
            }
        }
    }
}


@Composable
fun ClickCounter(
    clicker: () -> Unit,
    clicks: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(10.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Click counter: Button Clicked $clicks times",
            color = Color.DarkGray
        )

        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            ),
            onClick = clicker
        ) {
            Text("Click Me")
        }
    }
}



@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier,

    ){
    Surface(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Gemini somehow know that im doing CodeLab practice",
                color = Color.LightGray, textAlign = TextAlign.Center)
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("He is spying on me")
            }
        }
    }
}

@Composable
fun LazyList(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp)) {
        Row (modifier = modifier.padding(24.dp)){
            Column(                modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello ")
                Text(text = name)

            }
            ElevatedButton(
                onClick = {expanded.value = !expanded.value}
            ){
                Text(if (expanded.value) "legend" else "i've greeted it 1000 times")
            }
        }
    }
}

/**
 * дорогой дневник. И не описать всей боли и унижений, что я испытал в этот день...
 *
 */
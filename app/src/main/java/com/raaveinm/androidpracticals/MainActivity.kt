package com.raaveinm.androidpracticals

import android.R.attr.contentDescription
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons.AutoMirrored.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raaveinm.androidpracticals.ui.theme.AndroidPracticalsTheme

/**
 * Kotlin and jetpack compose Start
 *
 * # **Theory**
 *
 * Compose apps transform data into UI by calling composable functions. If your data changes,
 * Compose re-executes these functions with the new data, creating an updated UI—this is
 * **called recomposition**. Compose also looks at what data is needed by an individual composable
 * so that it only needs to recompose components whose data has changed and skip recomposing those
 * that are not affected.
 *
 * Composable functions can executed in any order. Compose can automatically recognise, that some
 * UI elements have more priority than others, so it can render them first. For example, in multi
 * screen in tab layout, the main screen will automatically, but this execution could be changed.
 *
 * Composable functions can run in parallel.
 *
 * Recomposition skips as much as possible. Compose will recompose only the elements that have to
 * be updated. If some state will not need recomposition, it will be skipped. Even if the state
 * changes only one parameter (only text, (even if it had header,text,footer)) it will recompose
 * only that element(text for example upper).
 *
 * If parameter changes before recomposition will be done - it will be stopped and rerun with new
 * parameter.
 *
 * Composable functions are run frequently
 *
 * To save composable state it should be used `MutableState` and `remember`. they will
 * AUTOMATICALLY track changes.
 *
 * In compose there is three basic standard layout elements in Compose are Column, Row and Box.
 * They are Composable functions that take Composable content, so you can place items inside.
 * For example, each child inside of a Column will be placed vertically.
 *
 * In Composable functions, state that is read or modified by multiple functions should live in a
 * common ancestor—this process is called state hoisting. To hoist means to lift or elevate.
 *
 * Making state hoistable avoids duplicating state and introducing bugs, helps reuse composables,
 * and makes composables substantially easier to test. Contrarily, state that doesn't need to be
 * controlled by a composable's parent should not be hoisted. The source of truth belongs to whoever
 * creates and controls that state.
 *
 * If you run the app on a device, click on the buttons and then you rotate, the onboarding screen
 * is shown again. The `remember` function works only as long as the composable is kept in the
 * Composition. When you rotate, the whole activity is restarted so all state is lost. This also
 * happens with any configuration change and on process death.
 * Instead of using `remember` you can use `rememberSaveable`. This will save each state surviving
 * configuration changes (such as rotations) and process death.
 *
 * The `spring` spec does not take any time-related parameters. Instead it relies on physical
 * properties (damping and stiffness) to make animations more natural. Any animation created with
 * `animate*AsState` is interruptible. This means that if the target value changes in the middle of
 * the animation, `animate*AsState` restarts the animation and points to the new value.
 *
 * The `Text` composable in the example above sets a new `TextStyle`. You can create your own
 * `TextStyle`, or you can retrieve a theme-defined style by using `MaterialTheme.typography`, which
 * is preferred. This construct gives you access to the Material-defined text styles, such as
 * `displayLarge, headlineMedium, titleSmall, bodyLarge, labelMedium` etc. In your example, you use
 * the headlineMedium style defined in the theme.
 *
 * # **Important Information**
 *
 * - Setting a different value for the expanded variable won't make Compose detect it as a
 * state change so nothing will happen. But its possible to save state decelerated with `remember`
 *
 * - if you call the same composable from different parts of the screen you will create different
 * UI elements, each with its own version of the state. You can think of internal state as a private
 * variable in a class.
 *
 * - In Compose you don't hide UI elements. Instead, you simply don't add them to the composition,
 * so they're not added to the UI tree that Compose generates. You do this with simple conditional
 * Kotlin logic.
 *
 * - LazyColumn and LazyRow are equivalent to RecyclerView in Android Views
 *
 * # **Methods**
 *
 * `Arrangement` (within a `Column` or `Row`): Dictates how children are spaced out along the main
 * axis of the layout. `Arrangement.Center` within a Column centers **vertically**. `Arrangement.Center`
 * within a Row centers **horizontally**. It controls the overall positioning between the children.
 *
 * `Alignment` (within a `Column` or `Row`): Dictates how children are positioned along the cross
 * axis of the layout. `Alignment.CenterHorizontally` within a Column centers **horizontally**.
 * `Alignment.CenterVertically` within a Row centers **vertically**. It controls positioning
 * perpendicular to the main arrangement.
 *
 * The key is to remember that `Arrangement` is along the primary direction of the layout
 * (vertical for `Column`, horizontal for `Row`), and Alignment is perpendicular to that.
 * Your initial statement mixed up which was the main and cross axis for each layout.
 *
 * `shouldShowOnboarding` is using a `by` keyword instead of the `=`. This is a property delegate
 * that saves you from typing `.value` every time.
 *
 * Instead of letting `OnboardingScreen` mutate our state, it would be better to let it notify when
 * the user clicked on the button. By passing **callbacks down**. Callbacks are functions that are
 * passed as arguments to other functions and get executed when the event occurs.
 * add a function parameter to the onboarding screen defined as `onContinueClicked: () -> Unit`
 * so you can mutate the state from MyApp.
 *
 * the `animateDpAsState` composable  returns a State object whose `value` will continuously be
 * updated by the animation until it finishes. It takes a "target value" whose type is `Dp`
 *
 * `animateDpAsState` takes an optional `animationSpec` parameter that lets you customize the animation
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            AndroidPracticalsTheme {
                App(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

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

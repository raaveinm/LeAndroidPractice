package com.raaveinm.androidpracticals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raaveinm.androidpracticals.ui.theme.AndroidPracticalsTheme

/**
 * # UI
 *
 * ## Examples
 *
 * ### Scaffold
 *
 * Scaffold is a basic layout for arranging material components. In common - patterns, such as the
 * screen with a small top app bar and a floating action button.
 * ```
 * Surface(){
 *     Scaffold(
 *         topBar = {},
 *         content = {},
 *         bottomBar = {}
 *     )
 * }
 *```
 *
 * ### Surface
 * ```
 * Surface(
 *     color = MaterialTheme.colorScheme.surface //example
 *     shape = MaterialTheme.shapes.medium
 *     border = BorderStroke(1.dp, Color.Black) // or colorScheme
 *     shadowElevation = 5.dp
 *     tonalElevation = 5.dp
 * ){
 *     Text("Surface")
 * }
 * ```
 *
 * ### Modifiers
 *
 * ```
 * Text(
 *     text = "Hello World",
 *     Modifier.background(Color.Magenta)
 *         .size(200.dp, 30.dp)
 *         .padding(5.dp)
 *         .alpha(0.2f)
 *         .clickable { TODO:"onClick" }
 * )
 * ```
 *
 * ### Row
 *
 * ```
 * fun RowExample(){
 *     Row(
 *         Modifier.fillMaxWidth()
 *             .padding(16.dp) //etc
 *     ){
 *         Image(pic.image)
 *         Text("sample")
 *         RadioButton(/*...*/)
 *     }
 * }
 * ```
 */
class LePractical2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPracticalsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidPracticalsTheme {
        Greeting2("Android")
    }
}
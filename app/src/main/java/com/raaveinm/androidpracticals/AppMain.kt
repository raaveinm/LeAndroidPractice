package com.raaveinm.androidpracticals

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raaveinm.androidpracticals.ui.theme.AndroidPracticalsTheme

@Composable
fun AppMain (modifier: Modifier = Modifier){
    var count by remember { mutableStateOf<Int>(0) }
    //CoffeePie(modifier)
    AndroidPracticalsTheme {
        Column (modifier=modifier){
            StatelessCounter(count, onIncrement = { count++ }, modifier)
            StatefulCounter(modifier)
        }
    }
}

@Composable
fun CoffeePie(modifier: Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun StatelessCounter(count: Int, onIncrement: () -> Unit, modifier: Modifier = Modifier){
    Row(modifier = modifier
        .padding(32.dp)
        .fillMaxWidth()){
        Text("Button pressed ${count} times")
        Button(
            onClick = onIncrement,
            modifier = modifier.padding(top = 4.dp)
        ) {
            Text(text = "butt")
        }
    }
}

@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf<Int>(0) }
    StatelessCounter(count, { count++ }, modifier)
}
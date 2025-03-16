# Kotlin and jetpack compose Start

# **Theory**

Compose apps transform data into UI by calling composable functions. If your data changes,
Compose re-executes these functions with the new data, creating an updated UI—this is
**called recomposition**. Compose also looks at what data is needed by an individual composable
so that it only needs to recompose components whose data has changed and skip recomposing those
that are not affected.

Composable functions can executed in any order. Compose can automatically recognise, that some
UI elements have more priority than others, so it can render them first. For example, in multi
screen in tab layout, the main screen will automatically, but this execution could be changed.

Composable functions can run in parallel.

Recomposition skips as much as possible. Compose will recompose only the elements that have to
be updated. If some state will not need recomposition, it will be skipped. Even if the state
changes only one parameter (only text, (even if it had header,text,footer)) it will recompose
only that element(text for example upper).

If parameter changes before recomposition will be done - it will be stopped and rerun with new 
parameter.

Composable functions are run frequently

To save composable state it should be used `MutableState` and `remember`. they will
AUTOMATICALLY track changes.

In compose there is three basic standard layout elements in Compose are Column, Row and Box.
They are Composable functions that take Composable content, so you can place items inside.
For example, each child inside of a Column will be placed vertically.

In Composable functions, state that is read or modified by multiple functions should live in a
common ancestor—this process is called state hoisting. To hoist means to lift or elevate.

Making state hoistable avoids duplicating state and introducing bugs, helps reuse composables,
and makes composables substantially easier to test. Contrarily, state that doesn't need to be
controlled by a composable's parent should not be hoisted. The source of truth belongs to whoever
creates and controls that state.

If you run the app on a device, click on the buttons and then you rotate, the onboarding screen
is shown again. The `remember` function works only as long as the composable is kept in the
Composition. When you rotate, the whole activity is restarted so all state is lost. This also
happens with any configuration change and on process death.
Instead of using `remember` you can use `rememberSaveable`. This will save each state surviving
configuration changes (such as rotations) and process death.

The `spring` spec does not take any time-related parameters. Instead it relies on physical
properties (damping and stiffness) to make animations more natural. Any animation created with
`animate*AsState` is interruptible. This means that if the target value changes in the middle of
the animation, `animate*AsState` restarts the animation and points to the new value.

The `Text` composable in the example above sets a new `TextStyle`. You can create your own
`TextStyle`, or you can retrieve a theme-defined style by using `MaterialTheme.typography`, which
is preferred. This construct gives you access to the Material-defined text styles, such as
`displayLarge, headlineMedium, titleSmall, bodyLarge, labelMedium` etc. In your example, you use
the headlineMedium style defined in the theme.


For a `Column`, you decide how its children should be aligned horizontally. The options are:
`Start` * `CenterHorizontally` * `End`

For a `Row`, you set the vertical alignment. The options are similar to those of the Column:
`Top` * `CenterVertically` * `Bottom`

For a `Box`, you combine both horizontal and vertical alignment. The options are:
`TopStart` * `TopCenter` * `TopEnd` * `CenterStart` * `Center` * `CenterEnd` * `BottomStart`
`BottomCenter` * `BottomEnd`

For a `Column`, the cross-axis is the horizontal axis, while for a `Row`, the cross-axis is the 
vertical axis.However, we can also make a decision on how to place child composables on a container's 
main axis (horizontal for `Row`, vertical for `Column`). 
For a `Row`, you can choose the following arrangements:

![img.png](img.png)

And for a `Column`

![img_1.png](img_1.png)

# **Important Information**

- Setting a different value for the expanded variable won't make Compose detect it as a
state change so nothing will happen. But its possible to save state decelerated with `remember`

- if you call the same composable from different parts of the screen you will create different
UI elements, each with its own version of the state. You can think of internal state as a private
variable in a class.

- In Compose you don't hide UI elements. Instead, you simply don't add them to the composition,
so they're not added to the UI tree that Compose generates. You do this with simple conditional
Kotlin logic.

- LazyColumn and LazyRow are equivalent to RecyclerView in Android Views

# **Methods**

`Arrangement` (within a `Column` or `Row`): Dictates how children are spaced out along the main
axis of the layout. `Arrangement.Center` within a Column centers **vertically**. `Arrangement.Center`
within a Row centers **horizontally**. It controls the overall positioning between the children.

`Alignment` (within a `Column` or `Row`): Dictates how children are positioned along the cross
axis of the layout. `Alignment.CenterHorizontally` within a Column centers **horizontally**.
`Alignment.CenterVertically` within a Row centers **vertically**. It controls positioning
perpendicular to the main arrangement.

The key is to remember that `Arrangement` is along the primary direction of the layout
(vertical for `Column`, horizontal for `Row`), and Alignment is perpendicular to that.
Your initial statement mixed up which was the main and cross axis for each layout.

`shouldShowOnboarding` is using a `by` keyword instead of the `=`. This is a property delegate
that saves you from typing `.value` every time.

Instead of letting `OnboardingScreen` mutate our state, it would be better to let it notify when
the user clicked on the button. By passing **callbacks down**. Callbacks are functions that are
passed as arguments to other functions and get executed when the event occurs.
add a function parameter to the onboarding screen defined as `onContinueClicked: () -> Unit`
so you can mutate the state from MyApp.

the `animateDpAsState` composable  returns a State object whose `value` will continuously be
updated by the animation until it finishes. It takes a "target value" whose type is `Dp`

`animateDpAsState` takes an optional `animationSpec` parameter that lets you customize the animation

## Examples

### Scaffold

Scaffold is a basic layout for arranging material components. In common - patterns, such as the
screen with a small top app bar and a floating action button.
```kt
Surface(){
    Scaffold(
        topBar = {},
        content = {},
        bottomBar = {}
    )
}
```


### Surface

```kt
Surface(
    color = MaterialTheme.colorScheme.surface //example
    shape = MaterialTheme.shapes.medium
    border = BorderStroke(1.dp, Color.Black) // or colorScheme
    shadowElevation = 5.dp
    tonalElevation = 5.dp
){
    Text("Surface")
}
```

### Modifiers

```kt
Text(
    text = "Hello World",
    Modifier.background(Color.Magenta)
        .size(200.dp, 30.dp)
        .padding(5.dp)
        .alpha(0.2f)
        .clickable { TODO:"onClick" }
)
```

### Row

```kt
fun RowExample(){
    Row(
        Modifier.fillMaxWidth()
            .padding(16.dp) //etc
    ){
        Image(pic.image)
        Text("sample")
        RadioButton(/*...*/)
    }
}
```

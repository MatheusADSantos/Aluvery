package com.github.matheusadsantos.aluvery

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    MyFirstComposable()
                }
            }
        }
    }
}

@Composable
fun MyFirstComposable() {
    Text("My first composable")
    Text("My second composable")
}

@Preview(showBackground = true)
@Composable
fun ColumnPreview() {
    Column {
        Text("Text 1")
        Text("Text 2")
    }
}

@Preview(showBackground = true)
@Composable
fun RowPreview() {
    Row {
        Text("Text 1")
        Text("Text 2")
    }
}

@Preview(showBackground = true)
@Composable
fun BoxPreview() {
    Box {
        Text("Text 1")
        Text("Text 2")
    }
}

@Preview(showBackground = true)
@Composable
fun CustomPreview() {
    Column {
        Text(text = "Text 1")
        Text(text = "Text 2")
        Row {
            Text(text = "Text 3")
            Text(text = "Text 4")
        }
        Box {
            Row {
                Text(text = "Text 5")
                Text(text = "Text 6")
            }
            Column {
                Text(text = "Text 7")
                Text(text = "Text 8")
            }
        }
    }
}

@Preview
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        Surface {
            MyFirstComposable()
        }
    }
}
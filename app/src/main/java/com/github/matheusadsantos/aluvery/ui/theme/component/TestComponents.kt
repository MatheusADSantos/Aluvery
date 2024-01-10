package com.github.matheusadsantos.aluvery.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme


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
    Column(
        Modifier
            .padding(8.dp)
            .background(Color.Blue)
            .padding(8.dp)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        Text(text = "Text 1")
        Text(text = "Text 2")
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                )
                .background(Color.Green)
                .fillMaxWidth(0.9f)
        ) {
            Text(text = "Text 3")
            Text(text = "Text 4")
        }
        Box(
            Modifier
                .padding(8.dp)
                .background(color = Color.Red)
                .padding(all = 8.dp)
        ) {
            Row(
                Modifier
                    .padding(8.dp)
                    .background(color = Color.Cyan)
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Text 5")
                Text(text = "Text 6")
            }
            Column(
                Modifier
                    .padding(8.dp)
                    .background(color = Color.Yellow)
                    .padding(all = 8.dp)
            ) {
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
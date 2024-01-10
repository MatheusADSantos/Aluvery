package com.github.matheusadsantos.aluvery.ui.theme.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ChallengeComposable() {
    Row(Modifier.background(Color.White)) {
        Box(
            Modifier
                .background(Color.Blue)
                .width(100.dp)
                .height(150.dp)
        ) {

        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = "Test 1",
                modifier = Modifier
                    .padding(8.dp)
            )
            Text(
                text = "Test 2",
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
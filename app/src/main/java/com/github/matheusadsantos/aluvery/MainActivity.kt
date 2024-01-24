package com.github.matheusadsantos.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.theme.Purple500
import com.github.matheusadsantos.aluvery.ui.theme.Teal200

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                Surface {
                    CardItem()
                }
            }
        }
    }
}

@Preview
@Composable
fun CardItem() {
    val sizeImage = 100.dp
    Surface(Modifier.padding(8.dp), shadowElevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
        Row(Modifier
                .height(sizeImage * 2)
                .fillMaxWidth()) {
            Box(Modifier
                    .width(sizeImage)
                    .fillMaxHeight()
                    .background(brush = Brush.verticalGradient(colors = listOf(
                            Purple500, Teal200
                    )))) {
                Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        Modifier
                                .size(sizeImage)
                                .align(Alignment.Center)
                                .offset(x = sizeImage / 2)
                                .clip(CircleShape)
                                .border(BorderStroke(2.dp,
                                        Brush.verticalGradient(listOf(Purple500, Teal200))),
                                        CircleShape)
                )
            }
            Spacer(modifier = Modifier.width(sizeImage / 2))
            Box(Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)) {
                Column {
                    Text(text = LoremIpsum(words = 50).values.first(),
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 20.sp)
                }
            }
        }
    }
}
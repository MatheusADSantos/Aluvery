package com.github.matheusadsantos.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.matheusadsantos.aluvery.extension.toBrazilianCurrency
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.theme.Purple500
import com.github.matheusadsantos.aluvery.ui.theme.Teal200
import java.math.BigDecimal

/*
This layout is based in:
https://www.figma.com/file/8jLGPSbGkd31YLLAXakwGp/Aluvery?node-id=5%3A34&mode=dev
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Composable
private fun App() {
    AluveryTheme {
        Surface {
            Column(
                Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProductSession()
                ProductSession()
                ProductSession()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    App()
}

@Preview(showBackground = true)
@Composable
fun ProductSession() {
    Column() {
        Text(
            text = "Promotions",
            Modifier.padding(start = 16.dp, end = 16.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProductItem(
                Product(
                    "Hamburguer",
                    BigDecimal("12.99"),
                    R.drawable.hamburger
                )
            )
            ProductItem(
                Product(
                    "Pizza",
                    BigDecimal("19.99"),
                    R.drawable.pizza
                )
            )
            ProductItem(
                Product(
                    "Fries",
                    BigDecimal("6.99"),
                    R.drawable.fries
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItem(
    product: Product = Product(
        LoremIpsum(10).values.first(),
        BigDecimal(0),
        R.drawable.placeholder
    )
) {
    Surface(
        shape = RoundedCornerShape(25.dp),
        shadowElevation = 4.dp
    ) {
        Column(
            Modifier
                .heightIn(250.dp, 300.dp)
                .width(200.dp)
        ) {
            val sizeImage = 100.dp
            Box(
                Modifier
                    .height(100.dp)
                    .background(brush = Brush.horizontalGradient(colors = listOf(Purple500, Teal200)))
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = product.image),
                    contentDescription = null,
                    Modifier
                        .size(sizeImage)
                        .offset(y = sizeImage / 2)
                        .clip(shape = CircleShape)
                        .align(Alignment.Center),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(modifier = Modifier.height(sizeImage / 2))
            Column(Modifier.padding(16.dp)) {
                Text(
                    text = product.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(),
                    Modifier.padding(top = 8.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight(400)
                )
            }
        }
    }
}

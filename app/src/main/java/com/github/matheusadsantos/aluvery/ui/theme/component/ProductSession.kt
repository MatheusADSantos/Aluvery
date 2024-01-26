package com.github.matheusadsantos.aluvery.ui.theme.component

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.matheusadsantos.aluvery.R
import com.github.matheusadsantos.aluvery.model.Product
import java.math.BigDecimal

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
fun ProductSessionPreview() {
    ProductSession()
}

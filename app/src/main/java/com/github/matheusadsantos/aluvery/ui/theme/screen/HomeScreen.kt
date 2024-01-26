package com.github.matheusadsantos.aluvery.ui.theme.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matheusadsantos.aluvery.R
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import com.github.matheusadsantos.aluvery.ui.theme.component.ProductSection
import java.math.BigDecimal

@Composable
fun HomeScreen() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProductSection("Promotions", sampleProducts)
        ProductSection(
            "Sweets", listOf(
                Product(
                    name = "Chocolate",
                    price = BigDecimal("5.99"),
                    image = R.drawable.placeholder
                )
            )
        )
        ProductSection("Drinks", sampleProducts)
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

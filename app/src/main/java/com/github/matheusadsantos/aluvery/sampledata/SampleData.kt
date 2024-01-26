package com.github.matheusadsantos.aluvery.sampledata

import com.github.matheusadsantos.aluvery.R
import com.github.matheusadsantos.aluvery.model.Product
import java.math.BigDecimal

val sampleProducts = listOf(
    Product(
        "Hamburguer",
        BigDecimal("12.99"),
        R.drawable.hamburger
    ),
    Product(
        "Pizza",
        BigDecimal("19.99"),
        R.drawable.pizza
    ),
    Product(
        "Fries",
        BigDecimal("6.99"),
        R.drawable.fries
    ),
)
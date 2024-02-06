package com.github.matheusadsantos.aluvery.dao

import com.github.matheusadsantos.aluvery.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = sampleProducts.toMutableList()
    }

    fun products() = products.toList()
}
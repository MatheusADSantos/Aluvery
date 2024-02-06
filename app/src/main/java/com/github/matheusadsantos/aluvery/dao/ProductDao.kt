package com.github.matheusadsantos.aluvery.dao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()
    fun save(product: Product) {
        products.add(product)
    }
}
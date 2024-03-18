package com.github.matheusadsantos.aluvery.dao

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }
    // as a copy, the will not get a casting to a mutable state flow for ex.
    fun products(): StateFlow<List<Product>> = products.asStateFlow()

    fun save(product: Product) {
        products.value += product
    }
}
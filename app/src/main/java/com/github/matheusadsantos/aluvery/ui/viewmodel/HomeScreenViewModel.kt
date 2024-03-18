package com.github.matheusadsantos.aluvery.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.matheusadsantos.aluvery.dao.ProductDao
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleCandies
import com.github.matheusadsantos.aluvery.sampledata.sampleDrinks
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import com.github.matheusadsantos.aluvery.ui.state.HomeScreenUIState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    var uiState: HomeScreenUIState by mutableStateOf(
        HomeScreenUIState(
            onSearchText = {
                uiState = uiState.copy( // As it is a copy from UIState "data class", all properties is accessible
                    searchText = it,
                    searchedProducts = searchedProducts(it)
                )
            },
        )
    )
        private set // Only get is public, set is private to ViewModel

    init {
        viewModelScope.launch {
            dao.products().collect { products ->
                uiState = uiState.copy(
                    sections = mapOf(
                        "All Products" to products,
                        "Promotions" to sampleDrinks + sampleCandies,
                        "Sweets" to sampleCandies,
                        "Drinks" to sampleDrinks
                    )
                )
            }
        }
    }


    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) == true
    }

    private fun searchedProducts(text: String): List<Product> =
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().value.filter(containsInNameOrDescription(text))
        } else emptyList()


}

package com.github.matheusadsantos.aluvery.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.matheusadsantos.aluvery.dao.ProductDao
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleCandies
import com.github.matheusadsantos.aluvery.sampledata.sampleDrinks
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import com.github.matheusadsantos.aluvery.ui.state.HomeScreenUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUIState> = MutableStateFlow(HomeScreenUIState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchText = { text ->
                    updateUiState(text)
                },
            )
        }


        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "All Products" to products,
                        "Promotions" to sampleDrinks + sampleCandies,
                        "Sweets" to sampleCandies,
                        "Drinks" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }

    private fun updateUiState(text: String) {
        _uiState.value = _uiState.value.copy(
            searchText = text,
            searchedProducts = searchedProducts(text)
        )
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

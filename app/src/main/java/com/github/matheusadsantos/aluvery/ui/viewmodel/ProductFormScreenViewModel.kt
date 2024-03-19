package com.github.matheusadsantos.aluvery.ui.viewmodel

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.github.matheusadsantos.aluvery.ui.state.ProductFormUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal
import java.text.DecimalFormat

class ProductFormScreenViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<ProductFormUIState> = MutableStateFlow(ProductFormUIState())
    val uiState: MutableStateFlow<ProductFormUIState> get() = _uiState

    //    fun updateName(name: String) {
//        _uiState.value = _uiState.value.copy(name = name)
//    }
//    fun updateDescription(description: String) {
//        _uiState.value = _uiState.value.copy(description = description)
//    }
    val formatter =
        DecimalFormat("#.##")


    init {
        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = {
                    _uiState.value = _uiState.value.copy(url = it)
                },
                onNameChange = {
                    _uiState.value = _uiState.value.copy(name = it)
                },
                onPriceChange = { price ->
                    val correctPrice = try {
                        formatter.format(BigDecimal(price))
                    } catch (e: IllegalArgumentException) {
                        if (price.isEmpty()) {
                            price
                        } else {
                            null
                        }
                    }
                    correctPrice?.let { _uiState.value = _uiState.value.copy(price = it) }
                },
                onDescriptionChange = {
                    _uiState.value = _uiState.value.copy(description = it)
                })
        }
    }
}
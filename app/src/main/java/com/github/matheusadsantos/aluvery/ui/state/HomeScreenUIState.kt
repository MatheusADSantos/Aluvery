package com.github.matheusadsantos.aluvery.ui.state

import com.github.matheusadsantos.aluvery.model.Product

data class HomeScreenUIState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchText: (String) -> Unit = {}
) {
    fun isShowSections() = searchText.isEmpty()
}
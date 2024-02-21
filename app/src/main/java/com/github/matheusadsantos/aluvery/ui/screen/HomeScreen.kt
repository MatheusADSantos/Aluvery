package com.github.matheusadsantos.aluvery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleCandies
import com.github.matheusadsantos.aluvery.sampledata.sampleDrinks
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import com.github.matheusadsantos.aluvery.sampledata.sampleSections
import com.github.matheusadsantos.aluvery.ui.component.CardProductItem
import com.github.matheusadsantos.aluvery.ui.component.ProductsSection
import com.github.matheusadsantos.aluvery.ui.component.SearchTextField
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.viewmodel.HomeScreenViewModel

class HomeScreenUIState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchText: (String) -> Unit = {}
) {
    fun isShowSections() = searchText.isEmpty()
}

@Composable // STATE FULL
fun HomeScreen(
    viewModel: HomeScreenViewModel,
    products: List<Product>
) {
    val sections = mapOf(
        "All Products" to products,
        "Promotions" to sampleDrinks + sampleCandies,
        "Sweets" to sampleCandies,
        "Drinks" to sampleDrinks
    )

    // Needed create a 'text state' and
    // Add join the 'sections state'(that have product too) into main state
    var text by rememberSaveable {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(text, ignoreCase = true) ||
                product.description?.contains(text, ignoreCase = true) == true
    }

    val searchedProducts = remember(products, text) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) + products.filter(containsInNameOrDescription())
        } else emptyList()
    }

    val state = viewModel.uiState
    HomeScreen(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable  // STATE LESS (All logic will in State Full)
fun HomeScreen(
    state: HomeScreenUIState = HomeScreenUIState()
) {
    Column {

        val sections = state.sections
        val text = state.searchText
        val searchedProducts = remember(text, sections) {
            state.searchedProducts
        }

        SearchTextField(
            searchText = text,
            onChangedSearch = state.onSearchText,
            modifier = Modifier
        )

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            if (state.isShowSections()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(product = product, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(state = HomeScreenUIState(sections = sampleSections))
        }
    }
}

@Preview
@Composable
fun HomeScreenWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(
                state = HomeScreenUIState(
                    searchText = "a",
                    sections = sampleSections
                )
            )
        }
    }
}

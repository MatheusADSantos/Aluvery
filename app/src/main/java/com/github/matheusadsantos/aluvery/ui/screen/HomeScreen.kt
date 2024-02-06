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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.sampledata.sampleProducts
import com.github.matheusadsantos.aluvery.sampledata.sampleSections
import com.github.matheusadsantos.aluvery.ui.component.CardProductItem
import com.github.matheusadsantos.aluvery.ui.component.ProductsSection
import com.github.matheusadsantos.aluvery.ui.component.SearchTextField
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme

class HomeScreenUIState(searchText: String = "") {

    var text: String by mutableStateOf(searchText)

    val searchedProducts
        get() =
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(text, ignoreCase = true) ||
                            product.description?.contains(text, ignoreCase = true) == true
                }
            } else emptyList()

    fun isShowSections() = text.isEmpty()

    val onSearchText: (String) -> Unit = { searchText ->
        text = searchText
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    state: HomeScreenUIState = HomeScreenUIState()
) {
    Column {

//        val state = remember { HomeScreenUIState(searchText) }
        val searchedProducts = state.searchedProducts
        val text = state.text

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
            HomeScreen(sampleSections)
        }
    }
}

@Preview
@Composable
fun HomeScreenWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections, HomeScreenUIState("a"))
        }
    }
}

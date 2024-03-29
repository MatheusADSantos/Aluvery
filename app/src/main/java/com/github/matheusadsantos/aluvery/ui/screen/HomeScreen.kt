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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.matheusadsantos.aluvery.sampledata.sampleSections
import com.github.matheusadsantos.aluvery.ui.component.CardProductItem
import com.github.matheusadsantos.aluvery.ui.component.ProductsSection
import com.github.matheusadsantos.aluvery.ui.component.SearchTextField
import com.github.matheusadsantos.aluvery.ui.state.HomeScreenUIState
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.viewmodel.HomeScreenViewModel

@Composable // Stateful
fun HomeScreen(
    viewModel: HomeScreenViewModel,
) {
    val state by viewModel.uiState.collectAsState()
    HomeScreen(state = state)
}

@Composable  // Stateless (All logic will in State Full)
fun HomeScreen(
    state: HomeScreenUIState = HomeScreenUIState()
) {
    Column {
        val sections = state.sections
        val text = state.searchText
        val searchedProducts = state.searchedProducts

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

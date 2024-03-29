package com.github.matheusadsantos.aluvery.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.github.matheusadsantos.aluvery.R
import com.github.matheusadsantos.aluvery.ui.state.ProductFormUIState
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.viewmodel.ProductFormScreenViewModel

// StateFull(Only logic/states) -> Called by Activity
@Composable
fun ProductFormScreen(
    viewModel: ProductFormScreenViewModel,
    onSaveClick: () -> Unit = {}
) {
    val state by viewModel.uiState.collectAsState()
    ProductFormScreen(
        state = state,
        onSaveClick = {
            viewModel.saveProduct()
            onSaveClick()
        }
    )
}

// StateLess(Only composables) -> Called by main ProductFormScreen
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    state: ProductFormUIState = ProductFormUIState(),
    onSaveClick: () -> Unit = {}
) {
    // Getting data
    val url = state.url
    val isShowPreview = state.isShowPreviewImageUrl
    val name = state.name
    val price = state.price
    val description = state.description

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier)
        Text(
            "Creating product",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )

        if (isShowPreview) {
            AsyncImage(
                model = url,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder),
            )
        }
        TextField(
            value = url, onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(), label = {
                Text("Url Image")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next // to go next step
            )
        )

        TextField(
            value = name, onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(), label = {
                Text("Name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next, // to go next step
                capitalization = KeyboardCapitalization.Words
            )
        )

        TextField(
            value = price,
            onValueChange = state.onPriceChange,
            Modifier.fillMaxWidth(),
            isError = state.isPriceError,
            label = {
                Text("Price")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next, // to go next step
            ),
        )
        if (state.isPriceError) {
            Text(
                text = "Price must be decimal",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        TextField(
            value = description, onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp), label = {
                Text("Description")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        Button(
            onClick = onSaveClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
        Spacer(modifier = Modifier)
    }
}


// We have 2 ways to preview
@Preview
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(state = ProductFormUIState())
        }
    }
}

@Preview
@Composable
fun ProductFormScreenFilledPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(
                state = ProductFormUIState(
                    url = "url teste",
                    name = "nome teste",
                    price = "123",
                    description = "descrição teste"
                )
            )
        }
    }
}
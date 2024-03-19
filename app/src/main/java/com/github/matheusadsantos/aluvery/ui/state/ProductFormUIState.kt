package com.github.matheusadsantos.aluvery.ui.state

data class ProductFormUIState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val isPriceError: Boolean = false,
    val description: String = "",
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
) {
    // Up level all data and behavior from ProductFormScreen
    val isShowPreviewImageUrl: Boolean get() = url.isNotBlank()
}
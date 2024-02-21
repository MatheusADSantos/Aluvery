package com.github.matheusadsantos.aluvery.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.github.matheusadsantos.aluvery.ui.screen.HomeScreenUIState

class HomeScreenViewModel : ViewModel() {

    var uiState by mutableStateOf(HomeScreenUIState())
        private set

}

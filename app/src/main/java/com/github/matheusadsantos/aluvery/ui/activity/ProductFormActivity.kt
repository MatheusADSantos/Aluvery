package com.github.matheusadsantos.aluvery.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.github.matheusadsantos.aluvery.dao.ProductDao
import com.github.matheusadsantos.aluvery.ui.screen.ProductFormScreen
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.viewmodel.ProductFormScreenViewModel

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AluveryTheme {
                Surface {
                    val viewModel: ProductFormScreenViewModel by viewModels()
                    ProductFormScreen(
                        viewModel = viewModel,
                        onSaveProduct = { product ->
                            dao.save(product)
                            finish()
                        })
                }
            }
        }
    }
}


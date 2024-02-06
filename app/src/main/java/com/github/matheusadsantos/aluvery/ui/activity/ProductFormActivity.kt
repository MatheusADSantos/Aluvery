package com.github.matheusadsantos.aluvery.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme

class ProductFormActivity: ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AluveryTheme {
                Surface {
                    Text("Product Form...")
                }
            }
        }
    }
}
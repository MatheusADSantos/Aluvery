package com.github.matheusadsantos.aluvery.ui.activity

import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.github.matheusadsantos.aluvery.sampledata.sampleSections
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.screen.HomeScreen

/*
This layout is based in:
https://www.figma.com/file/8jLGPSbGkd31YLLAXakwGp/Aluvery?node-id=5%3A34&mode=dev
*/

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                startActivity(Intent(this, ProductFormActivity::class.java))
            })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun App(onFabClick: () -> Unit = {}) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = onFabClick) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) { padding ->
        Box(Modifier.padding(padding)) {
            AluveryTheme {
                Surface {
                    HomeScreen(sampleSections)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    App()
}




package com.github.matheusadsantos.aluvery.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.github.matheusadsantos.aluvery.sampledata.sampleSections
import com.github.matheusadsantos.aluvery.ui.screen.HomeScreen
import com.github.matheusadsantos.aluvery.ui.state.HomeScreenUIState
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import com.github.matheusadsantos.aluvery.ui.viewmodel.HomeScreenViewModel

/*
    This layout is based in:
    https://www.figma.com/file/8jLGPSbGkd31YLLAXakwGp/Aluvery?node-id=5%3A34&mode=dev
    ------------------------------------------------------------------
    This blog explaining all about compose:
    https://medium.com/wearejaya/conhecendo-o-jetpack-compose-d0ca4398e5c7
*/

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(onFabClick = {
                val intent = Intent(this, ProductFormActivity::class.java)
                startActivity(intent)
            }) {
                val viewModel by viewModels<HomeScreenViewModel>()
                HomeScreen(viewModel = viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    AluveryTheme {
        Surface {
            Scaffold(floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }) { padding ->
                Box(Modifier.padding(padding)) {
                    content()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppPreview() {
    App {
        HomeScreen(HomeScreenUIState(sections = sampleSections))
    }
}




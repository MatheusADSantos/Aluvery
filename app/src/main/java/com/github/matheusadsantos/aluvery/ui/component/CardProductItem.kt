package com.github.matheusadsantos.aluvery.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.github.matheusadsantos.aluvery.R
import com.github.matheusadsantos.aluvery.extension.toBrazilianCurrency
import com.github.matheusadsantos.aluvery.model.Product
import com.github.matheusadsantos.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: CardElevation = CardDefaults.elevatedCardElevation(),
    expanded: Boolean = false
) {
    var expandedState: Boolean by remember { mutableStateOf(expanded) }
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable(onClick = {
                expandedState = !expandedState
                Log.d("MADS", "CardProductItem: expanded: $expandedState")

            }),
        elevation = elevation,
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            product.description?.let { description ->
                val textOverFlow = if (expandedState) TextOverflow.Visible else TextOverflow.Ellipsis
                val textMaxLines = if (expandedState) Int.MAX_VALUE else 2
                Text(
                    text = description,
                    Modifier.padding(16.dp),
                    overflow = textOverFlow,
                    maxLines = textMaxLines
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    "Test",
                    BigDecimal("9.99"),
                ),
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "Test",
                    price = BigDecimal("9.99"),
                    image = LoremIpsum(50).values.first(),
                ),
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionExpandedPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "Test",
                    price = BigDecimal("9.99"),
                    image = LoremIpsum(150).values.first(),
                ),
                expanded = true,
            )
        }
    }
}
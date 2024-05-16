package kz.bakhus.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.bakhus.core.models.Prices

@Composable
fun PriceDisplay(prices: Prices) {
    if (prices.hasDiscount) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = "${prices.basePrice}₸",
                style = TextStyle(
                    fontSize = 16.sp,
                    textDecoration = TextDecoration.LineThrough,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = "${prices.discountedPrice}₸",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )
            )
        }
    } else {
        Text(
            text = "${prices.basePrice}₸",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(top=8.dp)
        )
    }
}

@Composable
fun ProductPriceSection(prices: Prices) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Цена",
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        PriceDisplay(prices = prices)
    }
}
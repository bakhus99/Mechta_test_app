package kz.bakhus.core.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AddToFavButton(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit
) {

    Button(
        onClick = {
            onFavoriteClick()
        },
        modifier = Modifier
            .padding(top = 8.dp)

    ) {
        Text(text = if (isFavorite) "Удалить из избранного" else "Добавить в избранное")

    }
}
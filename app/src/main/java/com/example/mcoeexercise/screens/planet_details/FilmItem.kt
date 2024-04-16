package com.example.mcoeexercise.screens.planet_details

// Import statements for necessary classes and interfaces
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcoeexercise.R
import com.example.mcoeexercise.component.TextLabelValue
import com.example.mcoeexercise.model.Film

/**
 * Composable function to display details of a film.
 *
 * @param film The film object containing details to display.
 * @param onClick Callback function to be invoked when the card is clicked.
 */
@Composable
fun FilmItemView(film: Film, onClick: () -> Unit) {
    // Card displaying film details
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = { onClick },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            // Title of the film
            Text(
                text = film.title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
            // Details: Director, Producer, Release Date
            TextLabelValue(stringResource(R.string.director), film.director)
            TextLabelValue(stringResource(R.string.producer), film.producer)
            TextLabelValue(stringResource(R.string.release_date), film.releaseDate)
            // Opening Crawl
            Text(
                text = stringResource(R.string.opening_crawl),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelSmall,
            )
            // Text displaying the opening crawl with ellipsis if exceeds max lines
            Text(
                text = film.openingCrawl!!,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Justify,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

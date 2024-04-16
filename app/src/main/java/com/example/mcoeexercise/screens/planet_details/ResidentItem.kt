package com.example.mcoeexercise.screens.planet_details

// Import necessary modules and libraries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.mcoeexercise.R
import com.example.mcoeexercise.component.TextLabelValue
import com.example.mcoeexercise.model.People

/**
 * Composable function to display details of a resident (people) on a card.
 *
 * @param people The details of the resident.
 * @param onClick Callback function for click events on the card.
 */
@Composable
fun ResidentItem(people: People, onClick: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        onClick = { onClick },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                text = people.name ?: "",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
            TextLabelValue(stringResource(R.string.gender), people.gender)
            TextLabelValue(stringResource(R.string.birth_year), people.birthYear)
        }
    }
}

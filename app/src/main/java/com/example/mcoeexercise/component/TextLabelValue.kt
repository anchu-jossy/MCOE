package com.example.mcoeexercise.component

// Import statements for necessary Compose components and resources

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/**
 * Composable function for displaying a label-value pair in a row layout.
 *
 * @param lbl The label text.
 * @param value The value text.
 */
@Composable
fun TextLabelValue(lbl: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        // Displaying the label text with specified style and alignment
        Text(
            text = lbl, modifier = Modifier
                .fillMaxWidth(0.5f),
            textAlign = TextAlign.Start,
            style = typography.labelSmall,
        )
        // Displaying the value text with specified style and alignment
        Text(
            text = value ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            style = typography.bodySmall,
        )
    }
}

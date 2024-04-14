package com.example.mcoeexercise.component

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

@Composable
fun TextLabelValue(lbl: String, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
    ) {
        Text(
            text = lbl, modifier = Modifier
                .fillMaxWidth(0.5f),
            textAlign = TextAlign.Start,
            style = typography.labelSmall,
            color = Color.Black
        )
        Text(
            text = value ?: "",
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.End,
            style = typography.bodySmall,
            color = Color.DarkGray
        )
    }
}
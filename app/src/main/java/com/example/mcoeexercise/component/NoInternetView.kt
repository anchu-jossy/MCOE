package com.example.mcoeexercise.component

// Import statements for necessary Compose components and resources
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.mcoeexercise.R

/**
 * Composable function for displaying a view indicating no internet connection.
 */
@Composable
fun NoInternetView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Displaying a Text with the resource string for the no internet message
        Text(
            text = stringResource(R.string.no_internet),
            modifier = Modifier.fillMaxWidth(0.75f),
            textAlign = TextAlign.Center,
        )
    }
}


package com.example.mcoeexercise.component

// Import statements for necessary Compose components
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * Composable function for displaying a circular indeterminate progress bar.
 *
 * @param isDisplayed Boolean value representing whether the progress bar should be displayed.
 * @param verticalBias Float value representing the vertical bias of the progress bar.
 */
@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, verticalBias: Float) {
    if (isDisplayed) {
        // Displaying a Column with the circular progress indicator centered vertically and horizontally
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }
}

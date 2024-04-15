package com.example.mcoeexercise

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcoeexercise.model.Film
import com.example.mcoeexercise.screens.planet_details.FilmItemView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FilmItemViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testFilmDetailsLoading() {
        composeTestRule.setContent {
            FilmItemView(Film("A New Hope", 4,"It is a period of civil war.\r\nRebel spaceships, striking\r\nfrom a hidden base, have won\r\ntheir first victory against\r\nthe evil Galactic Empire.\r\n\r\nDuring the battle, Rebel\r\nspies managed to steal secret\r\nplans to the Empire's\r\nultimate weapon, the DEATH\r\nSTAR, an armored space\r\nstation with enough power\r\nto destroy an entire planet.\r\n\r\nPursued by the Empire's\r\nsinister agents, Princess\r\nLeia races home aboard her\r\nstarship, custodian of the\r\nstolen plans that can save her\r\npeople and restore\r\nfreedom to the galaxy....",
                "George Lucas","Gary Kurtz, Rick McCallum", "1977-05-25")){}
        }

        composeTestRule.onNodeWithText("A New Hope").assertIsDisplayed()

    }
}
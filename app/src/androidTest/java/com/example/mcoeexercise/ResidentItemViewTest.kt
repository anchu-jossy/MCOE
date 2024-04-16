package com.example.mcoeexercise

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mcoeexercise.model.People
import com.example.mcoeexercise.screens.planet_details.ResidentItem
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResidentItemViewTest {


    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testResidentDetailsLoading() {
        composeTestRule.setContent {
            ResidentItem(
                People("Leia Organa", "150","49","brown","light",
                    "brown","19BBY", "female")
            ){}
        }

        composeTestRule.onNodeWithText("Leia Organa").assertIsDisplayed()
        composeTestRule.onNodeWithText("Male").assertIsNotDisplayed()


    }
}
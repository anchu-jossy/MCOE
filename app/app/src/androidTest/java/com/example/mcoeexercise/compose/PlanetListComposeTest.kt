package com.example.mcoeexercise.compose

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mcoeexercise.AllPlanets
import com.example.mcoeexercise.MainActivity
import com.example.mcoeexercise.R
import com.example.mcoeexercise.screens.planet_details.PlanetDetails
import com.example.mcoeexercise.screens.planetlist.PlanetList
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.junit.runner.RunWith

@HiltAndroidTest
//@RunWith(AndroidJUnit4::class)
class PlanetListComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @get:Rule
    var hiltTestRule = HiltAndroidRule(this)
    @get:Rule
    var uiTestRule = RuleChain.outerRule(hiltTestRule).around(composeTestRule)


    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            val navController = rememberNavController()
            PlanetDetails(navController, 1)
        }
    }

    @Test
    fun app_displays_list_of_items() {
        //assert the list is displayed
        composeTestRule.onNodeWithText( "Films").assertIsDisplayed()

    }

    @Test
    fun planet_list_test() {

        composeTestRule.setContent {
            AllPlanets()
        }
        composeTestRule
            .onNodeWithContentDescription("LazyColumn")
            .performScrollToIndex(2)

        composeTestRule
            .onNodeWithContentDescription("LazyColumn").assertIsDisplayed()


Assert.assertTrue(true)

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.mcoeexercise", appContext.packageName)
    }
}

package com.example.mcoeexercise

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mcoeexercise.repository.PlanetRepository
import com.example.mcoeexercise.screens.planet_details.PlanetDetailViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject
import kotlin.jvm.Throws
/*

@HiltAndroidTest
class PlanetDetailViewModelTest {

    private lateinit var viewModel: PlanetDetailViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()


    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var planetRepository: PlanetRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext

        viewModel = PlanetDetailViewModel(planetRepository)
//        viewModel.planetDetails(1)
    }
    @Test
    fun testPlantDetails() = coroutineRule.runBlockingTest {
        viewModel.planetDetails(1)
    }


    */
/*@Suppress("BlockingMethodInNonBlockingContext")
    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() = coroutineRule.runBlockingTest {
        Assert.assertFalse(viewModel.planetDetail.value?.name.isNullOrEmpty())
    }
*//*



}*/

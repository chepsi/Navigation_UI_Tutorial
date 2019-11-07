package com.chepsi.navigationuitutorial

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test fun navigationWorks(){
        val mockController = mock(NavController::class.java)
        val homeScenario = launchFragmentInContainer<HomeFragment>()
        homeScenario.onFragment {
            Navigation.setViewNavController(it.requireView(), mockController)
        }
        onView(ViewMatchers.withId(R.id.settingsFragment)).perform(ViewActions.click())
        verify(mockController).navigate(R.id.action_homeFragment_to_settingsFragment)
    }
}
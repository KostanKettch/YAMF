package com.kostankettch.yamf

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kostankettch.yamf.view.MainActivity
import com.kostankettch.yamf.view.rv_viewholders.MovieViewHolder
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recyclerViewShouldBeAttached() {
        onView(withId(R.id.main_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
    }

    @Test
    fun searchViewShouldBeAbleToInputText() {
        val testString = "123456"
        onView(withId(R.id.main_recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.main_recycler)).perform(typeSearchViewText(testString))
    }

    @Test
    fun allMenuDestinationsShouldWork() {
        onView(withId(R.id.favorites)).perform(click())
        onView(withId(R.id.favorites_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.watch_later)).perform(click())
        onView(withId(R.id.watch_later_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.collections)).perform(click())
        onView(withId(R.id.collections_fragment_root)).check(matches(isDisplayed()))

        onView(withId(R.id.home)).perform(click())
        onView(withId(R.id.home_fragment_root)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldOpenDetailsFragment() {
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
        onView(withId(R.id.app_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun addToFavoritesButtonClickable() {
        onView(withId(R.id.main_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<MovieViewHolder>(0, click()))
        onView(withId(R.id.details_fab_favorites)).perform(click())
        onView(withId(R.id.details_fab_favorites)).perform(click())

    }




    private fun typeSearchViewText(text: String?): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return allOf(isDisplayed(), isAssignableFrom(SearchView::class.java))
            }

            override fun getDescription(): String {
                return "Change view text"
            }

            override fun perform(uiController: UiController?, view: View?) {
                (view as SearchView).setQuery(text, false)
            }
        }
    }
}
package com.syukron.submission1_bajp_dicoding.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.syukron.submission1_bajp_dicoding.R
import com.syukron.submission1_bajp_dicoding.utils.DummyMovies
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

        private val dummyMovies = DummyMovies.generateDataDummyMovies()
        private val dummyTvShows = DummyMovies.generateDataDummyTvShows()

        @Before
        fun setup() {
            ActivityScenario.launch(MainActivity::class.java)
        }

        @Test
        fun loadMovies() {
            onView(withId(R.id.rv_movie_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.rv_movie_fragment)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
        }

        @Test
        fun loadDetailMovies() {
            onView(withId(R.id.rv_movie_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

            onView(withId(R.id.iv_detail_bg)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.iv_detail_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyMovies[0].title)))
            onView(withId(R.id.tv_detail_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_year)).check(ViewAssertions.matches(withText(dummyMovies[0].releaseDate)))
            onView(withId(R.id.tv_detail_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_genre)).check(ViewAssertions.matches(withText(dummyMovies[0].genre)))
            onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(withText(dummyMovies[0].overview)))
        }

        @Test
        fun loadTvShows() {
            onView(withText("TV Show")).perform(ViewActions.click())
            onView(withId(R.id.rv_tv_shows_fragment)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.rv_tv_shows_fragment)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
        }

        @Test
        fun loadDetailTvShows() {
            onView(withText("TV Show")).perform(ViewActions.click())
            onView(withId(R.id.rv_tv_shows_fragment)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))

            onView(withId(R.id.iv_detail_bg)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.iv_detail_poster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_title)).check(ViewAssertions.matches(withText(dummyTvShows[0].title)))
            onView(withId(R.id.tv_detail_year)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_year)).check(ViewAssertions.matches(withText(dummyTvShows[0].releaseDate)))
            onView(withId(R.id.tv_detail_genre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_genre)).check(ViewAssertions.matches(withText(dummyTvShows[0].genre)))
            onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(withId(R.id.tv_detail_overview)).check(ViewAssertions.matches(withText(dummyTvShows[0].overview)))
        }
}

package com.project.alfidh02.jetpack.tvmov.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.project.alfidh02.jetpack.tvmov.R
import com.project.alfidh02.jetpack.tvmov.model.utils.MovieTVDummy
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {

    private val dummyMovie = MovieTVDummy.generateMovies()
    private val dummyTv = MovieTVDummy.generateTVShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // check image
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))

        // check title
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyMovie[0].title)))

        // check release date
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyMovie[0].date)))

        // check detail
        onView(withId(R.id.tv_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_detail)).check(matches(withText(dummyMovie[0].genre)))

        // check desc
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dummyMovie[0].desc)))
    }

    @Test
    fun loadTv() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTv.size))
    }

    @Test
    fun loadTVDetail() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // check image
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))

        // check title
        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_title_detail)).check(matches(withText(dummyTv[0].title)))

        // check release date
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(withText(dummyTv[0].date)))

        // check detail
        onView(withId(R.id.tv_genre_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre_detail)).check(matches(withText(dummyTv[0].genre)))

        // check desc
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(withText(dummyTv[0].desc)))
    }

}
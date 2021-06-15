package com.alfidh02.tvmov.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.alfidh02.tvmov.R
import com.alfidh02.tvmov.testutil.DataDummy
import com.alfidh02.tvmov.testutil.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyDataMovie = DataDummy.generateMovie()
    private val dummyDataTV = DataDummy.generateTVShows()

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovieAndTVShowList() {
        onView(withId(R.id.rv_movies)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyDataMovie.size
            )
        )

        onView(withId(R.id.view_pager)).perform(swipeLeft())

        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyDataTV.size
            )
        )
    }

    @Test
    fun loadMovieDetail() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTVShowDetail() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))
    }

    @Test
    fun loadFavoriteMovie() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(pressBack())

        onView(withId(R.id.switch_fav)).perform(click())

        onView(withId(R.id.rv_movies_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movies_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))

        onView(isRoot()).perform(pressBack())
    }

    @Test
    fun loadFavoriteTVShow() {
        onView(withText("TV")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_fav)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        onView(withId(R.id.switch_fav)).perform(click())

        onView(withText("TV")).perform(click())

        onView(withId(R.id.rv_tv_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_fav)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_title_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release_date)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_rate_detail)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_desc)).check(matches(isDisplayed()))

        onView(isRoot()).perform(pressBack())
    }
}
package com.alfidh.tvmov.view.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alfidh.tvmov.R
import com.alfidh.tvmov.testingutil.DataDummy
import com.alfidh.tvmov.testingutil.DetailDataDummy
import com.alfidh.tvmov.testingutil.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dataDummyMovie = DataDummy.generateMovie()
    private val detailDummyMovie = DetailDataDummy.generateDetailMovie()

    private val dataDummyTVShow = DataDummy.generateTVShows()
    private val detailDummyTVShow = DetailDataDummy.generateDetailTVShows()

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
        Espresso.onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataDummyMovie.size))

        Espresso.onView(withId(R.id.view_pager)).perform(ViewActions.swipeLeft())

        Espresso.onView(withId(R.id.rv_tv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.rv_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataDummyTVShow.size))
    }

    @Test
    fun loadMovieDetail() {
        Espresso.onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(withText(dataDummyMovie[0].title)))

        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText(dataDummyMovie[0].date)))

        Espresso.onView(withId(R.id.tv_genre_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre_detail))
            .check(ViewAssertions.matches(withText(detailDummyMovie.genres.toString()
                .replace("[", "")
                .replace("]", ""))))

        Espresso.onView(withId(R.id.iv_poster_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tv_rate_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rate_detail))
            .check(ViewAssertions.matches(withText(dataDummyMovie[0].rate.toString())))

        Espresso.onView(withId(R.id.tv_desc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(ViewAssertions.matches(withText(detailDummyMovie.desc)))
    }

    @Test
    fun loadTVShowDetail() {
        Espresso.onView(ViewMatchers.withText("TV")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))

        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_title_detail))
            .check(ViewAssertions.matches(withText(dataDummyTVShow[0].title)))

        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_release_date))
            .check(ViewAssertions.matches(withText(dataDummyTVShow[0].date)))

        Espresso.onView(withId(R.id.tv_genre_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_genre_detail))
            .check(ViewAssertions.matches(withText(detailDummyTVShow.genres.toString()
                .replace("[", "")
                .replace("]", ""))))

        Espresso.onView(withId(R.id.iv_poster_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(withId(R.id.tv_rate_detail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_rate_detail))
            .check(ViewAssertions.matches(withText(dataDummyTVShow[0].rate.toString())))

        Espresso.onView(withId(R.id.tv_desc))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.tv_desc))
            .check(ViewAssertions.matches(withText(detailDummyTVShow.desc)))
    }
}
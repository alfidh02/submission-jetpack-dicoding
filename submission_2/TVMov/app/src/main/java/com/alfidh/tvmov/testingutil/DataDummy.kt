package com.alfidh.tvmov.testingutil

import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.alfidh.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh.tvmov.model.data.remote.response.tv.TVRemote


object DataDummy {
    fun generateMovie(): ArrayList<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                337404,
                "Cruella",
                "2021-05-26",
                "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
                8.8
            )
        )

        movies.add(
            MovieEntity(
                19404,
                "Dilwale Dulhania Le Jayenge",
                "1995-10-20",
                "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                8.7
            )
        )

        movies.add(
            MovieEntity(
                724089,
                "Gabriel's Inferno Part II",
                "2020-07-31",
                "/irlfhYtHfhZuYpsq2LAoh308NFe.jpg",
                8.7
            )
        )

        movies.add(
            MovieEntity(
                278,
                "The Shawshank Redemption",
                "1994-09-23",
                "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                8.7
            )
        )

        movies.add(
            MovieEntity(
                238,
                "The Godfather",
                "1972-03-14",
                "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                8.7
            )
        )
        return movies
    }


    fun generateTVShows(): ArrayList<TVEntity> {

        val tvShows = ArrayList<TVEntity>()

        tvShows.add(
            TVEntity(
                100,
                "I Am Not an Animal",
                "2004-05-10",
                "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                9.4
            )
        )

        tvShows.add(
            TVEntity(
                88040,
                "Given",
                "2019-07-12",
                "/7gbmM2NWcqZONbp65HUWDf4wr0Q.jpg",
                9.1
            )
        )

        tvShows.add(
            TVEntity(
                83097,
                "The Promised Neverland",
                "2019-01-11",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.1
            )
        )

        tvShows.add(
            TVEntity(
                83095,
                "The Rising of the Shield Hero",
                "2019-01-09",
                "/qSgBzXdu6QwVVeqOYOlHolkLRxZ.jpg",
                9.1
            )
        )

        tvShows.add(
            TVEntity(
                80564,
                "Banana Fish",
                "2018-07-06",
                "/ci7jTekDFEx6U48XUCl3vBMdrns.jpg",
                9.0
            )
        )

        return tvShows
    }


    fun generateRemoteMovies(): ArrayList<MovieRemote> {

        val remoteMovie = ArrayList<MovieRemote>()

        remoteMovie.add(
            MovieRemote(
                id = 337404,
                title = "Cruella",
                date = "2021-05-26",
                pic = "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
                rate = 8.8
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 19404,
                title = "Dilwale Dulhania Le Jayenge",
                date = "1995-10-20",
                pic = "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                rate = 8.7
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 724089,
                title = "Gabriel's Inferno Part II",
                date = "2020-07-31",
                pic = "/irlfhYtHfhZuYpsq2LAoh308NFe.jpg",
                rate = 8.7
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 278,
                title = "The Shawshank Redemption",
                date = "1994-09-23",
                pic = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                rate = 8.7
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 238,
                title = "The Godfather",
                date = "1972-03-14",
                pic = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                rate = 8.7
            )
        )

        return remoteMovie
    }


    fun generateRemoteTVShows(): ArrayList<TVRemote> {

        val tvShowRemote = ArrayList<TVRemote>()

        tvShowRemote.add(
            TVRemote(
                id = 100,
                title = "I Am Not an Animal",
                date = "2004-05-10",
                pic = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                rate = 9.4
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 88040,
                title = "Given",
                date = "2019-07-12",
                pic = "/7gbmM2NWcqZONbp65HUWDf4wr0Q.jpg",
                rate = 9.1
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 83097,
                title = "The Promised Neverland",
                date = "2019-01-11",
                pic = "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                rate = 9.1
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 83095,
                title = "The Rising of the Shield Hero",
                date = "2019-01-09",
                pic = "/qSgBzXdu6QwVVeqOYOlHolkLRxZ.jpg",
                rate = 9.1
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 80564,
                title = "Banana Fish",
                date = "2018-07-06",
                pic = "/ci7jTekDFEx6U48XUCl3vBMdrns.jpg",
                rate = 9.0
            )
        )

        return tvShowRemote
    }
}
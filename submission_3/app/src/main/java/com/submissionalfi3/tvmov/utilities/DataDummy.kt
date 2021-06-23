package com.submissionalfi3.tvmov.utilities

import com.submissionalfi3.tvmov.model.data.local.entities.MovieEntity
import com.submissionalfi3.tvmov.model.data.local.entities.TVEntity
import com.submissionalfi3.tvmov.model.data.remote.response.genre.GenreResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.movie.MovieRemoteResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.submissionalfi3.tvmov.model.data.remote.response.tv.TVRemoteResponse

object DataDummy {
    fun generateMovie(): ArrayList<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                19404,
                "Dilwale Dulhania Le Jayenge",
                "1995-10-20",
                "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                8.7,
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga."
            )
        )

        movies.add(
            MovieEntity(
                278,
                "The Shawshank Redemption",
                "1994-09-23",
                "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                8.7,
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope."
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
                9.4,
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."            )
        )

        tvShows.add(
            TVEntity(
                88040,
                "Given",
                "2019-07-12",
                "/7gbmM2NWcqZONbp65HUWDf4wr0Q.jpg",
                9.1,
                "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji."            )
        )

        return tvShows
    }


    fun generateRemoteMovies(): ArrayList<MovieRemoteResponse> {

        val remoteMovie = ArrayList<MovieRemoteResponse>()

        remoteMovie.add(
            MovieRemoteResponse(
                id = 19404,
                title = "Dilwale Dulhania Le Jayenge",
                date = "1995-10-20",
                image = "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                rate = 8.7,
                "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga."
            )
        )

        remoteMovie.add(
            MovieRemoteResponse(
                id = 278,
                title = "The Shawshank Redemption",
                date = "1994-09-23",
                image = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                rate = 8.7,
                "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope."
            )
        )
        return remoteMovie
    }


    fun generateRemoteTVShows(): ArrayList<TVRemoteResponse> {

        val tvShowRemote = ArrayList<TVRemoteResponse>()

        tvShowRemote.add(
            TVRemoteResponse(
                id = 100,
                title = "I Am Not an Animal",
                date = "2004-05-10",
                image = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                rate = 9.4,
                "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."            )
        )

        tvShowRemote.add(
            TVRemoteResponse(
                id = 88040,
                title = "Given",
                date = "2019-07-12",
                image = "/7gbmM2NWcqZONbp65HUWDf4wr0Q.jpg",
                rate = 9.1,
                "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji."            )
        )

        return tvShowRemote
    }

    fun generateDetailMovie(): MovieEntity {
        return MovieEntity(
            337404,
            "Cruella",
            "2021-05-26",
            "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
            8.8,
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }


    fun generateDetailTVShows(): TVEntity {

        return TVEntity(
            100,
            "I Am Not an Animal",
            "2004-05-10",
            "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            9.4,
            "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."
        )
    }


    fun generateDetailRemoteMovies(): MovieDetailResponse {
        return MovieDetailResponse(
            id = 337404,
            title = "Cruella",
            date = "2021-05-26",
            image = "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
            rate = 8.8 ,
            desc = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
            genres = listOf(
                GenreResponse(
                    id = 35,
                    name = "Comedy"
                ),
                GenreResponse(
                    id = 80,
                    name = "Crime"
                )
            ),
        )
    }


    fun generateDetailRemoteTVShows(): TVDetailResponse {
        return TVDetailResponse(
            id = 100,
            title = "I Am Not an Animal",
            date = "2004-05-10",
            image = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            rate = 9.4 ,
            desc = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists.",
            genres = listOf(
                GenreResponse(
                    id = 35,
                    name = "Comedy"
                ),
                GenreResponse(
                    id = 80,
                    name = "Crime"
                )
            ),
        )
    }
}
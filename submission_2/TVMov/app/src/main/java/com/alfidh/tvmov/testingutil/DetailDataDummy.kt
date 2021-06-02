package com.alfidh.tvmov.testingutil

import com.alfidh.tvmov.model.data.entity.DetailEntity
import com.alfidh.tvmov.model.data.entity.MovieEntity
import com.alfidh.tvmov.model.data.entity.TVEntity
import com.alfidh.tvmov.model.data.remote.response.genre.GenreResponse
import com.alfidh.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh.tvmov.model.data.remote.response.tv.TVDetailResponse
import com.alfidh.tvmov.model.data.remote.response.tv.TVRemote


object DetailDataDummy {

    fun generateDetailMovie(): DetailEntity {
        return DetailEntity(
            337404,
            "Cruella",
            "2021-05-26",
            "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
            8.8,
            listOf("Comedy","Crime"),
            "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }


    fun generateDetailTVShows(): DetailEntity {

        return DetailEntity(
            100,
            "I Am Not an Animal",
            "2004-05-10",
            "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            9.4,
            listOf("Animation","Comedy"),
            "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."
        )
    }


    fun generateDetailRemoteMovies(): MovieDetailResponse {
        return MovieDetailResponse(
            id = 337404,
            title = "Cruella",
            date = "2021-05-26",
            pic = "/pKAxHs04yxLDQSIf4MNiZoePVWX.jpg",
            rate = 8.8 ,
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
            desc = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }


    fun generateDetailRemoteTVShows():TVDetailResponse {
        return TVDetailResponse(
            id = 100,
            title = "I Am Not an Animal",
            date = "2004-05-10",
            pic = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            rate = 9.4 ,
            genres = listOf(
                GenreResponse(
                    id = 16,
                    name = "Animation"
                ),
                GenreResponse(
                    id = 35,
                    name = "Comedy"
                )
            ),
            desc = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."
        )
    }
}
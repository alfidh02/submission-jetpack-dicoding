package com.alfidh02.tvmov.util

import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieDetailResponse
import com.alfidh02.tvmov.model.data.remote.response.tv.TVDetailResponse


object DetailDataDummy {

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
            desc = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella."
        )
    }


    fun generateDetailRemoteTVShows():TVDetailResponse {
        return TVDetailResponse(
            id = 100,
            title = "I Am Not an Animal",
            date = "2004-05-10",
            image = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
            rate = 9.4 ,
            desc = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."
        )
    }
}
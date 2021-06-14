package com.alfidh02.tvmov.util

import com.alfidh02.tvmov.model.data.entity.MovieEntity
import com.alfidh02.tvmov.model.data.entity.TVEntity
import com.alfidh02.tvmov.model.data.remote.response.movie.MovieRemote
import com.alfidh02.tvmov.model.data.remote.response.tv.TVRemote


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

        movies.add(
            MovieEntity(
                724089,
                "Gabriel's Inferno Part II",
                "2020-07-31",
                "/irlfhYtHfhZuYpsq2LAoh308NFe.jpg",
                8.7,
                "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?"
            )
        )

        movies.add(
            MovieEntity(
                238,
                "The Godfather",
                "1972-03-14",
                "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                8.7,
                "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge."
            )
        )

        movies.add(
            MovieEntity(
                761053,
                "Gabriel's Inferno Part III",
                "2020-11-19",
                "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                8.7,
                "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.  Watch now : https://stream4k.xyz"            )
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

        tvShows.add(
            TVEntity(
                83095,
                "The Rising of the Shield Hero",
                "2019-01-09",
                "/qSgBzXdu6QwVVeqOYOlHolkLRxZ.jpg",
                9.1,
                "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins..."            )
        )

        tvShows.add(
            TVEntity(
                83097,
                "The Promised Neverland",
                "2019-01-11",
                "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                9.1,
                "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama."
            )
        )

        tvShows.add(
            TVEntity(
                108261,
                "Mr. Queen",
                "2020-12-12",
                "/ozuyMnOO5pekDklyPpUL1Htkuzy.jpg",
                9.0,
                "In the present day, Jang Bong Hwan works as a chef at the President's Blue House. She has a free spirit, but her spirit somehow finds its way into the body of Queen Kim So Yong in the Joseon period.\n\nKing Cheol Jong has secrets. He seems like a figurehead, who is gentle and easygoing. In fact, he hides his strong aspects. Queen Sunwon is the late King Sunjo’s wife. She wields the true power in the country and, thus, relegates King Cheol Jong as just a figurehead. Kim Jwa Geun is Queen Sunwon’s younger brother. He is extremely ambitious."            )
        )

        return tvShows
    }


    fun generateRemoteMovies(): ArrayList<MovieRemote> {

        val remoteMovie = ArrayList<MovieRemote>()

        remoteMovie.add(
            MovieRemote(
                id = 19404,
                title = "Dilwale Dulhania Le Jayenge",
                date = "1995-10-20",
                image = "/2CAL2433ZeIihfX1Hb2139CX0pW.jpg",
                rate = 8.7,
                desc = "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga."
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 278,
                title = "The Shawshank Redemption",
                date = "1994-09-23",
                image = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                rate = 8.7,
                desc = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope."
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 724089,
                title = "Gabriel's Inferno Part II",
                date = "2020-07-31",
                image = "/irlfhYtHfhZuYpsq2LAoh308NFe.jpg",
                rate =  8.7,
                desc = "Professor Gabriel Emerson finally learns the truth about Julia Mitchell's identity, but his realization comes a moment too late. Julia is done waiting for the well-respected Dante specialist to remember her and wants nothing more to do with him. Can Gabriel win back her heart before she finds love in another's arms?"
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 238,
                title = "The Godfather",
                date = "1972-03-14",
                image =  "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
                rate =  8.7,
                desc = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family. When organized crime family patriarch, Vito Corleone barely survives an attempt on his life, his youngest son, Michael steps in to take care of the would-be killers, launching a campaign of bloody revenge."
            )
        )

        remoteMovie.add(
            MovieRemote(
                id = 761053,
                title = "Gabriel's Inferno Part III",
                date = "2020-11-19",
                image = "/fYtHxTxlhzD4QWfEbrC1rypysSD.jpg",
                rate = 8.7,
                desc = "The final part of the film adaption of the erotic romance novel Gabriel's Inferno written by an anonymous Canadian author under the pen name Sylvain Reynard.  Watch now : https://stream4k.xyz"            )
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
                image = "/qG59J1Q7rpBc1dvku4azbzcqo8h.jpg",
                rate = 9.4,
                desc = "I Am Not An Animal is an animated comedy series about the only six talking animals in the world, whose cosseted existence in a vivisection unit is turned upside down when they are liberated by animal rights activists."
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 88040,
                title = "Given",
                date = "2019-07-12",
                image = "/7gbmM2NWcqZONbp65HUWDf4wr0Q.jpg",
                rate = 9.1,
                desc = "Tightly clutching his Gibson guitar, Mafuyu Satou steps out of his dark apartment to begin another day of his high school life. While taking a nap in a quiet spot on the gymnasium staircase, he has a chance encounter with fellow student Ritsuka Uenoyama, who berates him for letting his guitar's strings rust and break. Noticing Uenoyama's knowledge of the instrument, Satou pleads for him to fix it and to teach him how to play. Uenoyama eventually agrees and invites him to sit in on a jam session with his two band mates: bassist Haruki Nakayama and drummer Akihiko Kaji."
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 83095,
                title = "The Rising of the Shield Hero",
                date = "2019-01-09",
                image = "/qSgBzXdu6QwVVeqOYOlHolkLRxZ.jpg",
                rate = 9.1,
                desc = "Iwatani Naofumi was summoned into a parallel world along with 3 other people to become the world's Heroes. Each of the heroes respectively equipped with their own legendary equipment when summoned, Naofumi received the Legendary Shield as his weapon. Due to Naofumi's lack of charisma and experience he's labeled as the weakest, only to end up betrayed, falsely accused, and robbed by on the third day of adventure. Shunned by everyone from the king to peasants, Naofumi's thoughts were filled with nothing but vengeance and hatred. Thus, his destiny in a parallel World begins..."
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 83097,
                title = "The Promised Neverland",
                date = "2019-01-11",
                image = "/uAjMQlbPkVHmUahhCouANlHSDW2.jpg",
                rate = 9.1,
                desc = "Surrounded by a forest and a gated entrance, the Grace Field House is inhabited by orphans happily living together as one big family, looked after by their \"Mama,\" Isabella. Although they are required to take tests daily, the children are free to spend their time as they see fit, usually playing outside, as long as they do not venture too far from the orphanage — a rule they are expected to follow no matter what. However, all good times must come to an end, as every few months, a child is adopted and sent to live with their new family... never to be heard from again.\n\nHowever, the three oldest siblings have their suspicions about what is actually happening at the orphanage, and they are about to discover the cruel fate that awaits the children living at Grace Field, including the twisted nature of their beloved Mama."
            )
        )

        tvShowRemote.add(
            TVRemote(
                id = 108261,
                title = "Mr. Queen",
                date = "2020-12-12",
                image = "/ozuyMnOO5pekDklyPpUL1Htkuzy.jpg",
                rate = 9.0,
                desc = "In the present day, Jang Bong Hwan works as a chef at the President's Blue House. She has a free spirit, but her spirit somehow finds its way into the body of Queen Kim So Yong in the Joseon period.\n\nKing Cheol Jong has secrets. He seems like a figurehead, who is gentle and easygoing. In fact, he hides his strong aspects. Queen Sunwon is the late King Sunjo’s wife. She wields the true power in the country and, thus, relegates King Cheol Jong as just a figurehead. Kim Jwa Geun is Queen Sunwon’s younger brother. He is extremely ambitious."            )
        )

        return tvShowRemote
    }
}
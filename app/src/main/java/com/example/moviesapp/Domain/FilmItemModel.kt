package com.example.moviesapp.Domain

import java.io.Serializable

data class FilmItemModel(
    var Title: String="",
    var Description: String="",
    var Poster: String="",
    var Time: String="",
    var Trailer: String="",
    var Imdb: Double=0.0,
    var Year: Int=0,
    var Price: Int=0,
    var Genre: ArrayList<String> = ArrayList(),
    var Casts: ArrayList<CastModel> = ArrayList()

    //Serializable --> Bir film nesnesini bir aktiviteden diğerine göndermek için kullanılır.
):Serializable

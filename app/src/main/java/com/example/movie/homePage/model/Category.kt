package com.example.movie.homePage.model


/**
 *  Category will have a category title and a list of shows/movies inside it
 */
data class Category(val title: String? = "", val items: ArrayList<String> = arrayListOf())

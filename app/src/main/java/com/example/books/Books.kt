package com.example.books

import com.fasterxml.jackson.annotation.JsonProperty

data class Books(
    val author: String="",
    val country: String="",
    val imageLink: String="",
    val language: String="",
    val link: String="",
    val pages: Int=0,
    val title: String="",
    val year: Int=0)

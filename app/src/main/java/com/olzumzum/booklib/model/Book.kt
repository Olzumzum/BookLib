package com.olzumzum.bookslibrary.model

data class Book(
    val authors: List<Author>,
    val by_statement: String,
    val covers: List<Int>,
    val created: Created,
    val genres: List<String>,
    val key: String,
    val languages: List<Language>,
    val lccn: List<String>,
    val number_of_pages: Int,
    val pagination: String,
    val publish_country: String,
    val publishers: List<String>,
    val source_records: List<String>,
    val subject_place: List<String>,
    val subjects: List<String>,
    val title: String,
    val type: Type,
    val works: List<Work>
)
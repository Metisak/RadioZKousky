package com.radiozkousky.data

enum class Category(val title: String, val shortTitle: String) {
    PREDPISY("Radiokomunikační předpisy", "Předpisy"),
    PROVOZ("Radiokomunikační provoz", "Provoz"),
    ELEKTRO("Elektrotechnika a radiotechnika", "Elektro")
}

data class Question(
    val id: Int,
    val category: Category,
    val text: String,
    val answer: String
)

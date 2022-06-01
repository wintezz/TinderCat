package com.alexpetrov.tinder.presentation.utils

import com.alexpetrov.tinder.data.model.Cat

fun sortList(list: List<Cat>): List<Cat> {
    val newList = arrayListOf<Cat>()
    for (vote in list) {
        if (vote.value == 1)
            newList.add(vote)
    }
    return newList
}
package com.example.bookcatalogue.model

import com.example.bookcatalogue.R

data class Genre(val title: String, val value: String, val icon: Int) {
    companion object {

        private var myList: MutableList<Genre> = ArrayList()
        fun generateGenreList(): List<Genre>{
            myList.add(Genre("FICTION", "fiction", R.drawable.ic_fiction))
            myList.add(Genre("DRAMA", "drama", R.drawable.ic_drama))
            myList.add(Genre("HUMOR", "humor", R.drawable.ic_humour))
            myList.add(Genre("POLITICS", "politics", R.drawable.ic_politics))
            myList.add(Genre("PHILOSOPHY", "philosophy", R.drawable.ic_philosophy))
            myList.add(Genre("HISTORY", "history", R.drawable.ic_history))
            myList.add(Genre("ADVENTURE", "adventure", R.drawable.ic_adventure))

            return myList
        }
    }
}

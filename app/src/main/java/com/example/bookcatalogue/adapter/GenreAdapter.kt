package com.example.bookcatalogue.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.bookcatalogue.R
import com.example.bookcatalogue.model.Genre

class GenreAdapter(
        private val genreList: List<Genre>,
        private val ctx: Context,
        val genreFun: (genre: Genre) -> Unit):
        RecyclerView.Adapter<GenreAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val iconIv: ImageView = itemView.findViewById(R.id.icon_iv)
        private val genreTv: TextView = itemView.findViewById(R.id.genre_tv)
        private val selectedCl: CardView = itemView.findViewById(R.id.selected_row)

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(genre: Genre, ctx: Context, genreFun: (genre: Genre) -> Unit) {
            iconIv.setImageDrawable(ctx.getDrawable(genre.icon))
            genreTv.text = genre.title

            selectedCl.setOnClickListener(View.OnClickListener {
                genreFun(genre)
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_genre, parent, false);
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(genreList[position], ctx, genreFun)
    }

    override fun getItemCount(): Int = genreList.size
}
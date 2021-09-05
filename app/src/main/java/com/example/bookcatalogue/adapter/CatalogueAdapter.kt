package com.example.bookcatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookcatalogue.R
import com.example.bookcatalogue.model.Formats
import com.example.bookcatalogue.model.Results

class CatalogueAdapter(val bookList: List<Results>, val ctx: Context, val openDoc: (format: Formats) -> Unit): RecyclerView.Adapter<CatalogueAdapter.MyViewHolder>() {
    
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookPic: ImageView = itemView.findViewById(R.id.book_pic_tv)
        private val bookNameTv: TextView = itemView.findViewById(R.id.book_name_tv)
        private val authorNameTv: TextView = itemView.findViewById(R.id.author_tv);
        private val selectedRowCl: ConstraintLayout = itemView.findViewById(R.id.selected_row)

        fun bind(book: Results, openDoc: (format: Formats) -> Unit) {
            Glide.with(itemView.context)
                .load(book.formats.imageJpeg).thumbnail(0.5f)
                .into(bookPic)

            bookNameTv.text = book.title
            authorNameTv.text = book.authors[0].name

            selectedRowCl.setOnClickListener(View.OnClickListener {
                openDoc(book.formats)
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_catalogue,
            parent,
            false
        )

        val layoutParams: ViewGroup.LayoutParams = view.getLayoutParams()
        layoutParams.width = (parent.width * 0.33).toInt()
        view.setLayoutParams(layoutParams)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(bookList[position], openDoc)
    }

    override fun getItemCount(): Int = bookList.size
}
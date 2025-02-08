package com.example.themovielibrary.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovielibrary.home.api.Results
import com.example.themovielibrary.databinding.ItemMoviesBinding

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {
        var movies = ArrayList<Results>()
        fun setMovieList(movies: ArrayList<Results>) {
            this.movies = movies
            notifyDataSetChanged()
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemMoviesBinding.inflate(inflater, parent, false)
            return MainViewHolder(binding)
        }
        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            val movie = movies[position]
            holder.binding.name.text = movie.title
            Glide.with(holder.itemView.context).load(IMAGE_BASE_URL+movie.posterPath).into(holder.binding.imageview)
        }
        override fun getItemCount(): Int {
            return movies.size
        }
    }

    class MainViewHolder(val binding: ItemMoviesBinding) : RecyclerView.ViewHolder(binding.root) {
    }
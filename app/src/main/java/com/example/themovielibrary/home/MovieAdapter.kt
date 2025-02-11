package com.example.themovielibrary.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themovielibrary.R
import com.example.themovielibrary.databinding.ItemMoviesBinding
import com.example.themovielibrary.home.api.Movie

class MainAdapter(private val isFavorite: Boolean, private val onClick: (movie: Movie) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {
    private var movies = mutableListOf<Movie>()

    fun setMovieList(newMovies: List<Movie>) {
        movies.clear()
        movies.addAll(newMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MainViewHolder(
        ItemMoviesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]

        holder.bind(movie)
        holder.itemView.setOnClickListener {
            val updatedMovie = movie.copy(isFavorite = !movie.isFavorite)
            if (isFavorite && !updatedMovie.isFavorite) {
                movies.removeAt(position)
            } else {
                movies[position] = updatedMovie
            }
            notifyItemChanged(position)

            onClick(updatedMovie)
        }

    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(private val binding: ItemMoviesBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.apply {
            name.text = movie.title
            Glide.with(itemView.context).load(IMAGE_BASE_URL + movie.posterPath).into(imageview)
            imgFavorite.setImageResource(
                if (movie.isFavorite) R.drawable.ic_favorite else R.drawable.ic_unfavorite
            )
        }
    }
}
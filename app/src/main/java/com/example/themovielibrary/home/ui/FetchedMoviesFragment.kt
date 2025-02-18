package com.example.themovielibrary.home.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovielibrary.R
import com.example.themovielibrary.databinding.FragmentFetchedMoviesBinding
import com.example.themovielibrary.home.viewmodel.MovieViewModel
import com.example.themovielibrary.home.api.MovieRepository
import com.example.themovielibrary.home.dagger.NetworkModule
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FetchedMoviesFragment : Fragment() {

    private lateinit var mBinding: FragmentFetchedMoviesBinding
    private val viewModel: MovieViewModel by viewModels()
    /*private val viewModel: MovieViewModel by lazy {
             ViewModelProvider(this)[MovieViewModel::class.java]
    }*/
    private val mainAdapter: MainAdapter by lazy {
        MainAdapter(isFavorite) {
            viewModel.updateFavorite(movie = it)
        }
    }
    private var isFavorite: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFetchedMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpObservers()
        setUpClickListeners()
    }

    private fun setUpRecyclerView() {
        val orientation = this.resources.configuration.orientation
        mBinding.recyclerview.apply {
            layoutManager = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                GridLayoutManager(requireActivity(), 2)
            } else {
                GridLayoutManager(requireActivity(), 4)
            }
            adapter = mainAdapter
        }
    }

    private fun setUpObservers() {
        viewModel.movieList.observe(viewLifecycleOwner) { list ->
            if (isFavorite) {
                mainAdapter.setMovieList(list.filter { it.isFavorite })
            } else {
                mainAdapter.setMovieList(list)
            }
        }
    }

    private fun setUpClickListeners() {
        mBinding.tvFilter.apply {
            setOnClickListener {
                if (isFavorite) {
                    text = requireActivity().getString(R.string.all_movies)
                    viewModel.movieList.value?.let { it1 -> mainAdapter.setMovieList(it1) }
                } else {
                    text = requireActivity().getString(R.string.favorite_movies)
                    viewModel.movieList.value?.let { list ->
                        mainAdapter.setMovieList(list.filter { it.isFavorite })
                    }
                }
                isFavorite = !isFavorite
            }
        }
    }

}

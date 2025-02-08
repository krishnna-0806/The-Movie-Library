package com.example.themovielibrary.home

import android.content.ContentValues.TAG
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themovielibrary.databinding.FragmentFetchedMoviesBinding
import com.example.themovielibrary.home.api.ApiService
import com.example.themovielibrary.home.api.MovieRepository
import com.example.themovielibrary.home.api.ViewModelFactory

class FetchedMoviesFragment : Fragment() {

    private lateinit var mBinding: FragmentFetchedMoviesBinding
    private lateinit var viewModel: MovieViewModel
    private val retrofitService = ApiService.getInstance()
    private val adapter = MainAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFetchedMoviesBinding.inflate(inflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchedNewlyMovies()

    }

    private fun fetchedNewlyMovies() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(MovieRepository(retrofitService))
        )[MovieViewModel::class.java]

        val orientation = this.resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT){
            mBinding.recyclerview.layoutManager = GridLayoutManager(requireActivity(), 2)
        }
        else{
            mBinding.recyclerview.layoutManager = GridLayoutManager(requireActivity(), 4)
        }

        mBinding.recyclerview.adapter = adapter
        viewModel.movieList.observe(requireActivity(), Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it.results)
        })
        viewModel.errorMessage.observe(requireActivity(), Observer {})
        viewModel.getAllMovies()
    }
}

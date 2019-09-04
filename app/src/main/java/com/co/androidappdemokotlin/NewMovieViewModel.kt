package com.co.androidappdemokotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.co.androidappdemokotlin.data.AppDatabase
import com.co.androidappdemokotlin.data.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewMovieViewModel(application : Application) : AndroidViewModel(application) {

    private val mDb: AppDatabase? = AppDatabase.getInstance(application)

    val allMovies = MutableLiveData<List<Movie>>()

    fun storeMovie(title: String) {
        val movie = Movie()
        movie.name = title

        GlobalScope.launch {
            mDb?.movieDao()?.insert(movie)
        }
    }

    fun retrieveMovies(): LiveData<List<Movie>>{

        GlobalScope.launch {
            val  list = mDb?.movieDao()?.getAll()
            Timber.i("retrieveMovies list count {${list?.size}}")
            allMovies.postValue(list)
        }
        return  allMovies
    }

}
package com.co.androidappdemokotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), NewMovieFragment.OnFragmentInteractionListener, MovieListFragment.OnFragmentInteractionListener {

    override fun goToMovieListFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.flContent, MovieListFragment.newInstance())
        transaction.commit()
    }

    override fun goToNewMovieFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.flContent, NewMovieFragment.newInstance())
        transaction.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            goToMovieListFragment()
        }
    }
}

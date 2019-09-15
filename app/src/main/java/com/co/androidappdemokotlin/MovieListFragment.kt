package com.co.androidappdemokotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MovieListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MovieListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MovieListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    private lateinit var mViewModel: NewMovieViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(NewMovieViewModel::class.java)

        mViewModel.retrieveMovies().observe(this, Observer {
            Timber.i("Received the movies ${it.size}")

            rvList.adapter = MovieRecyclerAdapter(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList.layoutManager = LinearLayoutManager(activity)

        btnAdd.setOnClickListener {
            listener?.goToNewMovieFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        fun goToNewMovieFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MovieListFragment()
    }
}

package com.co.androidappdemokotlin

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_new_movie.*

// TODO: Rename parameter arguments, choose names that match

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [NewMovieFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [NewMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class NewMovieFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a ViewModel the first time the system calls an activity's onCreate() method.
        // Re-created activities receive the same MyViewModel instance created by the first activity.
        val model = ViewModelProviders.of(this).get(NewMovieViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button.setOnClickListener {
            val input = editText.text.toString().trim()
            if (input.isEmpty()){
                Toast.makeText(activity, "Title required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (input.length>30){
                Toast.makeText(activity, "Title too long", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            Toast.makeText(activity, "$input entered", Toast.LENGTH_SHORT).show()
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
        fun goToMovieListFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NewMovieFragment()
    }
}

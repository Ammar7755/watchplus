package com.example.movie.homePage.view

import VerticalAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.homePage.viewModel.HomeViewModel
import com.example.movie.utils.SharedPrefConstants
import com.example.movie.utils.SharedPrefsManager

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    val verticalAdapter = VerticalAdapter()

    companion object  {
        const val TAG = "HomePageFragment"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnLogout = view.findViewById<ImageView>(R.id.btn_logout)

        // handle vertical rec
        handleVerticalRecyclerView()

        // call api
        homeViewModel.getMovie()

        // observe api result
        homeViewModel.movieObserver.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                verticalAdapter.updateRecyclerViewData(movies)
            }
        }

        btnLogout.setOnClickListener {
            SharedPrefsManager.setBoolean(SharedPrefConstants.IS_LOGGED_IN, false)
            // redirect user to authLanding
//            childFragmentManager.popBackStack()
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun handleVerticalRecyclerView() {
        view?.let { view ->
            val recyclerView: RecyclerView = view.findViewById(R.id.rec_vertical)
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recyclerView.adapter = verticalAdapter
        }
    }
}
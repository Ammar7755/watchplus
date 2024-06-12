package com.example.movie.homePage.view

import CategoriesAdapter
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.homePage.model.Category
import com.example.movie.homePage.viewModel.HomeViewModel

class HomePageFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private val homeViewModel: HomeViewModel by viewModels()
    val categoriesAdapter = CategoriesAdapter()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // handle vertical rec
        handleVerticalRecyclerView()

        // call api
        homeViewModel.getMovie()

        // observe api result
        homeViewModel.movieObserver.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                categoriesAdapter.updateRecyclerViewData(movies)
            }
        }
    }

    private fun handleVerticalRecyclerView() {
        view?.let { view ->
            val recyclerView: RecyclerView = view.findViewById(R.id.rec_categories)
            recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recyclerView.adapter = categoriesAdapter
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomePageFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
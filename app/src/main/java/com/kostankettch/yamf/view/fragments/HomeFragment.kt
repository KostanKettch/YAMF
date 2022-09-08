package com.kostankettch.yamf.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kostankettch.yamf.domain.Cinema
import com.kostankettch.yamf.R
import com.kostankettch.yamf.view.rv_adapters.SpaceDecor
import com.kostankettch.yamf.databinding.FragmentHomeBinding
import com.kostankettch.yamf.utils.AnimationHelper
import com.kostankettch.yamf.view.MainActivity
import com.kostankettch.yamf.view.rv_adapters.MovieListRecyclerAdapter
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merge_home_screen_content.main_recycler
import kotlinx.android.synthetic.main.merge_home_screen_content.search_view
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var moviesAdapter: MovieListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding

    private val viewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(HomeFragmentViewModel::class.java)
    }

    private var moviesDB = listOf<Cinema>()
        set(value) {
            if (field == value) return
            field = value
            moviesAdapter.addItems(field)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(
            home_fragment_root,
            requireActivity(),
            1
        )
        initSearchView()

        initPullToRefresh()

        initRecycler()

        viewModel.moviesListLiveData.observe(viewLifecycleOwner, Observer<List<Cinema>> {
            moviesDB = it
            moviesAdapter.addItems(it)
        })
    }

    private fun initPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            moviesAdapter.items.clear()
            viewModel.getMovies()
            binding.pullToRefresh.isRefreshing = false
        }
    }


    private fun initSearchView() {
        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    moviesAdapter.addItems(moviesDB)
                    return true
                }
                val result = moviesDB.filter {
                    it.title.toLowerCase(Locale.getDefault())
                        .contains(newText.toLowerCase(Locale.getDefault()))
                }
                moviesAdapter.addItems(result)
                return true
            }

        })
    }

    private fun initRecycler() {
        main_recycler.apply {
            moviesAdapter =
                MovieListRecyclerAdapter(object : MovieListRecyclerAdapter.OnItemClickListener {
                    override fun click(cinema: Cinema) {
                        (requireActivity() as MainActivity).launchDetailsFragment(cinema)
                    }
                })
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = SpaceDecor(8)
            addItemDecoration(decorator)
        }

    }

}
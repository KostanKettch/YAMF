package com.kostankettch.yamf.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.databinding.FragmentHomeBinding
import com.kostankettch.yamf.utils.AnimationHelper
import com.kostankettch.yamf.utils.AutoDisposable
import com.kostankettch.yamf.utils.addTo
import com.kostankettch.yamf.view.MainActivity
import com.kostankettch.yamf.view.rv_adapters.MovieListRecyclerAdapter
import com.kostankettch.yamf.view.rv_adapters.SpaceDecor
import com.kostankettch.yamf.viewmodel.HomeFragmentViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var moviesAdapter: MovieListRecyclerAdapter
    private lateinit var binding: FragmentHomeBinding
    private val autoDisposable = AutoDisposable()

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
        autoDisposable.bindTo(lifecycle)
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
            binding.homeFragmentRoot,
            requireActivity(),
            1
        )
        initSearchView()

        initPullToRefresh()

        initRecycler()

        viewModel.moviesListData
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                moviesDB = list
                moviesAdapter.addItems(list)
            }
            .addTo(autoDisposable)
        viewModel.showProgressBar
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.progressBar.isVisible = it
            }
            .addTo(autoDisposable)
    }


    private fun initPullToRefresh() {
        binding.pullToRefresh.setOnRefreshListener {
            moviesAdapter.items.clear()
            viewModel.getMovies()
            binding.pullToRefresh.isRefreshing = false
        }
    }


    private fun initSearchView() {
        binding.searchView.setOnClickListener {
            binding.searchView.isIconified = false
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

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
        binding.mainRecycler.apply {
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
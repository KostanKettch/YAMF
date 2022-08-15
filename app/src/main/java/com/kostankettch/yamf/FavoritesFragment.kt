package com.kostankettch.yamf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kostankettch.yamf.databinding.FragmentFavoritesBinding
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var moviesAdapter: MovieListRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(favorites_fragment_root, requireActivity(), 2)

        val favoritesList: List<Cinema> = emptyList()

        binding.favoritesRecycler.apply {
            moviesAdapter = MovieListRecyclerAdapter(object : MovieListRecyclerAdapter.OnItemClickListener {
                    override fun click(cinema: Cinema) {
                        (requireActivity() as MainActivity).launchDetailsFragment(cinema)
                    }
                })
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = SpaceDecor(8)
            addItemDecoration(decorator)
        }
        moviesAdapter.addItems(favoritesList)
    }
}
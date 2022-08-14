package com.kostankettch.yamf

import android.os.Bundle
import androidx.transition.*
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionSet
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.merge_home_screen_content.main_recycler
import kotlinx.android.synthetic.main.merge_home_screen_content.search_view
import java.util.*

class HomeFragment : Fragment() {
    private lateinit var moviesAdapter: MovieListRecyclerAdapter
    private val moviesDB = listOf(
        Cinema(
            "Arcane: League of Legends",
            R.drawable.arcane,
            "Set in utopian Piltover and the oppressed underground of Zaun, the story follows the origins of two iconic League champions-and the power that will tear them apart.",
            9.0f,
            false
        ),
        Cinema(
            "Blade Runner 2049",
            R.drawable.bladerunner2049,
            "Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who's been missing for thirty years.",
            8.0f,
            false
        ),
        Cinema(
            "Back to the Future",
            R.drawable.bttf,
            "Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown.",
            8.5f,
            false
        ),
        Cinema(
            "Guardians of the Galaxy Vol. 2",
            R.drawable.guardians2,
            "The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego.",
            7.6f,
            false
        ),
        Cinema(
            "Raiders of the Lost Ark",
            R.drawable.indy,
            "In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Adolf Hitler's Nazis can obtain its awesome powers.",
            8.4f,
            false
        ),
        Cinema(
            "Logan",
            R.drawable.logan,
            "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety.",
            8.1f,
            false
        ),
        Cinema(
            "Spider-Man: Into the Spider-Verse",
            R.drawable.spiderverse,
            "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities.",
            8.4f,
            false
        ),
        Cinema(
            "Stranger Things",
            R.drawable.strangerthings3,
            "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back.",
            8.7f,
            false
        ),
        Cinema(
            "The Empire Strikes Back",
            R.drawable.swesb,
            "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued across the galaxy by Darth Vader and bounty hunter Boba Fett.",
            8.7f,
            false
        ),
        Cinema(
            "The Mandalorian",
            R.drawable.swmando,
            "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic.",
            8.7f,
            false
        ),
        Cinema(
            "Thor: Ragnarok",
            R.drawable.thor3,
            "Imprisoned on the planet Sakaar, Thor must race against time to return to Asgard and stop Ragnarök, the destruction of his world, at the hands of the powerful and ruthless villain Hela.",
            7.9f,
            false
        ),
        Cinema(
            "WandaVision",
            R.drawable.wandavision,
            "Blends the style of classic sitcoms with the MCU, in which Wanda Maximoff and Vision - two super-powered beings living their ideal suburban lives - begin to suspect that everything is not as it seems.",
            7.9f,
            false
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(home_fragment_root,requireActivity(), 1)

        val scene = Scene.getSceneForLayout(home_fragment_root, R.layout.merge_home_screen_content, requireContext())
        val searchSlide = Slide(Gravity.TOP).addTarget(R.id.search_view)
        val recyclerSlide = Slide(Gravity.BOTTOM).addTarget(R.id.main_recycler)
        val disclaimerFade = Fade().addTarget(R.id.disclaimer)
        val customTransition = TransitionSet().apply {
            duration = 500
            addTransition(recyclerSlide)
            addTransition(searchSlide)
            addTransition(disclaimerFade)
        }
        TransitionManager.go(scene, customTransition)

        search_view.setOnClickListener {
            search_view.isIconified = false
        }

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText : String): Boolean {
                if(newText.isEmpty()) {
                    moviesAdapter.addItems(moviesDB)
                    return true
                }
                val result = moviesDB.filter {
                    it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault()))
                }
                moviesAdapter.addItems(result)
                return true
            }

        })


        main_recycler.apply {
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
        moviesAdapter.addItems(moviesDB)
    }

}
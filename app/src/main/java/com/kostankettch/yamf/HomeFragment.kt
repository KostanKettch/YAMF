package com.kostankettch.yamf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    private lateinit var moviesAdapter: MovieListRecyclerAdapter
    private val moviesDB = listOf(
        Cinema(
            "Arcane: League of Legends",
            R.drawable.arcane,
            "Set in utopian Piltover and the oppressed underground of Zaun, the story follows the origins of two iconic League champions-and the power that will tear them apart."
        ),
        Cinema(
            "Blade Runner 2049",
            R.drawable.bladerunner2049,
            "Young Blade Runner K's discovery of a long-buried secret leads him to track down former Blade Runner Rick Deckard, who's been missing for thirty years."
        ),
        Cinema(
            "Back to the Future",
            R.drawable.bttf,
            "Marty McFly, a 17-year-old high school student, is accidentally sent thirty years into the past in a time-traveling DeLorean invented by his close friend, the eccentric scientist Doc Brown."
        ),
        Cinema(
            "Guardians of the Galaxy Vol. 2",
            R.drawable.guardians2,
            "The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father the ambitious celestial being Ego."
        ),
        Cinema(
            "Raiders of the Lost Ark",
            R.drawable.indy,
            "In 1936, archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before Adolf Hitler's Nazis can obtain its awesome powers."
        ),
        Cinema(
            "Logan",
            R.drawable.logan,
            "In a future where mutants are nearly extinct, an elderly and weary Logan leads a quiet life. But when Laura, a mutant child pursued by scientists, comes to him for help, he must get her to safety."
        ),
        Cinema(
            "Spider-Man: Into the Spider-Verse",
            R.drawable.spiderverse,
            "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other dimensions to stop a threat for all realities."
        ),
        Cinema(
            "Stranger Things",
            R.drawable.strangerthings3,
            "When a young boy disappears, his mother, a police chief and his friends must confront terrifying supernatural forces in order to get him back."
        ),
        Cinema(
            "The Empire Strikes Back",
            R.drawable.swesb,
            "After the Rebels are brutally overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued across the galaxy by Darth Vader and bounty hunter Boba Fett."
        ),
        Cinema(
            "The Mandalorian",
            R.drawable.swmando,
            "The travels of a lone bounty hunter in the outer reaches of the galaxy, far from the authority of the New Republic."
        ),
        Cinema(
            "Thor: Ragnarok",
            R.drawable.thor3,
            "Imprisoned on the planet Sakaar, Thor must race against time to return to Asgard and stop Ragnarök, the destruction of his world, at the hands of the powerful and ruthless villain Hela."
        ),
        Cinema(
            "WandaVision",
            R.drawable.wandavision,
            "Blends the style of classic sitcoms with the MCU, in which Wanda Maximoff and Vision - two super-powered beings living their ideal suburban lives - begin to suspect that everything is not as it seems."
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
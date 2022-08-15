package com.kostankettch.yamf

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kostankettch.yamf.databinding.FragmentCollectionsBinding
import kotlinx.android.synthetic.main.fragment_collections.*

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AnimationHelper.performFragmentCircularRevealAnimation(collections_fragment_root, requireActivity(), 4)
    }
}
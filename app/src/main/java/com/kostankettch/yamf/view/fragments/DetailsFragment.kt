package com.kostankettch.yamf.view.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kostankettch.yamf.data.entity.Cinema
import com.kostankettch.yamf.R
import com.kostankettch.yamf.data.ApiConstants
import com.kostankettch.yamf.databinding.FragmentDetailsBinding
import com.kostankettch.yamf.viewmodel.DetailFragmentViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*


class DetailsFragment : Fragment() {
    private lateinit var cinema: Cinema
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailFragmentViewModel by viewModels()
    private val scope = CoroutineScope(Dispatchers.IO)


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCinemaDetails()

        binding.detailsFabFavorites.setOnClickListener {
            if (!cinema.isFavorite) {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_24)
                cinema.isFavorite = true
            } else {
                binding.detailsFabFavorites.setImageResource(R.drawable.ic_round_star_border_24)
                cinema.isFavorite = false
            }
        }
        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Check out this film: ${cinema.title} \n \n ${cinema.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }

        binding.detailsFabDownloadWp.setOnClickListener {
            performAsyncLoadOfPoster()
        }
    }

    private fun setCinemaDetails() {
        cinema = arguments?.get("cinema") as Cinema

        binding.detailsToolbar.title = cinema.title

        Glide.with(this)
            .load(ApiConstants.IMAGE_URL + "w780" + cinema.poster)
            .centerCrop()
            .into(binding.detailsPoster)
        binding.detailsDescription.text = cinema.description
        binding.detailsFabFavorites.setImageResource(
            if (cinema.isFavorite) R.drawable.ic_round_star_24
            else R.drawable.ic_round_star_border_24
        )
    }

    private fun performAsyncLoadOfPoster() {
        if (!checkPermission()) {
            requestPermission()
            return
        }
        MainScope().launch {
            binding.progressBar.isVisible = true
            val job = scope.async {
                viewModel.loadWallpaper(ApiConstants.IMAGE_URL + "original" + cinema.poster)
            }
            saveToGallery(job.await())
            Snackbar.make(
                binding.root,
                R.string.downloaded_to_gallery,
                Snackbar.LENGTH_LONG
            )
                .setAction(R.string.open) {
                    val intent = Intent()
                    intent.action = Intent.ACTION_VIEW
                    intent.type = "image/*"
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                .show()
            binding.progressBar.isVisible = false
        }
    }

    private fun checkPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            1
        )
    }

    private fun saveToGallery(bitmap: Bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, cinema.title.handleSingleQuote())
                put(
                    MediaStore.Images.Media.DISPLAY_NAME,
                    cinema.title.handleSingleQuote()
                )
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis())
                put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CinemaSearchApp")
            }
            val contentResolver = requireActivity().contentResolver
            val uri = contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues
            )
            val outputStream = contentResolver.openOutputStream(uri!!)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream?.close()
        } else {
            @Suppress("DEPRECATION")
            MediaStore.Images.Media.insertImage(
                requireActivity().contentResolver,
                bitmap,
                cinema.title.handleSingleQuote(),
                cinema.description.handleSingleQuote()
            )
        }
    }

    private fun String.handleSingleQuote(): String {
        return this.replace("'", "")
    }

}
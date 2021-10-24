package com.example.flickert.ui.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.flickert.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Change l'image et le titre
        viewModel.photos.observe(viewLifecycleOwner,
            Observer { data ->

                val image = layout.findViewById<ImageView>(R.id.image_hp)
                val titre = layout.findViewById<TextView>(R.id.title_image)
                val boutonNext = layout.findViewById<Button>(R.id.buttonNext)

                val firstUrl = "https://farm" + data.photo.get(viewModel.index).farm + ".staticflickr.com/" +
                        data.photo.get(viewModel.index).server + "/" + data.photo.get(viewModel.index).id+"_"+data.photo.get(viewModel.index).secret + ".jpg"

                titre.text = data.photo.get(viewModel.index).title + viewModel.url
                Glide.with(layout).load(firstUrl).into(image)



                boutonNext.setOnClickListener {
                    viewModel.nextImage()
                    titre.text = data.photo.get(viewModel.index).title
                    Glide.with(layout).load(viewModel.url).into(image)
                }

            })
        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}


package com.example.flickert.ui.ui.main

import android.annotation.SuppressLint
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
import androidx.navigation.Navigation
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
                val boutonAll = layout.findViewById<Button>(R.id.button_all_images)
                titre.text = data.photo.get(viewModel.index).title
                val FirstUrl="https://farm" + data.photo.get(viewModel.index).farm + ".staticflickr.com/" +
                        data.photo.get(viewModel.index).server + "/" + data.photo.get(viewModel.index).id+"_"+data.photo.get(viewModel.index).secret + ".jpg"

                Glide.with(layout).load(FirstUrl).into(image)

                image.setOnClickListener{
                    val action =MainFragmentDirections.mainTofull(viewModel.url)
                    Navigation.findNavController(image).navigate(action)
                }

                boutonNext.setOnClickListener {
                    viewModel.nextImage()
                    titre.text = data.photo.get(viewModel.index).title
                    Glide.with(layout).load(viewModel.url).into(image)
                    image.setOnClickListener{
                        val action =MainFragmentDirections.mainTofull(viewModel.url)
                        Navigation.findNavController(image).navigate(action)
                    }}

                boutonAll.setOnClickListener{
                    Navigation.findNavController(boutonAll).navigate(R.id.main_to_listFragment)
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


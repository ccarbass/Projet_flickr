package com.example.flickert.ui.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.flickert.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layout = inflater.inflate(R.layout.main_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // Change l'image et le titre
        viewModel.photos.observe(viewLifecycleOwner, Observer {data ->
            val image = layout.findViewById<ImageView>(R.id.image_hp)
            val titre = layout.findViewById<TextView>(R.id.title_image)
            titre.text = data.photo.get(viewModel.index).title
    })
    return layout}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
package com.example.flickert.ui.ui.full

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.flickert.R

class FullFragment : Fragment() {

    companion object {
        fun newInstance() = FullFragment()
    }

    private lateinit var viewModel: FullViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layout=inflater.inflate(R.layout.full_fragment, container, false)
        val image=layout.findViewById<ImageView>(R.id.photofull)
        val url=FullFragmentArgs.fromBundle(requireArguments()).url
        Glide.with(layout).load(url).into(image)


        return layout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FullViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
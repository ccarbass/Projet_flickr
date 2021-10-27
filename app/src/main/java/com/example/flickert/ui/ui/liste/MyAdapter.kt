package com.example.flickert.ui.ui.liste

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickert.R
import com.example.flickert.model.Photo
import com.example.flickert.ui.ui.main.MainFragmentDirections

class MyAdapter(val photos : List<Photo>, val callback: (Int)->Unit) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    // un ViewHolder permet de stocker la vue de chaque item de la liste
    class MyViewHolder(val v: GridLayout) : RecyclerView.ViewHolder(v)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyViewHolder {
        val layout =LayoutInflater.from(parent.context).inflate(R.layout.photo,parent,false)
        val holder = MyViewHolder(layout as GridLayout)
        layout.setOnClickListener {
            // ici on sait que l'utilisateur a cliqué sur l'item
            callback(holder.adapterPosition)
        }

        return holder
    }
    // appelé quand on doit peupler le ViewHolder avec le contenu de l'élément numéro "position"
    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val image = holder.v.findViewById<ImageView>(R.id.photo)
        val farm = photos[position].farm
        val server = photos[position].server
        val id =photos[position].id
        val secret = photos[position].secret
        Log.v("farm",farm)
        Log.v("server",server)
        Log.v("id",id)
        Log.v("secret",secret)

        val url = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg"

        Glide.with(holder.v).load(url).into(image)

        image.setOnClickListener{
            val action = ListFragmentDirections.listTofull(url)
            Navigation.findNavController(holder.v).navigate(action)
        }
    }

    // appelé quand le recycerview a besoin de connaître la taille de la liste qu'il doit afficher
    override fun getItemCount(): Int = photos.size
}
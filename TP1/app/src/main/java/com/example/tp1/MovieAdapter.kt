package com.example.tp1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * In this adapter we populate the recycler view with movie posters
 */

class MovieAdapter(var mMovies: List<Movie>, private val listener: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    // Get the view that we'll modify in this adapter
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var posterImageView: ImageView = itemView.findViewById(R.id.imageViewPoster)
        var noPosterTextView: TextView= itemView.findViewById(R.id.noPosterTextView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val movieView: View = inflater.inflate(R.layout.popular_items, parent, false)

        return ViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie: Movie = mMovies[position]

        // Display the poster and add the title if there is no poster
        if (movie.poster_path == null){
            Glide.with(holder.posterImageView).load(R.drawable.not_found).into(holder.posterImageView)
            if (movie.title == null){
                holder.noPosterTextView.text = movie.original_title
            } else {
                holder.noPosterTextView.text = movie.title
            }

        }else{
            Glide.with(holder.posterImageView).load("https://image.tmdb.org/t/p/w500" + movie.poster_path).into(holder.posterImageView)
            holder.noPosterTextView.visibility = View.GONE

        }

        // Set a listener to redirect to the details of the movie when the user clicks on the poster
        holder.posterImageView.setOnClickListener {
            listener(mMovies[position])
        }
    }

    override fun getItemCount(): Int {
        return mMovies.size
    }
}

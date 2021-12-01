package com.example.td6

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SearchAdapter(repos: List<Repo>, private val listener: (Repo) -> Unit) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    var mRepos: List<Repo> = repos

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var repoNameElement = itemView.findViewById<TextView>(R.id.textViewRepoName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var text = mRepos[position].full_name?.split("/")

        holder.repoNameElement.text = Html.fromHtml((text?.get(0) ?: "ERROR").toString() + "/<b>" + (text?.get(1)?: "ERROR") + "</b>", Html.FROM_HTML_MODE_COMPACT)
        holder.repoNameElement.setOnClickListener { listener(mRepos[position]) }
    }

    override fun getItemCount(): Int {
        return mRepos.size
    }

}
package mobiledev.firstapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by Daria on 11.02.2018.
 */
class CustomAdapter(val repos: ArrayList<MainActivity.GitHubRepositoryInfo>)
    : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(repo: MainActivity.GitHubRepositoryInfo) {
            itemView.item.text = repo.name
        }

    }
}
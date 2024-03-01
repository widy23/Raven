package com.raven.home.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raven.home.R
import com.raven.home.databinding.ListNewsItemsBinding
import com.raven.home.db.NewsModelDB
import com.raven.home.domain.models.NewsModel
import com.raven.home.presentation.view.HomeFragment

class ListNewsAdapter(private val listNews: List<NewsModelDB>,private val  actionListener :HomeFragment):
    RecyclerView.Adapter<ListNewsAdapter.ListNewsVH>(){
    interface ListNewsListener {
        fun onNewsItemClick(item: NewsModelDB, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsVH {
        val binding = ListNewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNewsVH(binding)
    }

    override fun onBindViewHolder(holder: ListNewsVH, position: Int) {
        val card = listNews[position]
        holder.bind(card, actionListener, position)    }

    class ListNewsVH (private val binding: ListNewsItemsBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(
            modelNews: NewsModelDB,
            context: HomeFragment,
            position: Int,
        ) {

            with(binding){
                txtTitleNews.text= modelNews.titulo
                txtDate.text= "Published: ${modelNews.date}"
                if (modelNews.url.isNullOrBlank()){
                    binding.imgNews.setBackgroundResource(R.drawable.not_image)
                }
                Glide.with(context).load(modelNews.url).into(binding.imgNews)
                mainItemView.setOnClickListener{
                    context.onNewsItemClick(modelNews,position)

                }
            }
        }

    }



    override fun getItemCount(): Int {
       return listNews.size
    }
}
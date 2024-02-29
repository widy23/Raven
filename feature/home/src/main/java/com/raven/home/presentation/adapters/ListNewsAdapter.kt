package com.raven.home.presentation.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raven.home.databinding.ListNewsItemsBinding
import com.raven.home.domain.models.NewsModel
import com.raven.home.presentation.view.HomeFragment

class ListNewsAdapter(private val listNews: List<NewsModel>,private val  actionListener :HomeFragment):
    RecyclerView.Adapter<ListNewsAdapter.ListNewsVH>(){
    interface ListNewsListener {
        fun onNewsItemClick(item: NewsModel, position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNewsVH {
        val binding = ListNewsItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListNewsVH(binding)
    }

    override fun onBindViewHolder(holder: ListNewsVH, position: Int) {
        val card = listNews[position]
        holder.bind(card, actionListener, position,listNews)    }

    class ListNewsVH (private val binding: ListNewsItemsBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(
            modelNews: NewsModel,
            actionListener: HomeFragment,
            position: Int,
            listNews: List<NewsModel>
        ) {
            val urls =modelNews.media.last().metaData.last().urlImage
            Log.d("TAG", "bind:$urls ")
            with(binding){
                txtTitleNews.text= modelNews.titulo
                txtDate.text= "Published: ${modelNews.date}"
                txtAuthor.text = modelNews.autor
                txtContentNews.text= modelNews.contenido
                // Para cada objeto NewsModel, carga la última imagen de la lista media
                val imageUrl= modelNews.media.run {
                    last().metaData.last().urlImage

                }
                Glide.with(actionListener)
                    .load(imageUrl)
                    .into(imgNews)
                mainItemView.setOnClickListener{
                    actionListener.onNewsItemClick(modelNews,position)
                }
            }



        }

        }



    override fun getItemCount(): Int {
       return listNews.size
    }
}
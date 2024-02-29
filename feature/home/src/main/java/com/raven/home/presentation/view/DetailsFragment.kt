package com.raven.home.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.raven.home.databinding.FragmentDetailsBinding
import com.raven.home.presentation.viewmodel.HomeViewModel

class DetailsFragment : Fragment() {

    private val  viewModel :HomeViewModel by activityViewModels<HomeViewModel>()
    private var binding : FragmentDetailsBinding ?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.newsSelected.observe(viewLifecycleOwner){
            with(binding!!){
                txtTitleNews.text  =it.titulo
                txtAuthor.text =it.autor
                txtDate.text = it.date
                txtContentNews.text = it.contenido
                val imageUrl= it.media.run {
                    last().metaData.last().urlImage
                }
                Glide.with(this@DetailsFragment)
                    .load(imageUrl)
                    .into(imgNews)

            }
        }
    }

}
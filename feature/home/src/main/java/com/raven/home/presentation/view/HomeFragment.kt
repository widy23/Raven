package com.raven.home.presentation.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raven.home.R
import com.raven.home.data.remote.NetworkHelper
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.db.NewsModelDB
import com.raven.home.domain.models.NewsModel
import com.raven.home.presentation.adapters.ListNewsAdapter
import com.raven.home.presentation.adapters.ListNewsAdapter.ListNewsListener
import com.raven.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), ListNewsListener {
    private val viewModel: HomeViewModel by activityViewModels<HomeViewModel>()
    private var binding: HomeFragmentBinding? = null
    private var listNewsAdapter: ListNewsAdapter? = null
    private val periodItem = listOf("1", "7", "30")
    private val defaultPeriodNews = "1"
    private val TAG = "HomeFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkInternet()

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    private fun checkInternet() {
        if (NetworkHelper.checkInternetConnection(requireContext())) {
            callDefaultPeriod()
        } else {
            viewModel.getNewsFromDataBase()
        }
    }

    private fun callDefaultPeriod() {
        viewModel.activateShimmer(true)
        viewModel.updatePeriodAndFetchNews(defaultPeriodNews)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()

    }

    override fun onResume() {
        super.onResume()
        setUpDropDown()

    }

    private fun setUpDropDown() {
        val adapterDropDown = ArrayAdapter(requireContext(), R.layout.items_options, periodItem)
        binding!!.dropDownPeriod.setAdapter(adapterDropDown)
        binding!!.dropDownPeriod.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val itemPeriodSelected = parent.getItemAtPosition(position)
                viewModel.updatePeriodAndFetchNews(itemPeriodSelected.toString())
            }

        viewModel.loading.observe(viewLifecycleOwner) {
         //   if (it) removeShimmerAnimation()
        }
    }

    private fun removeShimmerAnimation() {
        with(binding!!) {
            linearLayout.visibility = View.VISIBLE
            layoutMenu.visibility = View.VISIBLE
            rcvListNews.visibility = View.VISIBLE

        }

    }

    private fun observeViewModel() {
        viewModel.news.observe(viewLifecycleOwner) {
            Log.d(TAG, "observeViewModel: $it")
            it?.let {
                setUpUiRecycler(it)
            }
        }
        viewModel.newsError.observe(viewLifecycleOwner){
            if (it !=null){
              showDialogError()

            }
        }
    }

    private fun showDialogError() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder
            .setMessage("Error Al cargar los datos ")
            .setTitle("Error")
            .setPositiveButton("Close") { dialog, which ->
                    dialog.dismiss()
            }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun setUpUiRecycler(it: List<NewsModelDB>) {
        listNewsAdapter = ListNewsAdapter(it, this)

        with(binding?.rcvListNews) {
            this?.setHasFixedSize(true)
            this?.layoutManager = LinearLayoutManager(context)
            this?.itemAnimator = DefaultItemAnimator()
            this?.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager(context).orientation
                )
            )
            this?.adapter = listNewsAdapter
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onNewsItemClick(item: NewsModelDB, position: Int) {
        Log.d(TAG, "onNewsItemClick: $item")
        viewModel.setSelectedNews(item)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
    }
}

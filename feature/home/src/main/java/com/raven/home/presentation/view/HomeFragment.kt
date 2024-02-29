package com.raven.home.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.raven.home.R
import com.raven.home.databinding.HomeFragmentBinding
import com.raven.home.domain.models.NewsModel
import com.raven.home.presentation.adapters.ListNewsAdapter
import com.raven.home.presentation.adapters.ListNewsAdapter.ListNewsListener
import com.raven.home.presentation.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), ListNewsListener {
    private var viewModel : HomeViewModel? = null
    private var binding : HomeFragmentBinding ? = null
    private var listNewsAdapter: ListNewsAdapter? = null
    private val periodItem = listOf("1", "7", "30")
    private val TAG="HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= HomeFragmentBinding.inflate(inflater,container,false)
        viewModel =ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel!!.updatePeriodAndFetchNews("1")
        return binding?.root
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
        val adapterDropDown  = ArrayAdapter(requireContext(),R.layout.items_options,periodItem)
        binding!!.dropDownPeriod.setAdapter(adapterDropDown)
        binding!!.dropDownPeriod.onItemClickListener = AdapterView.OnItemClickListener {
        parent, view, position, id ->
            val itemPeriodSelected = parent.getItemAtPosition(position)
            viewModel!!.updatePeriodAndFetchNews(itemPeriodSelected.toString())
        }
    }

    private fun observeViewModel() {
            viewModel?.news?.observe(viewLifecycleOwner){
                Log.d(TAG, "observeViewModel: $it")
                it?.let {
                    setUpUiRecycler(it)
                }
            }
        }

    @SuppressLint("SuspiciousIndentation")
    private fun setUpUiRecycler(it: List<NewsModel>) {
        listNewsAdapter = ListNewsAdapter(it, this)
            with(binding?.rcvListNews){
                this?.layoutManager = LinearLayoutManager(context)
                this?.itemAnimator = DefaultItemAnimator()
                this?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))
                this?.adapter = listNewsAdapter
            }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onNewsItemClick(item: NewsModel, position: Int) {
        Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)


    }


}

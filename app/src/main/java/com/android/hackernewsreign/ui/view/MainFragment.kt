package com.android.hackernewsreign.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.hackernewsreign.R
import com.android.hackernewsreign.databinding.FragmentMainBinding
import com.android.hackernewsreign.ui.adapter.HitListAdapter
import com.android.hackernewsreign.ui.viewmodel.HitViewModel
import com.android.hackernewsreign.utils.SwipeGesture
import com.android.hackernewsreign.utils.toast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(), HitListAdapter.ItemActionsListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val hitViewModel: HitViewModel by viewModels()
    lateinit var adapter: HitListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        initRecycler()
        hitViewModel.init()
        callObserver()
        callLoading()
        swipeToRefresh()
        showToast()

        return binding.root
    }

    private fun initRecycler() {
        adapter = HitListAdapter(arrayListOf(), this)
        binding.rvHitList.setHasFixedSize(true)
        binding.rvHitList.layoutManager = LinearLayoutManager(context)

        swipeToDelete()

        binding.rvHitList.adapter = adapter
        binding.rvHitList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    hitViewModel.getData(true)
                }
            }
        })
    }

    private fun swipeToDelete(){
        val swipeGesture = object: SwipeGesture(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction) {
                    ItemTouchHelper.LEFT -> {
                        val item = adapter.getHitModel(viewHolder.adapterPosition)
                        adapter.deleteItem(viewHolder.adapterPosition)
                        hitViewModel.saveHitDelete(item.id)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding.rvHitList)
    }

    private fun callObserver() {
        hitViewModel.hitModel.observe(viewLifecycleOwner, {
            adapter.addItems(it)
        })
    }

    private fun callLoading() {
        hitViewModel.isLoading.observe(viewLifecycleOwner, {
            binding.swipeRefreshLayout.isRefreshing = it
        })
    }

    private fun swipeToRefresh(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            hitViewModel.getData()
        }
    }

    private fun showToast(){
        hitViewModel.showToast.observe(viewLifecycleOwner, {
            requireContext().toast(getString(it))
        })
    }

    override fun onClickItem(v: View, url: String) {
        val bundle = Bundle()
        bundle.putString("url", url)
        Navigation.findNavController(v).navigate(R.id.itemWebViewFragment, bundle)
    }

    override fun webNotAvailable() {
        context?.toast(resources.getString(R.string.page_not_available))
    }

}
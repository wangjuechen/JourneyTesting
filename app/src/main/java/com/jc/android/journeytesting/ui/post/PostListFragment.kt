package com.jc.android.journeytesting.ui.post

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jc.android.journeytesting.R
import com.jc.android.journeytesting.databinding.MainFragmentBinding
import com.jc.android.journeytesting.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : Fragment(), PostListItemListener {

    private val postListViewModel: PostListViewModel by viewModels()
    private val postListAdapter = PostListAdapter()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.postListRecyclerView.apply {
            adapter = postListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

            val dividerItemDecoration =
                DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            dividerItemDecoration.setDrawable(context.getDrawable(R.drawable.recycler_view_divider)!!)
            addItemDecoration(dividerItemDecoration)
        }

        postListViewModel.postAndCommentsMediatorLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) postListAdapter.setData(it.data, this)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.search).actionView as SearchView
        setupSearchView(searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun postItemSelected(postId: String) {
        val action = PostListFragmentDirections.actionPostListFragmentToCommentsFragment(postId)
        findNavController().navigate(action)
    }

    private fun setupSearchView(searchView: SearchView) {
        searchView.apply {
            queryHint = getString(R.string.search_post_hint)
            isIconifiedByDefault = false
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    postListAdapter.filter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    postListAdapter.filter.filter(newText)
                    return false
                }
            })
        }
    }
}


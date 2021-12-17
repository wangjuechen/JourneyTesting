package com.jc.android.journeytesting.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jc.android.journeytesting.databinding.MainFragmentBinding

class PostListFragment : Fragment() {

    private val postListViewModel: PostListViewModel by viewModels()
    private val postListAdapter = PostListAdapter()

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

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

        postListViewModel.postListLiveData.observe(viewLifecycleOwner) { postList ->
            postListAdapter.setData(postList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PostListFragment()
    }
}
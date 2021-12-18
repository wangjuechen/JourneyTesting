package com.jc.android.journeytesting.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jc.android.journeytesting.R
import com.jc.android.journeytesting.databinding.MainFragmentBinding
import com.jc.android.journeytesting.ui.comment.CommentsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostListFragment : Fragment(), PostListItemListener {

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

        binding.postListRecyclerView.apply {
            adapter = postListAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL ,false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        postListViewModel.postListLiveData.observe(viewLifecycleOwner) { postList ->
            postListAdapter.setData(postList, this)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = PostListFragment()
    }

    override fun postItemSelected(postId: String) {
        val commentsFragment = CommentsFragment()
        val args = Bundle()
        args.putString("postId", postId);
        commentsFragment.arguments = args
        activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.container, commentsFragment, "commentsFragment")?.commit()
    }
}
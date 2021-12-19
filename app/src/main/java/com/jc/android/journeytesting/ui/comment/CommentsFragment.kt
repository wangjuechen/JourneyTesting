package com.jc.android.journeytesting.ui.comment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jc.android.journeytesting.databinding.CommentsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: CommentsFragmentBinding? = null
    private val binding get() = _binding!!

    private val commentViewModel: CommentViewModel by viewModels()
    private val commentsListAdapter = CommentListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CommentsFragmentBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle = arguments
        if (bundle == null) {
            Log.e("Comments screen", "CommentsFragment did not receive post information")
            return
        }

        commentViewModel.postId = CommentsFragmentArgs.fromBundle(bundle).postId

        binding.commentsListRecyclerView.apply {
            adapter = commentsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        commentViewModel.setupCommentListLiveData()
        commentViewModel.commentListLiveData.observe(viewLifecycleOwner) { commentList ->
            commentsListAdapter.setData(commentList ?: emptyList())
        }
    }
}
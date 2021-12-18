package com.jc.android.journeytesting.ui.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jc.android.journeytesting.databinding.CommentsFragmentBinding

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

        commentViewModel.postId.value = this.arguments?.getString("postId")

        binding.commentsListRecyclerView.apply {
            adapter = commentsListAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        commentViewModel.commentListLiveData.observe(viewLifecycleOwner) { commentList ->
            commentsListAdapter.setData(commentList ?: emptyList())
        }
    }
}
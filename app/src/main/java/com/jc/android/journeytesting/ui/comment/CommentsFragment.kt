package com.jc.android.journeytesting.ui.comment

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jc.android.journeytesting.databinding.CommentsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.appcompat.app.AppCompatActivity
import com.jc.android.journeytesting.R


@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private var _binding: CommentsFragmentBinding? = null
    private val binding get() = _binding!!

    private val commentViewModel: CommentViewModel by viewModels()
    private val commentsListAdapter = CommentListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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
        commentViewModel.setupCommentListLiveData()

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
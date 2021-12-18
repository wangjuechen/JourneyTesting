package com.jc.android.journeytesting.ui.comment

import androidx.lifecycle.*
import com.jc.android.journeytesting.data.CommentRepository
import com.jc.android.journeytesting.domain.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {
    var postId = MutableLiveData<String?>()
    val commentListLiveData: LiveData<List<Comment>?> = Transformations.switchMap(postId) {
        setupCommentListLiveData(it)
    }

    private fun setupCommentListLiveData(postId: String?): LiveData<List<Comment>?> {
        return liveData { commentRepository.getCommentsByPost(postId ?: "") }
    }
}
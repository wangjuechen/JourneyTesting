package com.jc.android.journeytesting.ui.comment

import androidx.lifecycle.*
import com.jc.android.journeytesting.data.CommentRepository
import com.jc.android.journeytesting.domain.Comment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(
    private val commentRepository: CommentRepository
) : ViewModel() {

    var postId: String? = null
    val commentListLiveData = MutableLiveData<List<Comment>?>()

   fun setupCommentListLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
            commentListLiveData.postValue(commentRepository.getCommentsByPost(postId ?: ""))
        }
    }
}
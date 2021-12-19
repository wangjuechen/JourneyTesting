package com.jc.android.journeytesting.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.PrimaryKey
import com.jc.android.journeytesting.data.CommentRepository
import com.jc.android.journeytesting.data.PostRepository
import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.domain.Post
import com.jc.android.journeytesting.utils.Resource
import com.squareup.moshi.Json
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {

    private val postListLiveData: LiveData<Resource<List<Post>>> = postRepository.getAllPosts()
    private val allCommentLiveData: LiveData<Resource<List<Comment>>> = commentRepository.getAllComments()

    var postAndCommentsMediatorLiveData = MediatorLiveData<Resource<List<PostAndComments>>>().apply {
        addSource(postListLiveData) {
            setupPostAndCommentsLiveData()
        }
        addSource(allCommentLiveData) {
            setupPostAndCommentsLiveData()
        }
    }

    private fun setupPostAndCommentsLiveData() {
        if (postListLiveData.value?.status == Resource.Status.LOADING || allCommentLiveData.value?.status == Resource.Status.LOADING) {
            postAndCommentsMediatorLiveData.value = Resource.loading()
        } else if (postListLiveData.value?.status == Resource.Status.ERROR) {
            postAndCommentsMediatorLiveData.value = Resource.error(postListLiveData.value?.message!!)
        } else if (allCommentLiveData.value?.status == Resource.Status.ERROR) {
            postAndCommentsMediatorLiveData.value = Resource.error(allCommentLiveData.value?.message!!)
        } else {
            val postAndCommentList = postListLiveData.value?.data?.map { post ->
                val commentsAmount = allCommentLiveData.value?.data?.count { it.postId == post.id} ?: 0
                PostAndComments(
                    userId = post.userId,
                    id = post.id,
                    title = post.title,
                    body = post.body,
                    numOfComments = commentsAmount
                )
            } ?: emptyList()

            postAndCommentsMediatorLiveData.value = Resource.success(postAndCommentList)
        }
    }
}


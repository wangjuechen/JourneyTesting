package com.jc.android.journeytesting.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jc.android.journeytesting.data.PostRepository
import com.jc.android.journeytesting.domain.Post
import com.jc.android.journeytesting.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    lateinit var postListLiveData: LiveData<Resource<List<Post>>>

    init {
        setupPostListLiveData()
    }

    private fun setupPostListLiveData() {
        postListLiveData = postRepository.getAllPosts()
    }

}
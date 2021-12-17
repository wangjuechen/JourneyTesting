package com.jc.android.journeytesting.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jc.android.journeytesting.data.PostRepository
import com.jc.android.journeytesting.domain.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {

    val postListLiveData = MutableLiveData<List<Post>>()

    init {
        setupPostListLiveData()
    }

    private fun setupPostListLiveData() {
        viewModelScope.launch {
            postListLiveData.postValue(postRepository.getAllPosts())
        }
    }

}
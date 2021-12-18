package com.jc.android.journeytesting.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jc.android.journeytesting.databinding.PostListItemBinding
import com.jc.android.journeytesting.domain.Post


class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var postList: List<Post> = emptyList()
    private lateinit var selectListener: PostListItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postListItemBinding.apply {
            postTitle.text = postList[position].title
            postBody.text = postList[position].body
            root.setOnClickListener {
                selectListener.postItemSelected(postId = postList[position].id)
            }
        }
    }

    override fun getItemCount(): Int = postList.size

    fun setData(dataList: List<Post>, postListItemListener: PostListItemListener) {
        this.postList = dataList
        this.selectListener = postListItemListener
        notifyDataSetChanged()
    }

    class ViewHolder(val postListItemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(postListItemBinding.root)
}

interface PostListItemListener {
    fun postItemSelected(postId: String)
}
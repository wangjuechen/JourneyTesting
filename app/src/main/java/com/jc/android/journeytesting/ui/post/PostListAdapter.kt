package com.jc.android.journeytesting.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jc.android.journeytesting.databinding.PostListItemBinding
import com.jc.android.journeytesting.domain.Post


class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private var postList: List<Post> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postListItemBinding.postTitle.text = postList[position].title
        holder.postListItemBinding.postBody.text = postList[position].body
    }

    override fun getItemCount(): Int = postList.size

    fun setData(dataList: List<Post>) {
        this.postList = dataList
        notifyDataSetChanged()
    }

    class ViewHolder(val postListItemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(postListItemBinding.root)
}
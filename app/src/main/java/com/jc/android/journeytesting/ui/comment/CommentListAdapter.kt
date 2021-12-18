package com.jc.android.journeytesting.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jc.android.journeytesting.databinding.CommentListItemBinding
import com.jc.android.journeytesting.domain.Comment

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.ViewHolder>() {

    private var commentList: List<Comment> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentListItemBinding.commentName.text = commentList[position].name
        holder.commentListItemBinding.commentEmail.text = commentList[position].email
        holder.commentListItemBinding.commentBody.text = commentList[position].body
    }

    override fun getItemCount() = commentList.size

    fun setData(dataList: List<Comment>) {
        this.commentList = dataList
        notifyDataSetChanged()
    }


    class ViewHolder(val commentListItemBinding: CommentListItemBinding) :
        RecyclerView.ViewHolder(commentListItemBinding.root)
}

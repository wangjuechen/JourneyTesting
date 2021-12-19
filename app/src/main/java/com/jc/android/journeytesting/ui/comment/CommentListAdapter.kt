package com.jc.android.journeytesting.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jc.android.journeytesting.databinding.CommentListItemBinding
import com.jc.android.journeytesting.domain.Comment
import com.jc.android.journeytesting.ui.post.PostAndComments

class CommentListAdapter : RecyclerView.Adapter<CommentListAdapter.ViewHolder>(), Filterable {

    private var commentList: List<Comment> = emptyList()
    private var commentListFiltered: MutableList<Comment> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            CommentListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.commentListItemBinding.commentName.text = commentListFiltered[position].name
        holder.commentListItemBinding.commentEmail.text = commentListFiltered[position].email
        holder.commentListItemBinding.commentBody.text = commentListFiltered[position].body
    }

    override fun getItemCount() = commentListFiltered.size

    fun setData(dataList: List<Comment>) {
        this.commentList = dataList
        commentListFiltered = commentList.toMutableList()
        notifyDataSetChanged()
    }

    class ViewHolder(val commentListItemBinding: CommentListItemBinding) :
        RecyclerView.ViewHolder(commentListItemBinding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""

                if (charString.isEmpty()) {
                    commentListFiltered = commentList.toMutableList()
                } else {
                    val filteredList = ArrayList<Comment>()
                    for (comment in commentList) {
                        if (comment.name.contains(charString, true)
                            || (comment.body?.contains(charString, true) == true)
                        ) {
                            filteredList.add(comment)
                        }
                    }
                    commentListFiltered = filteredList

                }
                return FilterResults().apply { values = commentListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                commentListFiltered = if (results?.values == null) {
                    ArrayList()
                } else {
                    results.values as ArrayList<Comment>
                }
                notifyDataSetChanged()
            }
        }
    }
}

package com.jc.android.journeytesting.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jc.android.journeytesting.R
import com.jc.android.journeytesting.databinding.PostListItemBinding


class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>(), Filterable {
    private var postList: List<PostAndComments> = emptyList()
    private var postListFiltered: MutableList<PostAndComments> = mutableListOf()
    private lateinit var selectListener: PostListItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            PostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postListFiltered[position]

        holder.postListItemBinding.apply {
            postTitle.text = post.title
            postUser.text = holder.itemView.context.getString(R.string.post_user, post.userId)
            postBody.text = post.body
            commentsNumber.text = holder.itemView.resources.getQuantityString(R.plurals.comments_amount, post.numOfComments, post.numOfComments)
            root.setOnClickListener {
                selectListener.postItemSelected(postId = post.id)
            }
        }
    }

    override fun getItemCount(): Int = postListFiltered.size

    fun setData(dataList: List<PostAndComments>, postListItemListener: PostListItemListener) {
        this.postList = dataList
        postListFiltered = postList.toMutableList()
        this.selectListener = postListItemListener
        notifyDataSetChanged()
    }

    class ViewHolder(val postListItemBinding: PostListItemBinding) :
        RecyclerView.ViewHolder(postListItemBinding.root)

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""

                if (charString.isEmpty()) {
                    postListFiltered = postList.toMutableList()
                } else {
                    val filteredList = ArrayList<PostAndComments>()
                    for (post in postList) {
                        if ((post.body?.contains(
                                charString,
                                true
                            ) == true) || (post.title?.contains(charString, true) == true)
                        ) {
                            filteredList.add(post)
                        }
                    }
                    postListFiltered = filteredList

                }
                return FilterResults().apply { values = postListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                postListFiltered = if (results?.values == null) {
                    ArrayList()
                } else {
                    results.values as ArrayList<PostAndComments>
                }
                notifyDataSetChanged()
            }
        }
    }
}

interface PostListItemListener {
    fun postItemSelected(postId: String)
}


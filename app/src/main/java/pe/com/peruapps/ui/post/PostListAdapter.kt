package pe.com.peruapps.ui.post

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pe.com.peruapps.R
import pe.com.peruapps.databinding.ItemPostBinding
import pe.com.peruapps.model.Post
import pe.com.peruapps.ui.postdetail.PostDetailActivity

typealias OnClickPostListener = (View, Post) -> Unit

class PostListAdapter(
    private val onClickPostListener: OnClickPostListener
): RecyclerView.Adapter<PostListAdapter.ViewHolder>() {
    private lateinit var postList: List<Post>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPostBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_post, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.itemView.setOnClickListener {
                view -> onClickPostListener.invoke(view, post)
        }
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return if(::postList.isInitialized) postList.size else 0
    }

    fun updatePostList(postList: List<Post>) {
        this.postList = postList
        notifyDataSetChanged()
    }

    fun openPostDetailActivity(view: View, post: Post) {
        view.context.startActivity(
            Intent(view.context, PostDetailActivity::class.java).putExtra("KEY_ID_POST", post.id))
    }

    class ViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        private val viewModel = PostViewModel()

        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}
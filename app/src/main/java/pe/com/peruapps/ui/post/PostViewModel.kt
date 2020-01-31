package pe.com.peruapps.ui.post

import android.arch.lifecycle.MutableLiveData
import pe.com.peruapps.base.BaseViewModel
import pe.com.peruapps.model.Post

class PostViewModel: BaseViewModel() {
    private val postTitle = MutableLiveData<String>()
    private val postBody = MutableLiveData<String>()
    private lateinit var postItem: Post
    fun bind(post: Post) {
        postTitle.value = post.title
        postBody.value = post.body
        postItem = post
    }

    fun getPostTitle(): MutableLiveData<String> {
        return postTitle
    }

    fun getPostBody(): MutableLiveData<String> {
        return postBody
    }

    fun onClickPost() {
    }
}
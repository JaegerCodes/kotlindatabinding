package pe.com.peruapps.ui.post

import android.arch.lifecycle.MutableLiveData
import io.reactivex.Observable
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import pe.com.peruapps.R
import pe.com.peruapps.base.BaseViewModel
import pe.com.peruapps.model.Post
import pe.com.peruapps.model.PostDao
import pe.com.peruapps.network.PostApi
import javax.inject.Inject

class PostListViewModel(private val postDao: PostDao): BaseViewModel() {
    @Inject
    lateinit var postApi: PostApi

    val postListAdapter: PostListAdapter = PostListAdapter(
        onClickPostListener = this::openPostDetailActivity
    )

    private fun openPostDetailActivity(view: View, post: Post) {
        postListAdapter.openPostDetailActivity(view, post)
    }

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }

    private lateinit var subscription: Disposable

    init {
        loadPosts()
    }

    private fun loadPosts() {
        subscription = Observable.fromCallable { postDao.all }
            .concatMap {
                    dbPostList ->
                if (dbPostList.isEmpty())
                    postApi.getPosts().concatMap {
                            apiPostList -> postDao.insertAll(*apiPostList.toTypedArray())
                        Observable.just(apiPostList)
                    }
                else
                    Observable.just(dbPostList)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePostListSuccess(result) },
                { onRetrievePostListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePostListSuccess(postList: List<Post>) {
        postListAdapter.updatePostList(postList)
    }

    private fun onRetrievePostListError() {
        errorMessage.value = R.string.post_error
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
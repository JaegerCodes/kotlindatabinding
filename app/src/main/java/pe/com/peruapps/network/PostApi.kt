package pe.com.peruapps.network

import io.reactivex.Observable
import pe.com.peruapps.model.Post
import retrofit2.http.GET

interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>
}
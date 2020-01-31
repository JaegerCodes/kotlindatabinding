package pe.com.peruapps.injection.component

import dagger.Component
import pe.com.peruapps.injection.module.NetworkModule
import pe.com.peruapps.ui.post.PostListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    fun inject(postListViewModel: PostListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}
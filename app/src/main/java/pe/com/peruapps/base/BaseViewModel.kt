package pe.com.peruapps.base

import android.arch.lifecycle.ViewModel
import pe.com.peruapps.injection.component.DaggerViewModelInjector
import pe.com.peruapps.injection.component.ViewModelInjector
import pe.com.peruapps.injection.module.NetworkModule
import pe.com.peruapps.ui.post.PostListViewModel

abstract class BaseViewModel: ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is PostListViewModel -> injector.inject(this)
        }
    }
}
package pe.com.peruapps.ui.postdetail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import pe.com.peruapps.R
import pe.com.peruapps.databinding.ActivityPostListBinding
import pe.com.peruapps.injection.ViewModelFactory

class PostDetailActivity: AppCompatActivity() {
    private var errorSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun showError(@StringRes errorMessage:Int) {

    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}
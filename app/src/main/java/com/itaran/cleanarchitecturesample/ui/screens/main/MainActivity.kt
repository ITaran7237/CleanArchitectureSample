package com.itaran.cleanarchitecturesample.ui.screens.main
import android.content.Context
import android.content.Intent
import com.itaran.cleanarchitecturesample.R
import com.itaran.cleanarchitecturesample.ui.screens.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainViewModel>() {

    override val layoutRes = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    companion object {
        fun startActivity(context: Context?) {
            Intent(context, MainActivity::class.java)
                .apply {
                    context?.startActivity(this)
                }
        }

        fun startWithClearAllActivity(context: Context?) {
            Intent(context, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    context?.startActivity(this)
                }
        }

        fun startWithClearTopActivity(context: Context?) {
            Intent(context, MainActivity::class.java)
                .apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    context?.startActivity(this)
                }
        }
    }

    override fun buttonClickListener() {}

    override fun liveDataObserver() {}
}

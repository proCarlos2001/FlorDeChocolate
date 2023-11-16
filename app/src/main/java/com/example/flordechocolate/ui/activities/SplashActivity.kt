package com.example.flordechocolate.ui.activities

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.flordechocolate.data.datasource.AppDatabase
import com.example.flordechocolate.data.datasource.MemoryDataSource
import com.example.flordechocolate.data.viewmodels.LoginViewModel

import com.example.flordechocolate.databinding.ActivitySplashBinding
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val db: AppDatabase by inject()
    private val memoryDataSource: MemoryDataSource by inject()
    private val scope = CoroutineScope(newSingleThreadContext("splash"))
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        scope.launch {
            if(db.productsDao().getCount() == 0) {
                db.productsDao().insertAll(memoryDataSource.product())
            }
            if(db.serviceDao().getCount() == 0) {
                db.serviceDao().insertAll(memoryDataSource.service())
            }
        }
        binding.splashAnimation.playAnimation()

        binding.splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                loginViewModel.currentUser()

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
        binding.splashAnimationTwo.playAnimation()

        binding.splashAnimationTwo.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                loginViewModel.currentUser()

            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
        observeViewModels()
    }
    private fun observeViewModels() {
        loginViewModel.user.observe(this, Observer {
            if(it == null) {
                val intent = Intent(applicationContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(applicationContext, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        })
    }
}
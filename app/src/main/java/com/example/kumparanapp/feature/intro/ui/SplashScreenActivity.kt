package com.example.kumparanapp.feature.intro.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.kumparanapp.MainActivity
import com.example.kumparanapp.core.base.BaseActivity
import com.example.kumparanapp.databinding.ActivitySplashScreenBinding
import com.example.kumparanapp.feature.post.ui.PostActivity

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun getViewBinding(): ActivitySplashScreenBinding {
        return ActivitySplashScreenBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler().postDelayed({
            val intent = Intent(this, PostActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}
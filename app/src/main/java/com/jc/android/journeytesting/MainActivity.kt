package com.jc.android.journeytesting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jc.android.journeytesting.databinding.MainActivityBinding
import com.jc.android.journeytesting.ui.post.PostListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PostListFragment.newInstance())
                .commitNow()
        }
    }
}
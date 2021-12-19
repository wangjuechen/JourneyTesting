package com.jc.android.journeytesting

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
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

        val navController = findNavController(R.id.my_nav_host_fragment)

        // Set up the ActionBar to stay in sync with the NavController
        setupActionBarWithNavController(this, navController)
    }
}
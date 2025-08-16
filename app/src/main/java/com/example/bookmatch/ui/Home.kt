package com.example.bookmatch.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.bookmatch.R
import com.example.bookmatch.databinding.ActivityHomeBinding
import com.example.bookmatch.utils.SharedPreferencesUtil
import com.google.android.material.button.MaterialButton

class Home : AppCompatActivity() {

    private lateinit var logout: MaterialButton
    private lateinit var binding: ActivityHomeBinding
    private lateinit var userEmail: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userEmail = intent.getStringExtra("userEmail") ?: ""

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(ExploreFragment(userEmail))

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.explore -> {
                    replaceFragment(ExploreFragment(userEmail))
                }
                R.id.my_list -> {
                    replaceFragment(MyListFragment(userEmail))
                }
                R.id.reviews -> {
                    replaceFragment(ReviewsFragment())
                }
            }
            true
        }

        logout = findViewById(R.id.logout)
        logout.setOnClickListener {
            SharedPreferencesUtil.removeKey(this@Home,"remember_me", "e_mail")
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
package com.divingdinosaur.discolights

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.divingdinosaur.discolights.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val colorArray = arrayOf("#3F51B5", "#F44336", "#9C27B0", "#FF5722", "#83FF16")

        // Launch a coroutine to swap the background color repeatedly
        lifecycleScope.launch {
            while (true) {
                for (i in colorArray) {
                    binding.discoLayout.setBackgroundColor(Color.parseColor(i))
                    delay(500)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()

        window.attributes.screenBrightness = 1.0F

        WindowCompat.setDecorFitsSystemWindows(window, false)

        WindowInsetsControllerCompat(window, window.decorView).apply {
            // Hide the status bar
            hide(WindowInsetsCompat.Type.systemBars())
            // Allow showing the status bar with swiping from top to bottom
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
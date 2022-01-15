package edu.neo.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.widget.ContentLoadingProgressBar
import com.google.android.material.progressindicator.LinearProgressIndicator
import edu.neo.R

class spash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spash)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            this.finish()
        },1000)
    }
}
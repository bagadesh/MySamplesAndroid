package com.bagadesh.myallsampleapps.mainScreen.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bagadesh.myallsampleapps.R

class Paging3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paging3)
        supportFragmentManager.beginTransaction().replace(R.id.container, Pagin3SampleFragment.newInstance()).commitNow()
    }
}
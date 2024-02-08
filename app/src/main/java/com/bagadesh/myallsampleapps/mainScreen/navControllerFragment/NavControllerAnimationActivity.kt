package com.bagadesh.myallsampleapps.mainScreen.navControllerFragment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.bagadesh.myallsampleapps.R
import com.bagadesh.myallsampleapps.ui.theme.MyAllSampleAppsTheme

/**
 * Created by bagadesh on 24/11/22.
 */
class NavControllerAnimationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nav_controller_activity)
    }

}
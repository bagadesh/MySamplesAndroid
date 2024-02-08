package com.bagadesh.myallsampleapps

import android.os.Bundle
import android.os.Debug
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bagadesh.myallsampleapps.mainScreen.MainScreen
import com.bagadesh.myallsampleapps.ui.theme.MyAllSampleAppsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.Period

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        val time = System.currentTimeMillis()
        Debug.startMethodTracing("Sample")
        super.onCreate(savedInstanceState)
        Debug.stopMethodTracing()
        val result = System.currentTimeMillis() - time
        println("datmug, ${LocalDate.now()}, ${Period.parse("P1Y")}")
        setContent {
            MyAllSampleAppsTheme {
                Column {
                    MainScreen()
                }
            }
        }
    }
}
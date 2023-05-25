package com.emadmehrez.tenki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.emadmehrez.tenki.ui.theme.TenkiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TenkiActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TenkiTheme {

            }
        }
    }
}


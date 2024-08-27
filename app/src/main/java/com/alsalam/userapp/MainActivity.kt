package com.alsalam.userapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.alsalam.userapp.Navigation.MainScreen
import com.alsalam.userapp.ui.theme.UserAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            UserAppTheme {
                MainScreen()
            }

        }
    }

}

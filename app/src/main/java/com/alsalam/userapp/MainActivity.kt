package com.alsalam.userapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import com.alsalam.userapp.activities.Payment


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContent {
            /*UserAppTheme {
                MainScreen()
            }*/
            val context = LocalContext.current
            val intent = Intent(context, Payment::class.java)
            intent.putExtra("Name","Example")
            intent.putExtra("Email","demo1@mail.com")
            intent.putExtra("Phone","1234567890")
            intent.putExtra("AMOUNT","500")
            intent.putExtra("TYPE","Admission Fees")

            Button(onClick = { context.startActivity(intent) }) {
                Text("Start Payment")
            }
        }
    }

}

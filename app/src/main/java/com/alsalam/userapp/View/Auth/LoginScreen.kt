package com.alsalam.userapp.View.Auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.alsalam.userapp.R
import com.alsalam.userapp.View.Components.CustomTextHeading
import com.alsalam.userapp.ViewModel.AuthState
import com.alsalam.userapp.ViewModel.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, authViewModel: AuthViewModel){

    val studentID by authViewModel.studentID.collectAsState("")
    val studentEmail by authViewModel.studentEmail.collectAsState("")
    val studentPassword by authViewModel.studentPassword.collectAsState("")

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate("home")
            else -> Unit
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(colorResource(id = R.color.BackgroundColor))
        .padding(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {

        // Heading
        Spacer(modifier = Modifier.height(80.dp))
        CustomTextHeading(heading = "Login here", fontSize = 35, fontWeight = FontWeight.Bold, color = colorResource(
            id = R.color.TextHeading)
        )

        // Sub Heading
        CustomTextHeading(heading = "Welcome back you've been missed", fontSize = 18, fontWeight = FontWeight.Normal, color = colorResource(
            id = R.color.black)
        )


        // StudentID
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value = studentID, onValueChange = {authViewModel.studentID.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Enter studentID",fontSize = 15.sp) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.TextBoxColor),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.TextHeading)
            )
        )

        // Email
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = studentEmail, onValueChange = {authViewModel.studentEmail.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Enter email",fontSize = 15.sp) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.TextBoxColor),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.TextHeading)
            )
        )

        // Password
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = studentPassword, onValueChange = {authViewModel.studentPassword.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Enter password",fontSize = 15.sp) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.TextBoxColor),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.TextHeading)
            )
        )

        // Sign In
        Spacer(modifier = Modifier.height(30.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(enabled = authState.value != AuthState.Loading) {
                authViewModel.login()

                if (authState.value == AuthState.Unauthenticated) {
                    Toast.makeText(context, "Don't have an account", Toast.LENGTH_SHORT).show()
                }
            },
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.TextHeading)),
            elevation = CardDefaults.cardElevation(10.dp)
        )
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("Sign In", fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 15.sp)
            }
        }


        // Create account
        Spacer(modifier = Modifier.height(45.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                "Don't have an account? ",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(
                "Create an account",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.TextHeading),
                fontSize = 15.sp,
                modifier = Modifier.clickable { navController.navigate("signUp") }
            )
        }
    }

}




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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.userapp.R
import com.alsalam.userapp.View.Components.CustomTextHeading
import com.alsalam.userapp.ViewModel.AuthState
import com.alsalam.userapp.ViewModel.AuthViewModel
import com.alsalam.userapp.ViewModel.StudentDetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, authViewModel: AuthViewModel){

    val studentName by authViewModel.studentName.collectAsState("")
    val studentID by authViewModel.studentID.collectAsState("")
    val studentEmail by authViewModel.studentEmail.collectAsState("")
    val studentPassword by authViewModel.studentPassword.collectAsState("")
    val confirmPassword by authViewModel.confirmPassword.collectAsState("")
    var signUpSuccess by remember{ mutableStateOf(false)}

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    val studentDetailsViewModel: StudentDetailsViewModel = viewModel()

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> {
                navController.navigate("home")
                signUpSuccess = true
            }
            is AuthState.Error -> Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
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
        CustomTextHeading(heading = "Create Account", fontSize = 35, fontWeight = FontWeight.Bold, color = colorResource(
            id = R.color.TextHeading)
        )

        // Sub Heading
        CustomTextHeading(heading = "Stay connected, informed, and organized", fontSize = 18, fontWeight = FontWeight.Normal, color = colorResource(
            id = R.color.black)
        )

        // Name
        Spacer(modifier = Modifier.height(100.dp))
        OutlinedTextField(value = studentName, onValueChange = {authViewModel.studentName.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Enter name",fontSize = 15.sp) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.TextBoxColor),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.TextHeading)
            )
        )

        // StudentID
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = studentID, onValueChange = {authViewModel.studentID.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Enter student id",fontSize = 15.sp) },
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

        // Confirm Password
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = confirmPassword, onValueChange = {authViewModel.confirmPassword.value = it},
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            placeholder = { Text("Confirm Password",fontSize = 15.sp) },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.TextBoxColor),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = colorResource(id = R.color.TextHeading)
            )
        )

        // Sign Up
        Spacer(modifier = Modifier.height(30.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                authViewModel.signUp()
                Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
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
                Text("Sign Up", fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 15.sp)
            }
        }


        // Sign in
        Spacer(modifier = Modifier.height(45.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                "Already have an account? ",
                fontWeight = FontWeight.Normal,
                color = Color.Black,
                fontSize = 15.sp
            )
            Text(
                "Sign in",
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.TextHeading),
                fontSize = 15.sp,
                modifier = Modifier.clickable { navController.navigate("login")
                    Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}
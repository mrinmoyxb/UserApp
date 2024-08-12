package com.alsalam.userapp.View.HomeScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.alsalam.userapp.Model.StudentInfo
import com.alsalam.userapp.R
import com.alsalam.userapp.View.Components.CustomCard
import com.alsalam.userapp.View.Components.CustomTextHeading
import com.alsalam.userapp.View.Components.StudentCard
import com.alsalam.userapp.ViewModel.AuthViewModel
import com.alsalam.userapp.ViewModel.PDFViewModel
import com.alsalam.userapp.ViewModel.PaymentViewModel
import com.alsalam.userapp.ViewModel.StudentDetailsViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(navController: NavHostController, authViewModel: AuthViewModel, result: PDFViewModel, paymentViewModel: PaymentViewModel) {
    val studentViewModel: StudentDetailsViewModel = viewModel()
    val sid by authViewModel.studentID.collectAsState("")
    val student by studentViewModel.studentDetails.collectAsState(StudentInfo())

    LaunchedEffect(Unit) {
        studentViewModel.studentId.value = sid
        studentViewModel.fetchStudentDetails()
        paymentViewModel.currentStudentId.value = sid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.BackgroundColor))
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            CustomTextHeading(heading = "Welcome back", fontSize = 30, fontWeight = FontWeight.Medium, color = Color.Black)
        }


        Spacer(modifier = Modifier.height(20.dp))
        StudentCard(name = student.studentName, studentID = student.studentId, dob = student.dateOfBirth)

        // All icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            CustomCard(image = painterResource(id = R.drawable.result), heading = "Result", onClick = {
                navController.navigate("result")
                result.studentId.value = sid
            })
            CustomCard(image = painterResource(id = R.drawable.notice), heading = "Notice", onClick = {navController.navigate("notice") })
            CustomCard(image = painterResource(id = R.drawable.holiday), heading = "Holiday", onClick = {navController.navigate("holiday")})
        }
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CustomCard(image = painterResource(id = R.drawable.routine), heading = "Class Routine", onClick = {navController.navigate("classroutine")})
            CustomCard(image = painterResource(id = R.drawable.festival), heading = "Festival", onClick = {navController.navigate("festival")})
            CustomCard(image = painterResource(id = R.drawable.payments), heading = "Payments", onClick = {
                navController.navigate("payments")
                paymentViewModel.gradeSelected.value = student.grade
                paymentViewModel.fetchStudentFees()
            })
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { /*TODO*/ }) {
            Text("Payment")
        }
    }
}



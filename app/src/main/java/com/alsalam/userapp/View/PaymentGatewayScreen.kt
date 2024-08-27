package com.alsalam.userapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.userapp.R
import com.alsalam.userapp.View.Components.SaveUploadButton

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PaymentGatewayScreen(){
    
    var studentId by remember{ mutableStateOf("") }
    var name by remember {mutableStateOf("")}
    var feesType by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.BackgroundColor))
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = studentId,
            onValueChange =  {studentId = it},
            label = { Text("Enter student id", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.BackgroundColor)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = name,
            onValueChange =  {name = it},
            label = { Text("Enter student name", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.BackgroundColor)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = feesType,
            onValueChange =  {feesType = it},
            label = { Text("Enter fees paid for", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.BackgroundColor)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))


        OutlinedTextField(
            value = feesType,
            onValueChange =  {feesType = it},
            label = { Text("Enter amount", fontSize = 15.sp) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(id = R.color.BackgroundColor)
            )
        )
        Spacer(modifier = Modifier.height(20.dp))


        SaveUploadButton(title = "Pay Now") {}
    }
}
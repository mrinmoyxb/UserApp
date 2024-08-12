package com.alsalam.userapp.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.userapp.Model.PaymentTypes
import com.alsalam.userapp.R
import com.alsalam.userapp.ViewModel.PaymentViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PaymentScreen(paymentViewModel: PaymentViewModel) {
    val studentsPaymentFetched by paymentViewModel.studentsPaymentFetched.collectAsState(emptyList())


    Scaffold(topBar = { CustomTopBar(text = "Holiday", fontSize = 12) }) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            item {
                Spacer(modifier = Modifier.height(85.dp))
                Text("Admission Fees")
                studentsPaymentFetched.forEach{ add ->
                    if(add.studentPaymentFor == PaymentTypes.AdmissionFees){
                        FeesPaidStudentCard(name = add.studentName, amount = add.studentPaymentAmount.toString(), paid = add.studentFeesPaid, date = add.dateSetByAdmin)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }


                Text("Hostel Fees")
                studentsPaymentFetched.forEach{ add ->
                    if(add.studentPaymentFor == PaymentTypes.HostelFees){
                        FeesPaidStudentCard(name = add.studentName, amount = add.studentPaymentAmount.toString(), paid = add.studentFeesPaid, date = add.dateSetByAdmin)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }


                Text("Tuition Fees")
                studentsPaymentFetched.forEach{ add ->
                    if(add.studentPaymentFor == PaymentTypes.TuitionFees){
                        FeesPaidStudentCard(name = add.studentName, amount = add.studentPaymentAmount.toString(), paid = add.studentFeesPaid, date = add.dateSetByAdmin)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }


                Text("Other Fees")
                studentsPaymentFetched.forEach{ add ->
                    if(add.studentPaymentFor == PaymentTypes.OtherFees){
                        FeesPaidStudentCard(name = add.studentName, amount = add.studentPaymentAmount.toString(), paid = add.studentFeesPaid, date = add.dateSetByAdmin)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }


                Text("Late Fees")
                studentsPaymentFetched.forEach{ add ->
                    if(add.studentPaymentFor == PaymentTypes.LateFees){
                        FeesPaidStudentCard(name = add.studentName, amount = add.studentPaymentAmount.toString(), paid = add.studentFeesPaid, date = add.dateSetByAdmin)
                        Spacer(modifier = Modifier.height(5.dp))
                    }
                }

            }
        }
    }
}


@Composable
fun FeesPaidStudentCard(name: String, amount: String, paid: Boolean, date: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)) {
            Column(
                modifier = Modifier
                    .width(250.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    name.uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    maxLines = 1
                )
                Text(
                    "₹ $amount",
                    color = Color.White,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(8.dp))

            }

            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    if (paid) "PAID" else "NOT PAID",
                    color = if (paid) Color.Green else Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    if (paid) date else "",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
            }
        }
    }
}
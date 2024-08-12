package com.alsalam.userapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.alsalam.userapp.Navigation.MainScreen
import com.alsalam.userapp.ui.theme.UserAppTheme
import com.razorpay.Checkout
import com.razorpay.ExternalWalletListener
import com.razorpay.PayloadHelper
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserAppTheme {
                MainScreen()
            }
        }
    }

}

//    private fun initPayment() {
//        val payloadHelper = PayloadHelper("INR", 100, "order_XXXXXXXXX")
//        payloadHelper.name = "Gaurav Kumae"
//        payloadHelper.description = "Description"
//        payloadHelper.prefillEmail = "gaurav.kumar@example.com"
//        payloadHelper.prefillContact = "9000090000"
//        payloadHelper.prefillCardNum = "4111111111111111"
//        payloadHelper.prefillCardCvv = "111"
//        payloadHelper.prefillCardExp = "11/24"
//        payloadHelper.prefillMethod = "card"
//        payloadHelper.prefillName = "MerchantName"
//        payloadHelper.sendSmsHash = true
//        payloadHelper.retryMaxCount = 4
//        payloadHelper.retryEnabled = true
//        payloadHelper.color = "#000000"
//        payloadHelper.allowRotation = true
//        payloadHelper.rememberCustomer = true
//        payloadHelper.timeout = 10
//        payloadHelper.redirect = true
//        payloadHelper.recurring = "1"
//        payloadHelper.subscriptionCardChange = true
//        payloadHelper.customerId = "cust_XXXXXXXXXX"
//        payloadHelper.callbackUrl = "https://accepts-posts.request"
//        payloadHelper.subscriptionId = "sub_XXXXXXXXXX"
//        payloadHelper.modalConfirmClose = true
//        payloadHelper.backDropColor = "#ffffff"
//        payloadHelper.hideTopBar = true
//        payloadHelper.notes = JSONObject("{\"remarks\":\"Discount to cusomter\"}")
//        payloadHelper.readOnlyEmail = true
//        payloadHelper.readOnlyContact = true
//        payloadHelper.readOnlyName = true
//        payloadHelper.image = "https://www.razorpay.com"
//        payloadHelper.amount = 100
//        payloadHelper.currency = "INR"
//        payloadHelper.orderId = "order_XXXXXXXXXXXXXX"
//    }
//
//    override fun onPaymentSuccess(p0: String?) {
//        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPaymentError(p0: Int, p1: String?) {
//        Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
//    }
//}

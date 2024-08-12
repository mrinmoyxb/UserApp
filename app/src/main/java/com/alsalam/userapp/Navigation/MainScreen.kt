package com.alsalam.userapp.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alsalam.userapp.View.HomeScreen.HomeScreen
import com.alsalam.userapp.View.Auth.LoginScreen
import com.alsalam.userapp.View.Auth.SignUpScreen
import com.alsalam.userapp.View.ClassRoutineScreen
import com.alsalam.userapp.View.FestivalScreen
import com.alsalam.userapp.View.HolidayScreen
import com.alsalam.userapp.View.NoticeScreen
import com.alsalam.userapp.View.PaymentScreen
import com.alsalam.userapp.View.ResultScreen
import com.alsalam.userapp.ViewModel.AuthViewModel
import com.alsalam.userapp.ViewModel.PDFViewModel
import com.alsalam.userapp.ViewModel.PaymentViewModel

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()
    val viewModel: PDFViewModel = viewModel()
    val paymentViewModel: PaymentViewModel = viewModel()

    NavHost(navController = navController, startDestination = "login") {
        composable(route = "login"){
            LoginScreen(navController, authViewModel)
        }

        composable(route = "signUp"){
            SignUpScreen(navController, authViewModel)
        }

        composable(route = "home"){
            HomeScreen(navController, authViewModel, viewModel, paymentViewModel)
        }

        composable(route = "classroutine"){
            ClassRoutineScreen()
        }

        composable(route = "notice"){
            NoticeScreen()
        }

        composable(route = "festival"){
            FestivalScreen()
        }

        composable(route = "result"){
            ResultScreen(viewModel)
        }

        composable(route = "holiday"){
            HolidayScreen()
        }

        composable(route = "payments"){
            PaymentScreen(paymentViewModel)
        }
    }
}


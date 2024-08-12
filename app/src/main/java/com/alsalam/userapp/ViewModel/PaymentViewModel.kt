package com.alsalam.userapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.userapp.Firebase.FirebaseManager
import com.alsalam.userapp.Model.StudentFee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PaymentViewModel: ViewModel() {

    val gradeSelected: MutableStateFlow<String> = MutableStateFlow<String>("")
    val currentStudentId: MutableStateFlow<String> = MutableStateFlow<String>("")
    val studentsPaymentFetched = MutableStateFlow<List<StudentFee>>(emptyList())

    fun fetchStudentFees() {
        viewModelScope.launch {
            FirebaseManager.fireStoreRef
                .collection("Payments")
                .document(gradeSelected.value)
                .collection(currentStudentId.value)
                .get()
                .addOnSuccessListener { result ->
                    val fetchedData = result.toObjects(StudentFee::class.java)
                    studentsPaymentFetched.value = fetchedData
                }
                .addOnFailureListener { e ->
                    Log.w("FireStore ERROR", "Error fetching documents", e)
                }
        }
    }
}
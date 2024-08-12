package com.alsalam.userapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.userapp.Firebase.FirebaseManager
import com.alsalam.userapp.Model.StudentInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class StudentDetailsViewModel: ViewModel(){

    val studentId = MutableStateFlow<String>("")
    var studentOutput = MutableStateFlow<List<StudentInfo>>(emptyList())
    var studentDetails: MutableStateFlow<StudentInfo> = MutableStateFlow(StudentInfo("", "", "","", "", "", "", "", "", "", "", "", "", "", "", ""))

    fun fetchStudentDetails() {
        val studentRef = FirebaseManager.allStudentRef.child(studentId.value)
        studentRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists())
                {
                    val student = dataSnapshot.getValue(StudentInfo::class.java)
                    studentDetails.value = student!!
                    Log.d("StudentFetch", "Student details fetched successfully: $student")
                } else {
                    Log.e("StudentFetchError", "Student not found with ID: $studentId")
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.e("StudentFetchError", "Error fetching student details: ${databaseError.message}")
            }
        })
    }

}

package com.alsalam.userapp.Firebase

import android.annotation.SuppressLint
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage

object FirebaseManager {
    // AUTHENTICATION
    val firebaseAuthRef = FirebaseAuth.getInstance()

    // DATABASE
    val database = Firebase.database
    val signUpRef = database.getReference("SignUpUserInfo")
    val studentRef = database.getReference("Students")
    val allStudentRef = database.getReference("AllStudents")

    // PDF
    val pdfRef = database.getReference("PDF_URL")
    val resultRef = database.getReference("RESULT_URL")

    @SuppressLint("StaticFieldLeak")
    val fireStoreRef = FirebaseFirestore.getInstance()

}
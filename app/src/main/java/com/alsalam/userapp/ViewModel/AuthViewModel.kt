package com.alsalam.userapp.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.userapp.Firebase.FirebaseManager
import com.alsalam.userapp.Model.SignUpUser
import com.alsalam.userapp.Model.StudentInfo
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    var studentName: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentID: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentEmail: MutableStateFlow<String> = MutableStateFlow<String>("")
    var studentPassword: MutableStateFlow<String> = MutableStateFlow<String>("")
    var confirmPassword: MutableStateFlow<String> = MutableStateFlow<String>("")

    var studentOutput = MutableStateFlow<List<StudentInfo>>(emptyList())

    private val _students = MutableLiveData<List<StudentInfo>>()
    val students: LiveData<List<StudentInfo>> = _students

    init{
        checkAuthStatus()
    }

    // checks whether we are login or not
    private fun checkAuthStatus() {
        viewModelScope.launch {
            if (auth.currentUser == null) {
                _authState.value = AuthState.Unauthenticated
            } else {
                _authState.value = AuthState.Loading
            }
        }
    }

    // login
    fun login() {
        viewModelScope.launch {
            if (studentEmail.value.isEmpty() || studentPassword.value.isEmpty() || studentID.value.isEmpty()) {
                _authState.value = AuthState.Error("Enter valid credentials")
            }
            _authState.value = AuthState.Loading
            auth.signInWithEmailAndPassword(studentEmail.value, studentPassword.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "")
                    }
                }
        }
    }

    // sign up
    fun signUp() {
        viewModelScope.launch {
            if (studentName.value.isNotEmpty() || studentID.value.isNotEmpty() || studentPassword.value.isNotEmpty() && studentEmail.value.isNotEmpty() && confirmPassword.value.isNotEmpty()) {
                if (studentPassword.value != confirmPassword.value) {
                    _authState.value = AuthState.Error("Passwords don't match")
                    studentPassword.value = ""
                    confirmPassword.value = ""
                }
            }

            //_authState.value = AuthState.Loading
            auth.createUserWithEmailAndPassword(studentEmail.value, studentPassword.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        _authState.value = AuthState.Authenticated
                        addSignUpUser()
                    } else {
                        _authState.value = AuthState.Error(task.exception?.message ?: "")
                    }
                }
        }
    }

    // sign out
    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }

    private fun addSignUpUser(){
        viewModelScope.launch {
            val signUpUser = SignUpUser(studentName.value, studentID.value, studentEmail.value, studentPassword.value)
            val userRef = FirebaseManager.signUpRef.child(studentID.value)

            userRef.setValue(signUpUser).addOnSuccessListener {
                Log.d("Student Add", "Student added successfully")
            }.addOnFailureListener { exception ->
                Log.e("StudentAddError", "Error adding student: $exception")
            }
        }
    }
}

sealed class AuthState{
    object Authenticated: AuthState()
    object Unauthenticated: AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}
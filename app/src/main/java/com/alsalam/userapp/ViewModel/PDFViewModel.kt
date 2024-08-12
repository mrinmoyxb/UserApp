package com.alsalam.userapp.ViewModel

import android.app.DownloadManager
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alsalam.userapp.Firebase.FirebaseManager
import com.alsalam.userapp.Model.PDFDataModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import android.content.Context
import com.alsalam.userapp.Model.ResultPDFDataModel

class PDFViewModel: ViewModel() {

    val _students: MutableStateFlow<List<PDFDataModel>> = MutableStateFlow<List<PDFDataModel>>(emptyList())
    val _result: MutableStateFlow<List<ResultPDFDataModel>> = MutableStateFlow<List<ResultPDFDataModel>>(emptyList())
    val currentPdfUrl: MutableStateFlow<String> = MutableStateFlow<String>("")
    val studentId: MutableStateFlow<String> = MutableStateFlow<String>("")

    fun loadNoticePdf() {
        viewModelScope.launch {
            val classRef = FirebaseManager.pdfRef.child("Notice")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<PDFDataModel>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(PDFDataModel::class.java)
                    student?.let { studentsList.add(it) }
                }
                _students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadHolidayPdf() {
        viewModelScope.launch {
            val classRef = FirebaseManager.pdfRef.child("Holiday")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<PDFDataModel>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(PDFDataModel::class.java)
                    student?.let { studentsList.add(it) }
                }
                _students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadClassRoutinePdf() {
        viewModelScope.launch {
            val classRef = FirebaseManager.pdfRef.child("ClassRoutine")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<PDFDataModel>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(PDFDataModel::class.java)
                    student?.let { studentsList.add(it) }
                }
                _students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadFestivalPdf() {
        viewModelScope.launch {
            val classRef = FirebaseManager.pdfRef.child("Festival")
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<PDFDataModel>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(PDFDataModel::class.java)
                    student?.let { studentsList.add(it) }
                }
                _students.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun loadResultPdf() {
        viewModelScope.launch {
            val classRef = FirebaseManager.resultRef.child(studentId.value.toString())
            classRef.get().addOnSuccessListener { dataSnapshot ->
                val studentsList = mutableListOf<ResultPDFDataModel>()
                dataSnapshot.children.forEach { child ->
                    val student = child.getValue(ResultPDFDataModel::class.java)
                    student?.let { studentsList.add(it) }
                }
                _result.value = studentsList
            }.addOnFailureListener { exception ->
                Log.e("LoadStudentsError", "Failed to load students: ${exception.message}")
            }
        }
    }

    fun downloadPdf(context: Context){
        val request = DownloadManager.Request(Uri.parse(currentPdfUrl.value))
            .setTitle("Downloading PDF")
            .setDescription("Downloading...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "downloaded_pdf.pdf")

        val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }
}
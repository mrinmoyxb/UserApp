package com.alsalam.userapp.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.userapp.View.Components.DisplayPDF
import com.alsalam.userapp.ViewModel.PDFViewModel
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@Composable
fun HolidayScreen(){

    val viewModel: PDFViewModel = viewModel()
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val noticePdf by viewModel._students.collectAsState(emptyList())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadHolidayPdf()
    }

    Scaffold(topBar = { CustomTopBar(text = "Holiday", fontSize = 12)}) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            item{
                Spacer(modifier = Modifier.height(85.dp))

                noticePdf.forEach { pdf ->
                    DisplayPDF(fileName = pdf.fileName, date = formatter.format(pdf.uploadedDate),
                        onClick = {viewModel.currentPdfUrl.value = pdf.url!!
                            viewModel.downloadPdf(context)
                            Toast.makeText(context, "Downloading ${pdf.fileName}", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

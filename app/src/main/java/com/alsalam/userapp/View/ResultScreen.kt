package com.alsalam.userapp.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alsalam.userapp.R
import com.alsalam.userapp.View.Components.DisplayPDF
import com.alsalam.userapp.ViewModel.PDFViewModel
import java.text.SimpleDateFormat

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SimpleDateFormat")
@Composable
fun ResultScreen(viewModel: PDFViewModel){

    val formatter = SimpleDateFormat("dd/MM/yyyy")
    val noticePdf by viewModel._result.collectAsState(emptyList())
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.loadResultPdf()
    }

    Scaffold(topBar = { CustomTopBar(text = "Result", fontSize = 12)}) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            item{
                Spacer(modifier = Modifier.height(85.dp))

                noticePdf.forEach { pdf ->
                    DisplayPDF(fileName = pdf.studentId, date = formatter.format(pdf.uploadedDate),
                        onClick = {viewModel.currentPdfUrl.value = pdf.url!!
                            viewModel.downloadPdf(context)
                            Toast.makeText(context, "Downloading Result", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(text: String, fontSize: Int){
    TopAppBar(title = {Text(text, color = colorResource(id = R.color.white))},
        colors = TopAppBarDefaults.topAppBarColors(colorResource(id = R.color.secondary_blue)))
}
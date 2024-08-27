package com.alsalam.userapp.View.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alsalam.userapp.R

@Composable
fun CustomTextHeading(heading: String, fontSize: Int, fontWeight: FontWeight, color: Color){
    Text(heading, fontWeight = fontWeight, fontSize = fontSize.sp, color = color)
}


@Composable
fun StudentCard(name: String, studentID: String, dob: String){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(120.dp)
        .background(Color.Transparent),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)),
        elevation = CardDefaults.elevatedCardElevation(10.dp)
    )
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            verticalArrangement = Arrangement.Center
            )
        {
            Text(name.uppercase(), color = colorResource(id = R.color.white), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text("Student ID: $studentID", color = colorResource(id = R.color.white), fontWeight = FontWeight.Normal, fontSize = 18.sp)
            Text("Date Of Birth: $dob", color = colorResource(id = R.color.white), fontWeight = FontWeight.Normal, fontSize = 18.sp)
        }
    }
}


@Composable
fun CustomCard(image: Painter, heading: String, height: Int = 100, width: Int = 100, imageHeight: Int = 40,
               imageWidth: Int = 40, textHeight: Int = 11, onClick: () -> Unit)
{
    Card(modifier = Modifier
        .height(height.dp)
        .width(width.dp)
        .clickable {
            onClick()
        },
        elevation = CardDefaults.cardElevation(12.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue))
    )
    {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center)
        {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = image, contentDescription = "",
                    modifier = Modifier
                        .width(imageWidth.dp)
                        .height(imageHeight.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(5.dp))

                Text(heading,
                    fontSize = textHeight.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White)
            }
        }
    }
}


@Composable
fun DisplayPDF(fileName: String, date: String, onClick:() -> Unit){
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)
        .background(Color.Transparent),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.secondary_blue)))
    {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    fileName, color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 20.sp)
                Text(date, color = Color.White, fontWeight = FontWeight.Medium, fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(painter = painterResource(id = R.drawable.download), contentDescription = "", modifier = Modifier.size(20.dp).clickable { onClick() }, tint = Color.White)
            }
        }
    }
}


// Save/Upload Button
@Composable
fun SaveUploadButton(title: String, onClick: () -> Unit){
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_blue)),
        onClick = {onClick()})
    {
        Text(title)
    }
}

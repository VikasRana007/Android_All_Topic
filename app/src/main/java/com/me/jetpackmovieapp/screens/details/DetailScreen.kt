package com.me.jetpackmovieapp.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.me.jetpackmovieapp.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController, movieData : String?){

     Scaffold(topBar = {
          TopAppBar(backgroundColor = Color.Yellow,
               elevation = 5.dp) {
               Row(horizontalArrangement = Arrangement.Start) {
                    Icon(imageVector =Icons.Default.ArrowBack , contentDescription ="Arrow Back",
                    modifier = Modifier.clickable{
                         navController.popBackStack()
                    })
                    Spacer(modifier = Modifier.width(100.dp))
               }
               Text(text = "Movies", fontFamily = FontFamily(Font(R.font.poppins_semi_bold)))
          }
     }){
          Surface(modifier = Modifier
               .fillMaxHeight()
               .fillMaxWidth()) {
               Column(horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Text(text = movieData.toString(), fontFamily = FontFamily(Font(R.font.poppins_bold)))
                    Spacer(modifier = Modifier.height(23.dp))
                    Button(onClick = {
                         navController.popBackStack()
                    }) {
                         Text(text = "Go Back")
                    }
               }
          }
     }


}


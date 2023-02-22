package com.me.jetpackmovieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.me.jetpackmovieapp.MovieRow
import com.me.jetpackmovieapp.R
import com.me.jetpackmovieapp.navigation.MovieScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController ){
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Cyan,
            elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start) {
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Movies", fontFamily = FontFamily(Font(R.font.poppins_semi_bold)))
            }

        }
    }){
        MainContent(navController = navController)
    }
}

@Composable
fun MainContent(navController: NavController,
    movieList: List<String> = listOf(
    "Avatar", "300", "Harry Potter", "Iron Man", "Avenger Endgame"
    , "Captain America"
    , "Salt"
    , "Lucy"
)){
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(items = movieList){
                        MovieRow(movie = it){ movie->
//                            Text(text = movie, style = R.font.poppins_bold)
//                        Text(text = movie, modifier = Modifier, fontFamily = FontFamily(Font(R.font.poppins_bold)))
                            navController.navigate(route = MovieScreens.DetailScreen.name+"/$movie")
//                    }
//                    Log.d("ITEM CLICKED :", "MainContent: $movie")
                }
            }
        }
    }
}
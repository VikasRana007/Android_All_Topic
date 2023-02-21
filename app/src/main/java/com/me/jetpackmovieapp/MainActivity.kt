package com.me.jetpackmovieapp

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.me.jetpackmovieapp.ui.theme.JetpackMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable() ()->Unit) {
    JetpackMovieAppTheme {
       Scaffold(topBar = {
           TopAppBar(backgroundColor = Color.Magenta,
           elevation = 5.dp) {
               Text(text = "Movies")
           }
       }) {
            content()
       }

    }
}

@Composable
fun MainContent(movieList: List<String> = listOf(
    "Avatar", "300", "Harry Potter", "Iron Man", "Avenger Endgame"
    , "Captain America"
    , "Salt"
    , "Lucy"
)){

    Column(modifier = Modifier.padding(12.dp)) {
      LazyColumn{
        items(items = movieList){
            MovieRow(movie = it)
        }
      }
    }

}

@Composable
fun MovieRow(movie:String){
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .height(130.dp),
    shape = RoundedCornerShape(corner = CornerSize(16.dp))
        , elevation = 6.dp) {
        Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                 shape = RectangleShape,
                 elevation = 4.dp) {
                 Icon(imageVector = Icons.Default.AccountBox,
                     contentDescription = "Movie Image")

            }
            Text(text = movie)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MyApp{
      MainContent()
  }
}
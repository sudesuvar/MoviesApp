package com.example.moviesapp.Activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import com.example.moviesapp.Domain.FilmItemModel
import com.example.moviesapp.R

class DetailMoviesActivity : BaseActivity() {
    private lateinit var fimItem:FilmItemModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fimItem= (intent.getSerializableExtra("object") as? FilmItemModel)!!

        setContent{
            DetailScreen(film = fimItem, onBackClick = {
                finish()
            })
        }
    }
}

@Composable
fun DetailScreen(film: FilmItemModel, onBackClick: () -> Unit){
    val scrollState = rememberScrollState()
    val isLoading = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.blackBackground))
    ){

        if (isLoading.value){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }else{
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ){

                Box(
                    modifier = Modifier.height(400.dp)
                ){
                    Image(
                        contentDescription = "",
                        painter = painterResource(id = R.drawable.back),
                        modifier = Modifier
                            .padding(start = 16.dp, top=48.dp)
                            .clickable { onBackClick() }
                    )
                    Image(
                        contentDescription = "",
                        painter = painterResource(id = R.drawable.fav),
                        modifier = Modifier
                            .padding(end = 16.dp, top=48.dp)
                            .align(Alignment.TopEnd)
                    )

                    AsyncImage(
                        model = film.Poster,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.1f
                    )
                    AsyncImage(
                        model = film.Poster,
                        contentDescription = "",
                        modifier = Modifier
                            .size(210.dp, 300.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .align(Alignment.BottomCenter),
                        contentScale = ContentScale.Crop
                    )


                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        colorResource(R.color.black2),
                                        colorResource(R.color.black1)
                                    ),
                                    start = Offset(0f, 0f),
                                    end = Offset(0f,Float.POSITIVE_INFINITY)
                                )
                            )
                    ){
                        Text(
                            text = film.Title,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 27.sp,
                            ),
                            modifier = Modifier
                                .padding(end = 16.dp, top = 48.dp)
                                .align(Alignment.BottomCenter)


                        )
                        Spacer(modifier = Modifier.height(48.dp))
                    }

                }
                Spacer(modifier = Modifier.height(32.dp))

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 24.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                                
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.star),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                )
                                Text(
                                    text = "IMDB: ${film.Imdb}",
                                    color = Color.White
                                )

                                Icon(
                                    painter = painterResource(R.drawable.time),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                )
                                Text(
                                    text = "Tıme: ${film.Time}",
                                    color = Color.White
                                )
                                Icon(
                                    painter = painterResource(R.drawable.cal),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                )
                                Text(
                                    text = "Release: ${film.Year}",
                                    color = Color.White
                                )

                            }

                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "Summary",
                                color = Color.White,
                                fontSize = 16.sp,
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = film.Description,
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(24.dp))

                            Text(
                                text = "Actors",
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            LazyRow(contentPadding = PaddingValues(8.dp)) {
                                items(film.Casts.size) {
                                    film.Casts[it].Actor?.let {
                                    Text(
                                        text = "$it, ",
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        modifier = Modifier.padding(end = 8.dp)
                                    )
                                    }
                                }
                            }
                        }

                        LazyRow(contentPadding = PaddingValues(8.dp)) {
                            items(film.Casts.size) {
                                AsyncImage(
                                    model = film.Casts[it].PicUrl,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(75.dp)
                                        .padding(end = 4.dp)
                                        .clip(RoundedCornerShape(50.dp)),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }


                    }


            }
        }

    }


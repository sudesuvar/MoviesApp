package com.example.moviesapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(hint: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(50.dp)
            .background(
                color = Color(0x20ffffff),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = painterResource(id = R.drawable.search),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(
                    text= hint, color = Color(0xffbdbdbd)
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent,
                focusedPlaceholderColor = Color.White,
                focusedTextColor =  Color.White,
                unfocusedTextColor = Color.White,

            ),
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
            ),
            shape = RoundedCornerShape(50.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(8.dp))

        Icon(
            painter = painterResource(id = R.drawable.microphone),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
        )
    }
}

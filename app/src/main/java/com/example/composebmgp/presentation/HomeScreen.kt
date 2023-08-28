package com.example.currencyconverter.persentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.currencyconverter.R

@Composable
@Preview(showBackground = true)
fun HomeScreen() {

        var isConvertClick by remember {
            mutableStateOf(true)
        }

        var isCompareClick by remember {
            mutableStateOf(false)
        }

        ConstraintLayout {
            val ( topapp,custom, convertAndCompare) =createRefs()

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(topapp) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                TopApp()
            }

            Box(
                modifier = Modifier
                    .constrainAs(custom) {
                        top.linkTo(parent.top , margin = 215.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                CustomToggleButton(onConvertClick = { isConvertClick = true
                    isCompareClick=false} ) {
                    isConvertClick =false
                    isCompareClick =true
                }
            }

            Box(
                modifier = Modifier
                    .constrainAs(convertAndCompare) {
                        top.linkTo(topapp.bottom )
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                AnimatedVisibility(visible = isConvertClick) {
                    ConvertCard()
                }

                AnimatedVisibility(visible = isCompareClick) {
                    CompareCard()
                }
            }
        }

        Row {
            Text(
                text = "live exchange rates",
                style = TextStyle(
                    fontSize = 18.sp
                ),
                modifier = Modifier
                    .padding(start = 20.dp, top = 10.dp)
                    .weight(1f)
            )
            Row(
                modifier = Modifier
                    .padding(end = 20.dp)
                    .border(2.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_plus_icon),
                    contentDescription = null, modifier = Modifier
                        .height(30.dp)
                        .width(30.dp)
                        .padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "Add to Favorites", modifier = Modifier.padding(5.dp))
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "My Portofolio",
            style = TextStyle(
                color = Color.Black,
                fontWeight = FontWeight.Bold, fontSize = 20.sp
            ),
            modifier = Modifier.padding(start = 20.dp, top = 10.dp)
        )

        //Spacer(modifier = Modifier.height(50.dp))
    }


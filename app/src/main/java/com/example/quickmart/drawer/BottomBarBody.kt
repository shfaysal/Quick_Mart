package com.example.quickmart.drawer

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarBody (
    items: List<NavigationItem>,
    modifier : Modifier = Modifier,
    itemsTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (NavigationItem) -> Unit,
) {

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }


        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItemIndex == index,
                    onClick = {
                        selectedItemIndex = index
                        onItemClick(item)
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontSize = 20.sp,
                            fontWeight = if (index == selectedItemIndex){
                                FontWeight.Bold
                            } else {
                                FontWeight.Normal
                            }
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedItemIndex) {
                                item.selectIcon
                            }else {
                                item.unselectIcon
                            },
                            contentDescription = item.title,
//                            tint = if (index == selectedItemIndex) {
//                                Color.Green
//                            } else {
//                                Color.White
//                            }
                        )
                    },
//                    colors = NavigationBarItemDefaults.colors(
//                        selectedIconColor = Color.Green,
//                        unselectedIconColor = Color.White
//                    )
                )
        }


//        items(items){item ->
//            Column(
//                modifier = modifier
//                    .fillMaxHeight()
//                    .padding(16.dp)
//                    .clickable {
//                        onItemClick(item)
//                    },
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
//            ){
//                Icon(imageVector = item.selectIcon, contentDescription = null)
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = item.title,
//                    style = itemsTextStyle
//                )
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomBarBodyPreview(){
    BottomBarBody(items = NavigationItemList.bottomBarItemList, onItemClick = {
        Log.d("TAG","Clicked")
    })
}
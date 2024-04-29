package com.example.quickmart.drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<NavigationItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (NavigationItem) -> Unit
){

    LazyColumn (modifier) {
       items(items){ item ->
           Row (
               modifier = Modifier
                   .fillMaxWidth()
                   .clickable {
                       onItemClick(item)
                   }
                   .padding(16.dp)
           ) {
               Icon(imageVector = item.selectIcon, contentDescription = null)
               Spacer(modifier = Modifier.width(16.dp))
               Text(
                   text = item.title,
                   style = itemTextStyle,
               )
           }
       }
    }
}


@Preview(showBackground = true)
@Composable
fun DrawerBodyPreview(){
    DrawerBody(items = NavigationItemList.drawerNavigationItemList, onItemClick = {
        println(it.title)
    })
}










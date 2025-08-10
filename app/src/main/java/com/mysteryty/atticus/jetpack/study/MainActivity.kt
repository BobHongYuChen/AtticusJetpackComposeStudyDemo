package com.mysteryty.atticus.jetpack.study

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.collection.intListOf
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mysteryty.atticus.jetpack.study.MainActivity.Companion.TAG
import com.mysteryty.atticus.jetpack.study.ui.first.BirthdayCardActivity
import com.mysteryty.atticus.jetpack.study.ui.theme.AtticusJetpackComposeStudyDemoTheme

class MainActivity : ComponentActivity() {

    companion object {
       const val TAG = "Atticus"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AtticusJetpackComposeStudyDemoTheme {
                Surface(color = Color.White) {
                    MyListScreen(buildListItem(this), intent, this)
                }
            }
        }
    }
}

fun buildListItem(context: Context): ArrayList<MyItem> {
    val result = ArrayList<MyItem>()
    val titleList = context.resources.getStringArray(R.array.title_list_home_navigation)
    val summaryList = context.resources.getStringArray(R.array.summary_list_home_navigation)
    for (title: String in titleList) {
        val position = titleList.indexOf(title)
        result.add(MyItem(title, summaryList[position], position))
    }
    return result
}

@Composable
fun MyListScreen(items: ArrayList<MyItem>, intent: Intent, context: Context) {
    LazyColumn {
        items(items = items) { item ->
            MyItemComposable(item = item) { itemPosition: Int ->
                when(itemPosition) {
                    0 -> {
                        context.startActivity(Intent(context, BirthdayCardActivity::class.java))
                    }
                }
            }
        }
    }
}

@Composable
fun MyItemComposable(item: MyItem, onItemClick: (itemPosition: Int) -> Unit) {
    Column(modifier = Modifier
        .padding(16.dp, 64.dp, 16.dp, 24.dp).fillMaxWidth()
        .clickable {
            onItemClick(item.position)
        }) {
        Text(text = item.title, style = MaterialTheme.typography.headlineMedium)
        Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
    }
}

data class MyItem(val title: String, val description: String, val position: Int)
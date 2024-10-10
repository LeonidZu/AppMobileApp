package com.example.artmobileapp

import android.graphics.pdf.PdfDocument.Page
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.artmobileapp.ui.theme.ArtMobileAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtMobileAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Page(vm: GalleryViewModel) {
    val scope = rememberCroutineScope()

    val ptrState = rememberPullToRefreshState(
        refreshing = vm.isLoading,
        onRefresh = { vm.getRandom() })

    LaunchedEffect(Unit, block = {
       vm.getRandom()
    })

    if(vm.photos.isEmpty()) Text(text = "Loading...")

    Box(modifier = Modifier.pullRefresh(ptrState)) {
        LazyVerticalStaggerGrid(
            columns = StaggeredGridCells.Adaptive(200.dp),
            verticalItemSpacing = 4.dp,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.fillMaxSize(),
            content = {
                items(vm.photos.size) {
                    ImageCard(
                        image = vm.photos[item],
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    )
                }
            })
    }
}

@Composable
fun ImageCard(name: String, modifier: Modifier = Modifier) {
    SubComposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current).data(image.path).crossfade(enable:true).build(),
        "",
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtMobileAppTheme {
        Greeting("Android")
    }
}
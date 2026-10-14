package com.example.cinestream.ui.AppUI.HomeScree

import android.R.attr.text
import android.R.id.home
import android.app.Activity
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.annotation.FontRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.example.cinestream.R
import com.example.cinestream.data.Info.TrendingAnime
import com.example.cinestream.data.vm.HomeVM
import com.example.cinestream.ui.AppUI.HomeScree.Surface


val Primary = Color(0xFFFF2A54)       // Pink-red
val Secondary = Color(0xFFFF9AAE)     // Soft pink
val Tertiary = Color(0xFF00B894)      // Premium teal

val Background = Color(0xFF0D0E12)    // Almost black
val Surface = Color(0xFF191A20)       // Dark card
val SurfaceVariant = Color(0xFF23242B)

val TextPrimary = Color(0xFFF5F3F4)   // Warm white
val TextSecondary = Color(0xFFA7A5AA) // Muted gray
val Border = Color(0xFF34353D)

    var openTopAppBar =true
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(homeVM: HomeVM) {

    var response=homeVM.gettAllHomePageContent

    var results=response?.results
     var trending=results?.trending
    var openWeb by rememberSaveable{ mutableStateOf(false) }

    Scaffold(
        containerColor = Background,



            topBar = {
        if(openTopAppBar) {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Background
                    ),
                    title = {
                        Text(
                            text = "CINESTREAM",
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 2.7.sp,
                            color = Color.White,
                            modifier = Modifier.clickable {
                                openWeb = !openWeb
                            }
                        )
                    },
                    actions = {
                        Row() {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }
                            IconButton(
                                onClick = {},
                                modifier = Modifier.clip(CircleShape)
                                    .background(Primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.clip(CircleShape)
                                        .background(Color(0xFFFF2A54))
                                )
                            }
                        }
                    }
                )

            }
        },


    ) {paddingValues ->
        val scroll= rememberScrollState()
        val latestScroll=rememberScrollState()
        Box(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .background(Background)

        ) {
            Column(modifier=Modifier.fillMaxSize()) {

                Spacer(modifier = Modifier.height(10.dp))
                TopRecomendation()
                Spacer(modifier = Modifier.height(7.dp))
                Column() {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Trending Now",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterEnd
                    ) {
                        Text(
                            text = "SEE ALL",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Normal,
                            color = Primary
                        )
                    }

                }
                    LazyRow {
                        items(trending.orEmpty()) { anime ->
                            Column(){
                            Trending(result = anime)
//                                Text(
//                                    anime.title,
//                                    color=TextPrimary
//                                )
                            }

                        }
                    }

            }
                Spacer(modifier=Modifier.height(7.dp))
                Column() {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Latest Anime Simulcasts",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Text(
                                text = "SEE ALL",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                color = Primary
                            )
                        }

                    }
                    Row(modifier=Modifier.horizontalScroll(latestScroll)){
                        for(i in 0..5){
                            Latest()

                        }
                    }
                }

            }

            if (openWeb) {
                WebView(
                    onClose = {
                        openWeb = false
                    }
                )
            }

        }
    }
}

@Composable
fun TopRecomendation(){
    val image=""
    Surface(modifier=Modifier.fillMaxWidth().padding(20.dp)){
           AsyncImage(
               model = image,
               contentDescription = null,
               placeholder = painterResource(R.drawable.movie),
               error = painterResource(R.drawable.movie)
           )
       Box(modifier=Modifier.fillMaxWidth()) {
           Column(modifier=Modifier.fillMaxWidth()) {
               Spacer(modifier = Modifier.height(60.dp))
               Row(modifier = Modifier.fillMaxWidth().padding(start = 15.dp)) {
                   Text(
                       text = "2024",
                       color = TextSecondary,
                       fontWeight = FontWeight.ExtraBold

                   )
                   Spacer(modifier = Modifier.width(13.dp))
                   Text(
                       text = "2h 46M",
                       color = TextSecondary,
                       fontWeight = FontWeight.ExtraBold
                   )
                   Spacer(modifier = Modifier.width(13.dp))
                   Text(
                       text = "4K UHD",
                       color = TextPrimary,
                       fontWeight = FontWeight.ExtraBold
                   )
               }
               Text(
                   text = "Dune:Part Two",
                   fontSize = 30.sp,
                   color = TextPrimary,
                   modifier = Modifier.shadow(10.dp)
               )
               Box() {
                   
               Row() {
                   Box(
                       modifier = Modifier
                           .clip(RoundedCornerShape(30.dp))
                           .background(Primary)
                   ) {
                       Box(modifier = Modifier.padding(vertical = 10.dp, horizontal = 25.dp)) {
                           Row(
                               horizontalArrangement = Arrangement.Center,
                               verticalAlignment = Alignment.CenterVertically
                           ) {
                               Icon(
                                   imageVector = Icons.Default.PlayArrow,
                                   contentDescription = null,
                                   tint = Color.White
                               )
                               Spacer(modifier = Modifier.width(5.dp))
                               Text(
                                   text = "Watch Film",
                                   color = TextPrimary,
                                   fontWeight = FontWeight.Bold,
                                   modifier = Modifier
                                       .shadow(9.dp)
                               )
                           }
                       }

                   }
               }
           }

       }

    }

    }
}

@Composable
fun Trending(result: TrendingAnime){

    var poster=result.poster
    Surface(modifier=Modifier
        .padding(horizontal =10.dp, vertical =4.dp)
        .height(220.dp)
        .width(120.dp)
     ){
        AsyncImage(
            model=poster,
            contentDescription = null,
            placeholder = painterResource(R.drawable.movie),
            error = painterResource(R.drawable.movie),
            onError = { state ->
                android.util.Log.e(
                    "PosterDebug",
                    "Image failed: ${result.title}, URL: ${result.poster}",
                    state.result.throwable
                )
            },
            onSuccess = {
                android.util.Log.d(
                    "PosterDebug",
                    "Image loaded: ${result.title}"
                )
            },
            contentScale = ContentScale.Crop

        )
    }
}

@Composable
fun Latest(){
    var poster=""
    Surface(modifier=Modifier
        .padding(horizontal =10.dp, vertical =4.dp)
        .height(290.dp)
        .width(290.dp)
    ){
        AsyncImage(
            model=poster,
            contentDescription = null,
            placeholder = painterResource(R.drawable.movie),
            error = painterResource(R.drawable.movie)

        )
    }
}
//@Composable
//fun WebView(){
//    Box(modifier=Modifier.fillMaxSize()){
//        AndroidView(
//            factory={context->
//                WebView(context).apply{
//                    settings.javaScriptEnabled = true
//                    settings.domStorageEnabled = true
//                    webViewClient= WebViewClient()
//                    loadUrl("https://gaiaflix.live/")
//                }
//            }
//        )
//    }
//
//}




@Composable
fun WebView(onClose: () -> Unit) {
    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    var fullscreenView by rememberSaveable() {
        mutableStateOf<View?>(null)
    }

    var customViewCallback by rememberSaveable() {
        mutableStateOf<WebChromeClient.CustomViewCallback?>(null)
    }
    val context = LocalContext.current

    val activity = remember(context) {
        var currentContext = context

        while (
            currentContext is ContextWrapper &&
            currentContext !is Activity
        ) {
            currentContext = currentContext.baseContext
        }

        currentContext as? Activity
    }
    var previousOrientation by rememberSaveable() {
        mutableIntStateOf(
            activity?.requestedOrientation
                ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        )
    }

    BackHandler {
        if (fullscreenView != null) {
            customViewCallback?.onCustomViewHidden()
            fullscreenView = null
            customViewCallback = null
        } else {
            val currentWebView = webView

            if (currentWebView != null && currentWebView.canGoBack()) {
                currentWebView.goBack()
            } else {
                onClose()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { context ->
                WebView(context).apply {
                    webView = this

                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.mediaPlaybackRequiresUserGesture = false

                    webViewClient = object : WebViewClient() {

                        private fun handleNavigation(url: String): Boolean {
                            val scheme = Uri.parse(url).scheme?.lowercase()

                            return when (scheme) {
                                "http", "https" -> false
                                else -> {
                                    android.util.Log.d(
                                        "WebViewNavigation",
                                        "Blocked URL: $url"
                                    )
                                    true
                                }
                            }
                        }

                        override fun shouldOverrideUrlLoading(
                            view: WebView,
                            request: WebResourceRequest
                        ): Boolean {
                            return handleNavigation(request.url.toString())
                        }

                        @Suppress("DEPRECATION")
                        override fun shouldOverrideUrlLoading(
                            view: WebView,
                            url: String
                        ): Boolean {
                            return handleNavigation(url)
                        }

                        override fun onPageFinished(
                            view: WebView,
                            url: String
                        ) {
                            super.onPageFinished(view, url)

                            view.evaluateJavascript(
                                """
    (() => {
        const text = document.body?.innerText || "";
        const jawanVisible = text.toLowerCase().includes("jawan");

        const info = {
            pageUrl: location.href,
            pageTitle: document.title,
            bodyContainsJawan: jawanVisible,
            bodyTextPreview: text.slice(0, 1500),
            visibleElements: [...document.querySelectorAll("a, article, [class*='search'], [class*='result']")]
                .filter(el => {
                    const r = el.getBoundingClientRect();
                    const s = getComputedStyle(el);
                    return r.width > 0 &&
                           r.height > 0 &&
                           s.display !== "none" &&
                           s.visibility !== "hidden";
                })
                .slice(0, 40)
                .map(el => ({
                    tag: el.tagName,
                    className: typeof el.className === "string" ? el.className : "",
                    text: (el.innerText || "").trim().slice(0, 120)
                }))
        };

        return JSON.stringify(info);
    })();
    """.trimIndent()
                            ) { result ->
                                android.util.Log.d("GaiaflixSearch", result ?: "No result")
                            }



                    webChromeClient = object : WebChromeClient() {

                        override fun onShowCustomView(
                            view: View,
                            callback: CustomViewCallback
                        ) {
                            if (fullscreenView != null) {
                                callback.onCustomViewHidden()
                                return
                            }
                            previousOrientation =
                                activity?.requestedOrientation
                                    ?: ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

                            activity?.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

                            fullscreenView = view
                            customViewCallback = callback
                            openTopAppBar=false
                        }

                        override fun onHideCustomView() {
                            fullscreenView = null
                            customViewCallback = null
                            activity?.requestedOrientation = previousOrientation
                            openTopAppBar=true
                        }
                    }

                    loadUrl("https://gaiaflix.live/")
                }
            }



        fullscreenView?.let { view ->
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    FrameLayout(context).apply {
                        setBackgroundColor(android.graphics.Color.BLACK)

                        (view.parent as? ViewGroup)?.removeView(view)

                        addView(
                            view,
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        )
                    }
                },
                update = { container ->
                    if (view.parent !== container) {
                        (view.parent as? ViewGroup)?.removeView(view)

                        container.addView(
                            view,
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        )
                    }
                }
            )
        }
    }
}








//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun make(){
//    HomePage()
//}

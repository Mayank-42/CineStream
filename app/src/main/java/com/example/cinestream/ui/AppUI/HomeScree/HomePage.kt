package com.example.cinestream.ui.AppUI.HomeScree

import android.R.attr.font
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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.example.cinestream.R
import com.example.cinestream.data.Info.PopularMovie
import com.example.cinestream.data.Info.Trending
import com.example.cinestream.data.Info.UpcomingMovie
import com.example.cinestream.data.Info.UpcomingMoviesResponse
import com.example.cinestream.data.vm.HomeVM


val Primary = Color(0xFFFF2A54)
val Secondary = Color(0xFFFF9AAE)
val Tertiary = Color(0xFF00B894)

val Background = Color(0xFF0D0E12)
val Surface = Color(0xFF191A20)
val SurfaceVariant = Color(0xFF23242B)

val TextPrimary = Color(0xFFF5F3F4)
val TextSecondary = Color(0xFFA7A5AA)
val Border = Color(0xFF34353D)

var openTopAppBar = true

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(homeVM: HomeVM) {

    var response = homeVM.gettAllHomePageContent?.results

    var upcomingresponse = homeVM.gettAllUpcomingContent?.results

    var popularresponse = homeVM.gettAllPopularContent



    var openWeb by rememberSaveable {
        mutableStateOf(false)
    }

    Scaffold(
        containerColor = Background,
        topBar = {
            if (openTopAppBar) {
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
                        Row {
                            IconButton(onClick = {}) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Primary)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color(0xFFFF2A54))
                                )
                            }
                        }
                    }
                )
            }
        }
    ) { paddingValues ->

        val scroll = rememberScrollState()
        val latestScroll = rememberScrollState()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Background)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                LazyRow {
                    items(popularresponse) { anime ->
                        Column {
                            TopRecomendation(anime)
                        }
                    }
                }


                Spacer(modifier = Modifier.height(7.dp))

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
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
                        items(response.orEmpty()) { anime ->
                            Column {
                                Trending(result = anime)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(7.dp))

                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
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

                    LazyRow {
                        items(upcomingresponse.orEmpty()) { anime ->
                            Column {
                                Latest(result = anime)
                            }
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
fun TopRecomendation(result: PopularMovie) {

    val image = ""

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        AsyncImage(
            model = image,
            contentDescription = null,
            placeholder = painterResource(R.drawable.movie),
            error = painterResource(R.drawable.movie)
        )

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(60.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp)
                ) {
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

                Box {
                    Row {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(30.dp))
                                .background(Primary)
                        ) {
                            Box(
                                modifier = Modifier.padding(
                                    vertical = 10.dp,
                                    horizontal = 25.dp
                                )
                            ) {
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
                                        modifier = Modifier.shadow(9.dp)
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
fun Trending(result: Trending) {

    val poster = result.posterPath?.let {
        "https://image.tmdb.org/t/p/w500$it"
    }
//    result.

    Surface(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .height(220.dp)
            .width(120.dp)
    ) {
        Column(Modifier.fillMaxSize()) {
        AsyncImage(
            model = poster,
            contentDescription = null,
            placeholder = painterResource(R.drawable.movie),
            error = painterResource(R.drawable.movie),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(170.dp)
        )
            Spacer(modifier=Modifier.padding(top=5.dp))
            Text(
                text=result.title,
                color = TextPrimary,
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraBold

            )
        }
    }
}

@Composable
fun Latest(result: UpcomingMovie) {

    val poster = result.backdropPath?.let {
        "https://image.tmdb.org/t/p/w500$it"
    }

    Surface(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 4.dp)
            .height(290.dp)
            .width(200.dp)
    ) {
        Column() {

            AsyncImage(
                model = poster,
                contentDescription = null,
                placeholder = painterResource(R.drawable.movie),
                error = painterResource(R.drawable.movie),
//                modifier=Modifier.fillMaxSize()
            )
            Spacer(modifier=Modifier.padding(top=5.dp))
            Text(
                text=result.originalTitle
            )
        }
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
//}

@Composable
fun WebView(onClose: () -> Unit) {

    var webView by remember {
        mutableStateOf<WebView?>(null)
    }

    var fullscreenView by remember {
        mutableStateOf<View?>(null)
    }

    var customViewCallback by remember {
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

    var previousOrientation by rememberSaveable {
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
        AndroidView<WebView>(
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
                            return handleNavigation(
                                request.url.toString()
                            )
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
            const css = `
                .watch-shell {
                    height: auto !important;
                    min-height: 240px !important;
                }

                .gaia-static-player,
                .svp {
                    height: 240px !important;
                }

                video.svp-video {
                    display: block !important;
                    width: 100% !important;
                    height: 240px !important;
                }
            `;

            let style = document.getElementById(
                'cinestream-player-fix'
            );

            if (!style) {
                style = document.createElement('style');
                style.id = 'cinestream-player-fix';
                document.head.appendChild(style);
            }

            style.textContent = css;
        })();
        """.trimIndent(),
                                null
                            )

                            view.evaluateJavascript(
                                """
                                (() => {
                                    const applySearchFix = () => {
                                        const dialog =
                                            document.querySelector(
                                                ".overlay-dialog"
                                            );

                                        const shell =
                                            dialog?.querySelector(
                                                "form.dialog-shell.search-shell"
                                            );

                                        const results =
                                            shell?.querySelector(
                                                ".search-results"
                                            );

                                        const list =
                                            results?.querySelector(
                                                ".live-search-list"
                                            );

                                        if (!shell || !results || !list) {
                                            return null;
                                        }

                                        const setStyles = (element, styles) => {
                                            Object.entries(styles).forEach(
                                                ([property, value]) => {
                                                    element.style.setProperty(
                                                        property,
                                                        value,
                                                        "important"
                                                    );
                                                }
                                            );
                                        };

                                        // Allow the search form to expand.
                                        setStyles(shell, {
                                            "max-height": "none",
                                            "height": "auto",
                                            "overflow": "visible"
                                        });

                                        // Give the results panel enough room
                                        // and allow scrolling.
                                        setStyles(results, {
                                            "display": "block",
                                            "height": "65vh",
                                            "min-height": "260px",
                                            "max-height": "65vh",
                                            "flex-shrink": "0",
                                            "overflow-y": "auto",
                                            "overflow-x": "hidden",
                                            "visibility": "visible",
                                            "opacity": "1"
                                        });

                                        // Let the movie list use its
                                        // natural content height.
                                        setStyles(list, {
                                            "display": "grid",
                                            "position": "relative",
                                            "height": "auto",
                                            "max-height": "none"
                                        });

                                        return {
                                            formHeight: Math.round(
                                                shell.getBoundingClientRect().height
                                            ),
                                            resultsHeight: Math.round(
                                                results.getBoundingClientRect().height
                                            ),
                                            listHeight: Math.round(
                                                list.getBoundingClientRect().height
                                            ),
                                            resultCount: list.querySelectorAll(
                                                ".live-search-result"
                                            ).length
                                        };
                                    };

                                    // Try immediately if the elements exist.
                                    const initialResult = applySearchFix();

                                    // Handle search UI created later.
                                    if (!window.__gaiaflixSearchFixObserver) {
                                        window.__gaiaflixSearchFixObserver =
                                            new MutationObserver(() => {
                                                applySearchFix();
                                            });

                                        window.__gaiaflixSearchFixObserver.observe(
                                            document.documentElement,
                                            {
                                                childList: true,
                                                subtree: true,
                                                attributes: true,
                                                attributeFilter: [
                                                    "class",
                                                    "open"
                                                ]
                                            }
                                        );
                                    }

                                    return JSON.stringify({
                                        observerInstalled: true,
                                        initialResult: initialResult
                                    });
                                })();
                                """.trimIndent()
                            ) { result ->
                                android.util.Log.d(
                                    "GaiaflixSearchFix",
                                    result ?: "No result"
                                )
                            }
                        }
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
                            openTopAppBar = false
                        }

                        override fun onHideCustomView() {
                            fullscreenView = null
                            customViewCallback = null

                            activity?.requestedOrientation =
                                previousOrientation

                            openTopAppBar = true
                        }
                    }

                    loadUrl("https://gaiaflix.live/")
                }
            }
        )

        fullscreenView?.let { customView ->
            AndroidView<FrameLayout>(
                modifier = Modifier.fillMaxSize(),
                factory = { context ->
                    FrameLayout(context).apply {
                        setBackgroundColor(
                            android.graphics.Color.BLACK
                        )

                        (customView.parent as? ViewGroup)
                            ?.removeView(customView)

                        addView(
                            customView,
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                        )
                    }
                },
                update = { container: FrameLayout ->
                    if (customView.parent !== container) {
                        (customView.parent as? ViewGroup)
                            ?.removeView(customView)

                        container.addView(
                            customView,
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
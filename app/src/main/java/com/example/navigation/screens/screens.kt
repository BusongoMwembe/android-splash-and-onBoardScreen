package com.example.navigation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.Icon
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.navigation.R
import com.example.navigation.models.Pager
import com.example.navigation.models.pagerList
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = {
        pagerList.size
    })
    var coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { page ->
            // Our page content
            PageUi(pager = pagerList[page])
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = pagerList.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(20.dp)

        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage==2){
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {navController.navigate("main")}) {
                    Text(text = "Get started")
                }
            }else
            {
                Button(
                    modifier=Modifier.fillMaxWidth(0.35f),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage-1)
                    }
                }) {
                    Text(text = "Prev")
                }
                Button(
                    modifier=Modifier.fillMaxWidth(0.35f),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { coroutineScope.launch {
                    pagerState.scrollToPage(pagerState.currentPage+1)
                } }) {
                    Text(
                        text = "Next",
                        style = TextStyle(
                            fontSize = 15.sp
                        )
                    )
                }
            }


                }


            }


        }




        @Composable
        fun PageUi(pager: Pager) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(painter = painterResource(id = pager.image),
                    modifier =
                    Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .fillMaxWidth(0.6f)
                        .background(color = Color.White)
                    ,
                    contentDescription = null)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = pager.description,
                    modifier=Modifier.padding(10.dp),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    fontFamily = FontFamily.Cursive
                )
                    )
            }

        }

        @Composable
        fun Navigation() {
            val navcontroller = rememberNavController()
            NavHost(navController = navcontroller, startDestination = "splash") {
                composable("splash") {
                    SplashPage(navcontroller)
                }
                composable("onBoard") {
                    OnBoardingScreen(navcontroller)
                }
                composable("main"){
                    MainPage()
                }


            }

        }

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun MainPage() {
   Scaffold(
       topBar = {
           TopAppBar(
              title = {
                  Text(
                      text = "Twasumpa Online News",
                      style = TextStyle(
                          Color.White,
                          fontSize = 15.sp,
                          fontFamily = FontFamily.SansSerif
                      )
                  )
              },
               navigationIcon = {
                   Icon(imageVector = Icons.Filled.ArrowBack, contentDescription ="" , tint = Color.White)
               },
               actions = {
                   Icon(
                       imageVector = Icons.Filled.MoreVert, contentDescription =null,
                       tint = Color.White
                   )
               },
               colors = TopAppBarDefaults.topAppBarColors(
                   MaterialTheme.colorScheme.primary
               )


           )
       }
   ) {
       Box(modifier = Modifier.padding(it)){
          TabScreen()
       }
   }
    
}








        @Composable
        fun SplashPage(navController: NavHostController) {
            val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.news6))
            var isPlaying by remember {
                mutableStateOf(true)
            }
            val progress by animateLottieCompositionAsState(
                composition = composition,
                isPlaying = isPlaying
            )

            LaunchedEffect(key1 = progress) {
                delay(2000)
                if (progress == 0f) {
                    isPlaying = true
                }
                if (progress == 1f) {
                    isPlaying = false
                }

                navController.popBackStack()
                navController.navigate("onBoard")

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LottieAnimation(
                    composition = composition,
                    modifier = Modifier
                        .size(400.dp)
                        .clickable { isPlaying = true },
                    progress = {
                        progress
                    }
                )
                Text(
                    text = "Twasumpuka News Online",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 20.sp
                    )
                )
            }

        }

        data class TabItem(val title: String, val icon: ImageVector)

        val tabItemList = listOf(
            TabItem("Home", Icons.Default.Home),
            TabItem("Contact", Icons.Default.Call),
            TabItem("Messages", Icons.Default.Email)
        )


        @Composable
        fun ContentView() {
            Column(modifier = Modifier.fillMaxSize()) {
                TabRowText()
                Spacer(modifier = Modifier.height(10.dp))
                TabRowIcons()

            }

        }

        @Composable
        fun TabRowText() {

            var tabIndex by remember {
                mutableStateOf(0)
            }
            val nameText = listOf("Home", "Spoerts", "Arts")
            TabRow(
                selectedTabIndex = tabIndex,
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            ) {
                nameText.forEachIndexed { index, s ->
                    val selected = tabIndex == index
                    Tab(
                        selected = selected,
                        onClick = {
                            tabIndex = index
                        },
                        text = {
                            Text(
                                text = s.toString(),
                                fontWeight = if (selected) {
                                    FontWeight.Bold
                                } else {
                                    FontWeight.Normal
                                }
                            )
                        },

                        selectedContentColor = Color.LightGray,
                        unselectedContentColor = Color.White,
                        interactionSource = MutableInteractionSource()
                    )
                }

            }

        }

        @Composable
        fun TabRowIcons() {
            val tabIcons = listOf(
                Icons.Default.Home,
                Icons.Default.Call,
                Icons.Default.Email
            )
            var selectedTabIndex by remember {
                mutableStateOf(0)
            }
            TabRow(
                selectedTabIndex = selectedTabIndex,
                backgroundColor = TabRowDefaults.primaryContainerColor,
                contentColor = Color.LightGray
            ) {
                tabIcons.forEachIndexed { index, imageVector ->
                    val selected = selectedTabIndex == index
                    Tab(
                        selected = selected,
                        onClick = {
                            selectedTabIndex = index
                        },
                        icon = {
                            Icon(imageVector = imageVector, contentDescription = null)
                        },
                        enabled = true,
                        interactionSource = MutableInteractionSource(),
                        selectedContentColor = Color.Magenta,
                        unselectedContentColor = Color.DarkGray
                    )
                }
            }

        }

        @OptIn(
            ExperimentalUnitApi::class, ExperimentalFoundationApi::class,
            ExperimentalMaterial3Api::class
        )


        @Composable
        fun CustomTabItem() {
            val tabIcons = listOf(
                Icons.Default.Home,
                Icons.Default.Call,
                Icons.Default.Email
            )
            var selectedTabIndex by remember {
                mutableStateOf(0)
            }
            TabRow(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxSize(),
                selectedTabIndex = selectedTabIndex,
                backgroundColor = Color.White,
                contentColor = Color.LightGray,
            ) {
                tabIcons.forEachIndexed { index, imageVector ->
                    val selected = selectedTabIndex == index
                    Tab(
                        selected = selected,
                        onClick = {
                            selectedTabIndex = index
                        },
                        enabled = true,
                        interactionSource = MutableInteractionSource(),
                        selectedContentColor = Color.Magenta,
                        unselectedContentColor = Color.DarkGray
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .height(50.dp)
                                .fillMaxWidth()
                        ) {
                            Icon(imageVector = imageVector, contentDescription = null)

                        }
                    }
                }
            }

        }


        @OptIn(ExperimentalFoundationApi::class)
        @Composable
        fun TabRow(pagerState: PagerState) {

        }

        @Composable
        fun ScreenOne() {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Hello world")
            }

        }

        @OptIn(ExperimentalFoundationApi::class, ExperimentalPagerApi::class)
        @Composable
        fun Tabse(pagerState: PagerState) {
            // in this function we are creating a list
            // in this list we are specifying data as
            // name of the tab and the icon for it.
            val list = listOf(
                "Home" to Icons.Default.Home,
                "Shopping" to Icons.Default.ShoppingCart,
                "Settings" to Icons.Default.Settings
            )
            // on below line we are creating
            // a variable for the scope.
            val scope = rememberCoroutineScope()
            // on below line we are creating a
            // individual row for our tab layout.

            TabRow(
                // on below line we are specifying
                // the selected index.
                selectedTabIndex = pagerState.currentPage,

                // on below line we are
                // specifying background color.
                backgroundColor = Color.LightGray,

                // on below line we are specifying content color.
                contentColor = Color.White,

                // on below line we are specifying
                // the indicator for the tab
                indicator = { tabPositions ->
                    // on below line we are specifying the styling
                    // for tab indicator by specifying height
                    // and color for the tab indicator.
                    TabRowDefaults.Indicator(
                        Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                        height = 2.dp,
                        color = Color.White
                    )
                }
            ) {
                // on below line we are specifying icon
                // and text for the individual tab item
                list.forEachIndexed { index, _ ->
                    // on below line we are creating a tab.
                    Tab(
                        // on below line we are specifying icon
                        // for each tab item and we are calling
                        // image from the list which we have created.
                        icon = {
                            Icon(imageVector = list[index].second, contentDescription = null)
                        },
                        // on below line we are specifying the text for
                        // the each tab item and we are calling data
                        // from the list which we have created.
                        text = {
                            Text(
                                list[index].first,
                                // on below line we are specifying the text color
                                // for the text in that tab
                                color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                            )
                        },
                        // on below line we are specifying
                        // the tab which is selected.
                        selected = pagerState.currentPage == index,
                        // on below line we are specifying the
                        // on click for the tab which is selected.
                        onClick = {
                            // on below line we are specifying the scope.
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
                }
            }

        }

        @OptIn(ExperimentalFoundationApi::class)
        @ExperimentalPagerApi
        @Composable
        fun TabsContent(pagerState: PagerState) {
            // on below line we are creating
            // horizontal pager for our tab layout.
            HorizontalPager(state = pagerState) {
                // on below line we are specifying
                // the different pages.
                    page ->
                when (page) {
                    // on below line we are calling tab content screen
                    // and specifying data as Home Screen.
                    0 -> TabContentScreen(data = "Welcome to Home Screen")
                    // on below line we are calling tab content screen
                    // and specifying data as Shopping Screen.
                    1 -> TabContentScreen(data = "Welcome to Shopping Screen")
                    // on below line we are calling tab content screen
                    // and specifying data as Settings Screen.
                    2 -> TabContentScreen(data = "Welcome to Settings Screen")
                }
            }
        }

        @Composable
        fun TabContentScreen(data: String) {
            // on below line we are creating a column
            Column(
                // in this column we are specifying modifier
                // and aligning it center of the screen on below lines.
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // in this column we are specifying the text
                Image(imageVector = Icons.Default.Home, contentDescription = null)
                Text(
                    // on below line we are specifying the text message
                    text = data,

                    // on below line we are specifying the text style.
                    style = MaterialTheme.typography.headlineLarge,

                    // on below line we are specifying the text color
                    color = Color.Green,

                    // on below line we are specifying the font weight
                    fontWeight = FontWeight.Bold,

                    //on below line we are specifying the text alignment.
                    textAlign = TextAlign.Center
                )
            }
        }

        @Composable
        fun TabScreen() {
            var tabIndex by remember { mutableStateOf(0) }

            val tabs = listOf("Home", "About", "Settings", "More", "Something", "Everything")

            Column(modifier = Modifier.fillMaxWidth()) {
                ScrollableTabRow(selectedTabIndex = tabIndex) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            icon = {
                                when (index) {
                                    0 -> Icon(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = null
                                    )

                                    1 -> Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = null
                                    )

                                    2 -> Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null
                                    )

                                    3 -> Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null
                                    )

                                    4 -> Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = null
                                    )

                                    5 -> Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = null
                                    )
                                }
                            }
                        )
                    }
                }
                when (tabIndex) {
                    0 -> HomeScreen()
                    1 -> AboutScreen()
                    2 -> SettingsScreen()
                    3 -> MoreScreen()
                    4 -> SomethingScreen()
                    5 -> EverythingScreen()
                }
            }
        }

        @Composable
        fun EverythingScreen() {
            Text(text = "Jahu")
        }

        @Composable
        fun SomethingScreen() {
            Text(text = "Jahu")
        }

        @Composable
        fun MoreScreen() {
            Text(text = "Jahu")
        }

        @Composable
        fun AboutScreen() {
            Text(text = "Jahu")
        }

        @Composable
        fun SettingsScreen() {
            Text(text = "Jahu")
        }

        @Composable
        fun HomeScreen() {
            Text(text = "Jahu")
        }


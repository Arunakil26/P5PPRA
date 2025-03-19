package com.example.projectapeepandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.sp
import com.example.projectapeepandroid.ui.theme.ProjectApeepAndroidTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectApeepAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val selectedItem = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            CustomBottomNavBar(
                navController = navController,
                selectedItem = selectedItem.value,
                onItemSelected = { selectedItem.value = it }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("search") { SearchScreen() }
            composable("discovery") { DiscoveryScreen() }
            composable("message") { MessageScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}

@Composable
fun CustomBottomNavBar(navController: NavController, selectedItem: Int, onItemSelected: (Int) -> Unit) {
    val items = listOf("home", "search", "discovery", "message", "profile")
    val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Outlined.Add, Icons.Outlined.Send, Icons.Default.Person)
    val colors = listOf(Color(0xFF8E24AA), Color(0xFF00E5FF))

    Box(
        modifier = Modifier.fillMaxWidth().height(90.dp).padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                brush = Brush.linearGradient(colors),
                size = size,
                cornerRadius = CornerRadius(60f, 60f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, screen ->
                val isSelected = selectedItem == index
                val scale by animateFloatAsState(targetValue = if (isSelected) 3f else 4f, label = "")
                val offsetY by animateDpAsState(targetValue = if (isSelected) (-20).dp else 0.dp, label = "")
                val bgColor = if (isSelected) Color.White else Color.Transparent

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .offset(y = offsetY)
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(bgColor)
                        .clickable {
                            if (selectedItem != index) {
                                onItemSelected(index)
                                navController.navigate(screen)
                            }
                        }
                ) {
                    Icon(
                        imageVector = icons[index],
                        contentDescription = screen,
                        tint = if (isSelected) Color.Black else Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ScrollableBoxSection() {
    val items = List(5) { it + 1 } // Membuat daftar 5 item
    var selectedItem by remember { mutableStateOf(3) } // Mulai dari item ke-3 (tengah)

    val listState = rememberLazyListState()

    LaunchedEffect(selectedItem) {
        listState.animateScrollToItem(selectedItem - 1)
    }

    LazyRow(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 32.dp)
    ) {
        items(items) { index ->
            val scale by animateFloatAsState(
                targetValue = if (index == selectedItem) 1.3f else 1f
            )

            Box(
                modifier = Modifier
                    .size((100 * scale).dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFADD8E6))
                    .clickable { selectedItem = index }
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "$index", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Welcome Home!", fontSize = 24.sp)
        ScrollableBoxSection()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Top Bar with Search
            TopAppBar(
                title = { Text("Nyari Nama Orang") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back action */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Search bar can be added here if needed
                }
            )

            // Categories
            CategorySection("UI/UX")
            CategorySection("Android")
            CategorySection("Animasi")
        }
    }
}




@Composable
    fun CategorySection(title: String) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = title,
                fontSize = 20.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 1..3) { // Replace with dynamic content as needed
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable { /* Handle item click */ }
                            .padding(8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Item $i")
                    }
                }
                Text(
                    text = "View All",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                        .clickable { /* Handle View All click */ }
                )
            }
        }
    }



@Composable
fun DiscoveryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Discovery Screen")
    }
}

@Composable
fun MessageScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Message Screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile Screen")
    }
}

@Preview(showBackground = true)
@Composable
fun MyApppreview(){
    ProjectApeepAndroidTheme {
        MyApp()
    }
}



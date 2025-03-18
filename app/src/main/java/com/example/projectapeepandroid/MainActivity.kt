@file:Suppress("UNREACHABLE_CODE")

package com.example.projectapeepandroid

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectapeepandroid.ui.theme.ProjectApeepAndroidTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.Alignment
import androidx.wear.compose.material.Chip

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectApeepAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
//                    Profil()
                    MyApp()

                }
            }
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar {
        val items = listOf("home", "search", "orders", "profile")
        val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.ShoppingCart, Icons.Default.Person)
        val selectedItem = remember { mutableStateOf(0) }

        items.forEachIndexed { index, screen ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = screen) },
                selected = selectedItem.value == index,
                onClick = {
                    selectedItem.value = index
                    navController.navigate(screen)
                }
            )
        }
    }
}

@Composable
fun SearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Search Screen")
    }
}


@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Categories", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            listOf("Cappuccino", "Latte", "Espresso").forEach {
                Chip(text = it)
            }
        }

        ProductCard()
    }
}

@Composable
fun ProductCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Text("Product", modifier = Modifier.padding(16.dp))
    }
}


@Composable
fun OrdersScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Orders Screen")
    }
}



@Composable
fun ProfileScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("search") { SearchScreen() }
            composable("orders") { OrdersScreen() }
            composable("profile") { ProfileScreen() }
        }
    }
}




@Composable
fun Chip(text: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.padding(end = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFD3A984))
    ) {
        Text(
            text,
            modifier = Modifier.padding(8.dp),
            color = Color.White
        )
    }
}

@Composable
fun CustomCard(
    shape: RoundedCornerShape,
    elevation: Dp,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        shape = shape,
        shadowElevation = elevation,
        modifier = modifier
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            content()
        }
    }
}













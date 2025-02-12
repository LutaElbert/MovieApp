package com.group.movieapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Camera
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            //TopAppBarComponent()
        },
        bottomBar = {
            //BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavigationItem("Home", Icons.Filled.Home),
        NavigationItem("History", Icons.Filled.History),
        NavigationItem("Scanner", Icons.Outlined.Camera), // Floating Button
        NavigationItem("Profile", Icons.Filled.Person),
        NavigationItem("Settings", Icons.Filled.Settings)
    )

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Box(Modifier.fillMaxWidth()) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            items.forEach { item ->
                if (item.title == "Scanner") {
                    Box(modifier = Modifier
                        .weight(1f)
                        , contentAlignment = Alignment.Center) {
                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                            elevation = FloatingActionButtonDefaults.elevation(0.dp),
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(imageVector = item.icon, contentDescription = "Scanner")
                        }
                    }
                } else {
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        selected = currentRoute == item.title.lowercase(),
                        onClick = {
                            navController.navigate(item.title.lowercase()) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        alwaysShowLabel = true,
                        label = { Text(item.title) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarComponent() {
    TopAppBar(
        title = { Text("Testing") },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Localized description",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color(0xFF00BCD4)
        )
    )
}

data class NavigationItem(val title: String, val icon: ImageVector)

package com.ucne.registro_navigation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ucne.registro_navigation.ui.theme.Registro_NavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Registro_NavigationTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        HomeScreen(navController)
                    }
                    composable("about") {
                        AboutScreen(navController)
                    }
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable("login") {
                            val viewModel = viewModel<SampleViewModel>()
                            LoginScreen(navController, viewModel)
                        }
                        composable("register") {
                            RegisterScreen(navController)
                        }
                        composable("forgot_password") {
                            ForgotPasswordScreen(navController)
                        }
                    }
                    navigation(
                        startDestination = "calendar_overview",
                        route = "calendar"
                    ) {
                        composable("calendar_overview") {
                            CalendarOverviewScreen(navController)
                        }
                        composable("calendar_entry") {
                            CalendarEntryScreen(navController)
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnrememberedGetBackStackEntry", "RememberReturnType")
@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(): T {
    val navGraphRoute = destination.hierarchy.first().route ?: return viewModel()
    val parentEntry = remember(navGraphRoute) {
        val navController = null
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}

fun <T> NavBackStackEntry.viewModel(parentEntry: Unit): T {
    TODO("Not yet implemented")
}

fun Nothing?.getBackStackEntry(navGraphRoute: String) {
    TODO("Not yet implemented")
}

class SampleViewModel : ViewModel() {
}

@Composable
fun HomeScreen(navController: NavController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Home Screen")
            Button(
                onClick = {
                    navController.navigate("about")
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Go to About Screen")
            }
        }
    }
}

@Composable
fun AboutScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("About Screen")
        Button(
            onClick = {
                navController.navigate("home")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Go to Home Screen")
        }
    }
}

@Composable
fun LoginScreen(navController: NavController, viewModel: SampleViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                navController.navigate("calendar") {
                    popUpTo("auth") {
                        inclusive = true
                    }
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Login")
        }
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Register")
        }
    }
}

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Forgot Password")
        }
    }
}

@Composable
fun CalendarOverviewScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calendar Overview Screen")
        Button(
            onClick = {
                navController.navigate("calendar_entry")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Go to Calendar Entry Screen")
        }
    }
}

@Composable
fun CalendarEntryScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Calendar Entry Screen")
        Button(
            onClick = {
                navController.navigate("calendar_overview")
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Go to Calendar Overview Screen")
        }
    }
}

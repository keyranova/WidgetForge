package com.keyraco.widgetforge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.keyraco.widgetforge.builder.BuilderScreen
import com.keyraco.widgetforge.common.Screens
import com.keyraco.widgetforge.home.HomeScreen
import com.keyraco.widgetforge.ui.LocalNavController
import com.keyraco.widgetforge.ui.theme.WidgetForgeTheme
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            CompositionLocalProvider(
                LocalNavController provides navController
            ) {
                WidgetForgeTheme {
                    NavHost(
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                        startDestination = Screens.HOME.route
                    ) {
                        composable(Screens.HOME.route) {
                            HomeScreen()
                        }
                        composable(
                            Screens.BUILDER.route,
                            arguments = listOf(navArgument("id") {
                                nullable = false
                            })
                        ) {
                            val id = it.arguments?.getString("id")?.let {
                                UUID.fromString(it)
                            } ?: return@composable
                            BuilderScreen(id)
                        }
                    }
                }
            }
        }
    }
}

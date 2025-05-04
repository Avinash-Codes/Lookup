package com.avinash.lookup

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.avinash.lookup.ui.theme.LookupTheme
import com.avinash.lookup.userinterface.BusinessSearchScreen
import com.avinash.lookup.userinterface.UserBusinessRegistration
import com.avinash.lookup.viemodel.UserProfile

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val language = prefs.getString("language", "en") ?: "en"
        val context = LocalizationManager.setLocale(this, language)
        resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)

        enableEdgeToEdge()
        setContent {

            LookupApp()

        }
    }
    override fun attachBaseContext(newBase: Context) {
        val prefs = newBase.getSharedPreferences("${newBase.packageName}_preferences", Context.MODE_PRIVATE)
        val language = prefs.getString("language", "en") ?: "en"
        val context = LocalizationManager.setLocale(newBase, language)
        super.attachBaseContext(context)
    }
}

@Composable
fun LookupApp() {
    val userProfile: UserProfile = viewModel()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "businessSearch"
    ) {
        composable("businessSearch") {
            BusinessSearchScreen(
                viewModel = userProfile,
                navController = navController
            )
        }
        composable("businessRegistration") {
            UserBusinessRegistration(
                UserProfile = userProfile,
                navController = navController
            )
        }
    }
}
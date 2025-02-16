package com.avinash.lookup

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import java.util.Locale

object LocalizationManager {
    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = context.resources.configuration
        config.setLocale(locale)

        // Return the new context with updated configuration
        return context.createConfigurationContext(config)
    }

    @Composable
    @ReadOnlyComposable
    fun getString(resId: Int): String {
        val context = LocalContext.current
        return context.getString(resId)
    }

    fun isHindiEnabled(context: Context): Boolean {
        return context.resources.configuration.locales[0].language == "hi"
    }
}
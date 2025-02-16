//package com.avinash.lookup.userinterface
//
//import android.content.Intent
//import android.net.Uri
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.rounded.ArrowForward
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material.icons.filled.Phone
//import androidx.compose.material.icons.rounded.AddCircle
//import androidx.compose.material.icons.rounded.Clear
//import androidx.compose.material.icons.rounded.Email
//import androidx.compose.material.icons.rounded.LocationOn
//import androidx.compose.material.icons.rounded.Phone
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.focus.onFocusChanged
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.avinash.lookup.LocalizationManager
//import com.avinash.lookup.R
//import com.avinash.lookup.dataclass.UserData
//import com.avinash.lookup.viemodel.UserProfile
//import com.avinash.lookup.viemodel.UserProfileEvent
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun BusinessSearchScreen(
//    viewModel: UserProfile,
//    navController: NavController,
//    modifier: Modifier = Modifier
//) {
//    var pincode by remember { mutableStateOf("") }
//    var expanded by remember { mutableStateOf(false) }
//    var selectedBusinessType by remember { mutableStateOf<String?>(null) }
//    var showSearchButton by remember { mutableStateOf(true) }
//    var isInputActive by remember { mutableStateOf(false) }
//
//    val businessTypes by viewModel.businessTypes.collectAsState()
//    val businessList by viewModel.businessList.collectAsState()
//
//    // Function to reset all states
//    fun resetAll() {
//        pincode = ""
//        selectedBusinessType = null
//        showSearchButton = true
//        expanded = false
//        viewModel._businessTypes.value = emptyList()
//        viewModel._businessList.value = emptyList()
//    }
//
//    val darkColorScheme = darkColorScheme(
//        primary = Color(0xFF86B9F5),
//        secondary = Color(0xFF6E97CC),
//        tertiary = Color(0xFF9CCAFF),
//        background = Color(0xFF121212),
//        surface = Color(0xFF1E1E1E),
//        onSurface = Color(0xFFE1E1E1),
//        onBackground = Color(0xFFE1E1E1),
//        error = Color(0xFFFF6B6B)
//    )
//
//    MaterialTheme(
//        colorScheme = darkColorScheme
//    ) {
//        Scaffold(
//            modifier = modifier.fillMaxSize(),
//            contentWindowInsets = WindowInsets(0, 0, 0, 0),
//            topBar = {
//                TopAppBar(
//                    title = {
//                        Text(
//                            text = "Business Directory",
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = MaterialTheme.colorScheme.onBackground
//                        )
//                    },
//                    actions = {
//                        IconButton(
//                            onClick = {
//                                navController.navigate("businessRegistration")
//                            }
//                        ) {
//                            Icon(
//                                imageVector = Icons.Rounded.AddCircle,
//                                contentDescription = "Add Business",
//                                tint = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                    },
//                    colors = TopAppBarDefaults.topAppBarColors(
//                        containerColor = MaterialTheme.colorScheme.background,
//                        titleContentColor = MaterialTheme.colorScheme.onBackground
//                    )
//                )
//            }
//        ) { paddingValues ->
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.background)
//                    .padding(paddingValues)
//                    .systemBarsPadding()
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(horizontal = 16.dp)
//                ) {
//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize(),
//                        contentPadding = PaddingValues(bottom = 16.dp)
//                    ) {
//
//                            item {
//                                Card(
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(bottom = 24.dp),
//                                    shape = RoundedCornerShape(24.dp),
//                                    colors = CardDefaults.cardColors(
//                                        containerColor = MaterialTheme.colorScheme.surface,
//                                    ),
//                                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//                                ) {
//                                    Column(
//                                        modifier = Modifier
//                                            .padding(24.dp)
//                                            .fillMaxWidth()
//                                    ) {
//                                        OutlinedTextField(
//                                            value = pincode,
//                                            onValueChange = {
//                                                if (it.length <= 6) {
//                                                    pincode = it
//                                                    selectedBusinessType = null
//                                                    showSearchButton = true
//                                                    viewModel._businessTypes.value = emptyList()
//                                                    viewModel._businessList.value = emptyList()
//                                                }
//                                            },
//                                            label = {
//                                                Text(
//                                                    (LocalizationManager.getString(R.string.enter_pincode)),
//                                                    color = MaterialTheme.colorScheme.onSurface.copy(
//                                                        alpha = 0.7f
//                                                    )
//                                                )
//                                            },
//                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                                            modifier = Modifier
//                                                .fillMaxWidth()
//                                                .padding(bottom = 16.dp)
//                                                .onFocusChanged { focusState ->
//                                                    if (focusState.isFocused && !isInputActive) {
//                                                        isInputActive = true
//                                                        resetAll()
//                                                    } else if (!focusState.isFocused) {
//                                                        isInputActive = false
//                                                    }
//                                                },
//                                            shape = RoundedCornerShape(16.dp),
//                                            colors = OutlinedTextFieldDefaults.colors(
//                                                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                                                unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(
//                                                    alpha = 0.2f
//                                                ),
//                                                focusedTextColor = MaterialTheme.colorScheme.onSurface,
//                                                unfocusedTextColor = MaterialTheme.colorScheme.onSurface
//                                            )
//                                        )
//
//                                        // Category Dropdown
//                                        if (businessTypes.isNotEmpty()) {
//                                            ExposedDropdownMenuBox(
//                                                expanded = expanded,
//                                                onExpandedChange = { expanded = it },
//                                                modifier = Modifier.fillMaxWidth()
//                                            ) {
//                                                OutlinedTextField(
//                                                    value = selectedBusinessType
//                                                        ?: "Select Business Category",
//                                                    onValueChange = {},
//                                                    readOnly = true,
//                                                    trailingIcon = {
//                                                        ExposedDropdownMenuDefaults.TrailingIcon(
//                                                            expanded = expanded
//                                                        )
//                                                    },
//                                                    modifier = Modifier
//                                                        .fillMaxWidth()
//                                                        .menuAnchor(),
//                                                    shape = RoundedCornerShape(16.dp),
//                                                    colors = OutlinedTextFieldDefaults.colors(
//                                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
//                                                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(
//                                                            alpha = 0.2f
//                                                        ),
//                                                        focusedTextColor = MaterialTheme.colorScheme.onSurface,
//                                                        unfocusedTextColor = MaterialTheme.colorScheme.onSurface
//                                                    )
//                                                )
//
//                                                ExposedDropdownMenu(
//                                                    expanded = expanded,
//                                                    onDismissRequest = { expanded = false },
//                                                    modifier = Modifier.background(MaterialTheme.colorScheme.surface)
//                                                ) {
//                                                    businessTypes.forEach { businessType ->
//                                                        DropdownMenuItem(
//                                                            text = { Text(businessType) },
//                                                            onClick = {
//                                                                selectedBusinessType = businessType
//                                                                expanded = false
//                                                                viewModel.fetchBusinessesFromPinCodeAndCategory(
//                                                                    pincode.toInt(),
//                                                                    businessType
//                                                                )
//                                                            }
//                                                        )
//                                                    }
//                                                }
//                                            }
//                                        }
//
//                                        // Search Button
//                                        if (showSearchButton && businessTypes.isEmpty()) {
//                                            Button(
//                                                onClick = {
//                                                    if (pincode.length == 6) {
//                                                        viewModel.fetchBusinessTypesFromPinCode(
//                                                            pincode.toInt()
//                                                        )
//                                                        showSearchButton = false
//                                                    }
//                                                },
//                                                modifier = Modifier
//                                                    .fillMaxWidth()
//                                                    .height(56.dp),
//                                                shape = RoundedCornerShape(16.dp),
//                                                enabled = pincode.length == 6,
//                                                colors = ButtonDefaults.buttonColors(
//                                                    containerColor = MaterialTheme.colorScheme.primary,
//                                                    disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
//                                                        alpha = 0.12f
//                                                    )
//                                                )
//                                            ) {
//                                                Text(
//                                                    "Search Directory",
//                                                    fontSize = 16.sp,
//                                                    fontWeight = FontWeight.Medium
//                                                )
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//
//                            // Loading State
//                            if (viewModel.isLoading.value) {
//                                item {
//                                    Box(
//                                        modifier = Modifier
//                                            .fillParentMaxSize(),
//                                        contentAlignment = Alignment.Center
//                                    ) {
//                                        CircularProgressIndicator(
//                                            color = MaterialTheme.colorScheme.primary,
//                                            modifier = Modifier.size(48.dp)
//                                        )
//                                    }
//                                }
//                            }
//
//                            // Error State
//                            if (viewModel.operationState.value is UserProfileEvent.Error) {
//                                item {
//                                    Card(
//                                        modifier = Modifier.fillMaxWidth(),
//                                        colors = CardDefaults.cardColors(
//                                            containerColor = MaterialTheme.colorScheme.error.copy(
//                                                alpha = 0.1f
//                                            )
//                                        ),
//                                        shape = RoundedCornerShape(16.dp)
//                                    ) {
//                                        Row(
//                                            modifier = Modifier.padding(16.dp),
//                                            verticalAlignment = Alignment.CenterVertically
//                                        ) {
//                                            Icon(
//                                                imageVector = Icons.Rounded.Clear,
//                                                contentDescription = "Error",
//                                                tint = MaterialTheme.colorScheme.error
//                                            )
//                                            Spacer(modifier = Modifier.width(12.dp))
//                                            Text(
//                                                text = viewModel.errorMessage.value,
//                                                color = MaterialTheme.colorScheme.error
//                                            )
//                                        }
//                                    }
//                                }
//                            }
//
//                            // Business List
//                            if (selectedBusinessType != null && !viewModel.isLoading.value) {
//                                items(businessList) { business ->
//                                    BusinessCard(business = business)
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//@Composable
//fun BusinessCard(business: UserData) {
//    val context = LocalContext.current
//
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 4.dp),
//        shape = RoundedCornerShape(16.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface
//        ),
//        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
//    ) {
//        Column(
//            modifier = Modifier
//                .padding(20.dp)
//                .fillMaxWidth()
//        ) {
//            // Business Name - Always show
//            Text(
//                text = business.userBusinessName,
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = MaterialTheme.colorScheme.onSurface
//            )
//
//            // Owner Name - Show if available
//            if (!business.userName.isNullOrBlank()) {
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(
//                    text = "Owner: ${business.userName}",
//                    fontSize = 14.sp,
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
//                )
//            }
//
//            // Business Address - Show if available
//            if (!business.userBusinessAddress.isNullOrBlank()) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.LocationOn,
//                        contentDescription = "Location",
//                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = business.userBusinessAddress,
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
//                    )
//                }
//            }
//
//            // Email - Show if available
//            if (!business.userBusinessEmail.isNullOrBlank()) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Email,
//                        contentDescription = "Email",
//                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = business.userBusinessEmail,
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
//                    )
//                }
//            }
//
//            // Website - Show if available
//            if (!business.userBusinessWebsite.isNullOrBlank()) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.Info,
//                        contentDescription = "Website",
//                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = business.userBusinessWebsite,
//                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
//                    )
//                }
//            }
//
//            // Description - Show if available
//            if (!business.userBusinessDescription.isNullOrBlank()) {
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(
//                    text = business.userBusinessDescription,
//                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
//                    fontSize = 14.sp
//                )
//            }
//
//            // Phone Number and Call Button - Always show (at the bottom)
//            Spacer(modifier = Modifier.height(12.dp))
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                horizontalArrangement = Arrangement.SpaceBetween,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Icon(
//                        imageVector = Icons.Rounded.Phone,
//                        contentDescription = "Phone",
//                        tint = MaterialTheme.colorScheme.primary,
//                        modifier = Modifier.size(16.dp)
//                    )
//                    Spacer(modifier = Modifier.width(8.dp))
//                    Text(
//                        text = business.userBusinessPhone,
//                        color = MaterialTheme.colorScheme.primary,
//                        fontWeight = FontWeight.Medium
//                    )
//                }
//
//                FilledTonalButton(
//                    onClick = {
//                        val intent = Intent(Intent.ACTION_DIAL).apply {
//                            data = Uri.parse("tel:${business.userBusinessPhone}")
//                        }
//                        context.startActivity(intent)
//                    },
//                    colors = ButtonDefaults.filledTonalButtonColors(
//                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
//                    )
//                ) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
//                        contentDescription = "Call",
//                        tint = MaterialTheme.colorScheme.primary
//                    )
//                }
//            }
//        }
//    }
//}
//



package com.avinash.lookup.userinterface

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.avinash.lookup.LocalizationManager
import com.avinash.lookup.R
import com.avinash.lookup.dataclass.UserData
import com.avinash.lookup.findActivity
import com.avinash.lookup.viemodel.UserProfile
import com.avinash.lookup.viemodel.UserProfileEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessSearchScreen(
    viewModel: UserProfile,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var pincode by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var selectedBusinessType by remember { mutableStateOf<String?>(null) }
    var showSearchButton by remember { mutableStateOf(true) }
    var isInputActive by remember { mutableStateOf(false) }

    val businessTypes by viewModel.businessTypes.collectAsState()
    val businessList by viewModel.businessList.collectAsState()

    val context = LocalContext.current

    // Function to reset all states
    fun resetAll() {
        pincode = ""
        selectedBusinessType = null
        showSearchButton = true
        expanded = false
        viewModel._businessTypes.value = emptyList()
        viewModel._businessList.value = emptyList()
    }

    val darkColorScheme = darkColorScheme(
        primary = Color(0xFF86B9F5),
        secondary = Color(0xFF6E97CC),
        tertiary = Color(0xFF9CCAFF),
        background = Color(0xFF121212),
        surface = Color(0xFF1E1E1E),
        onSurface = Color(0xFFE1E1E1),
        onBackground = Color(0xFFE1E1E1),
        error = Color(0xFFFF6B6B)
    )

    MaterialTheme(
        colorScheme = darkColorScheme
    ) {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            topBar = {

                TopAppBar(
                    title = {
                        Text(
                            text = LocalizationManager.getString(R.string.business_directory),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    actions = {
                        // Language switcher button
                        IconButton(
                            onClick = {
                                val activity = context.findActivity()
                                activity?.let {
                                    val prefs = it.getPreferences(Context.MODE_PRIVATE)
                                    val currentLanguage = prefs.getString("language", "en") ?: "en"
                                    val newLanguage = if (currentLanguage == "en") "hi" else "en"

                                    // Save new language preference
                                    prefs.edit().putString("language", newLanguage).apply()

                                    // Update locale and recreate activity
                                    val updatedContext = LocalizationManager.setLocale(it, newLanguage)
                                    it.resources.updateConfiguration(
                                        updatedContext.resources.configuration,
                                        updatedContext.resources.displayMetrics
                                    )

                                    // Recreate activity to apply changes
                                    it.recreate()
                                }
                            }
                        ) {
                            val context = LocalContext.current
                            val prefs = context.findActivity()?.getPreferences(Context.MODE_PRIVATE)
                            val currentLanguage = prefs?.getString("language", "en") ?: "en"

                            // Show text indicator based on which language to switch to
                            Text(
                                text = if (currentLanguage == "en") "हिंदी" else "ENG",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // Business registration button
                        IconButton(
                            onClick = {
                                navController.navigate("businessRegistration")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.AddCircle,
                                contentDescription = LocalizationManager.getString(R.string.business_registration),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.background,
                        titleContentColor = MaterialTheme.colorScheme.onBackground
                    )
                )

            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(paddingValues)
                    .systemBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {

                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 24.dp),
                                shape = RoundedCornerShape(24.dp),
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.surface,
                                ),
                                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(24.dp)
                                        .fillMaxWidth()
                                ) {
                                    OutlinedTextField(
                                        value = pincode,
                                        onValueChange = {
                                            if (it.length <= 6) {
                                                pincode = it
                                                selectedBusinessType = null
                                                showSearchButton = true
                                                viewModel._businessTypes.value = emptyList()
                                                viewModel._businessList.value = emptyList()
                                            }
                                        },
                                        label = {
                                            Text(
                                                LocalizationManager.getString(R.string.enter_pincode),
                                                color = MaterialTheme.colorScheme.onSurface.copy(
                                                    alpha = 0.7f
                                                )
                                            )
                                        },
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp)
                                            .onFocusChanged { focusState ->
                                                if (focusState.isFocused && !isInputActive) {
                                                    isInputActive = true
                                                    resetAll()
                                                } else if (!focusState.isFocused) {
                                                    isInputActive = false
                                                }
                                            },
                                        shape = RoundedCornerShape(16.dp),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                                            unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(
                                                alpha = 0.2f
                                            ),
                                            focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                            unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                                        )
                                    )

                                    // Category Dropdown
                                    if (businessTypes.isNotEmpty()) {
                                        ExposedDropdownMenuBox(
                                            expanded = expanded,
                                            onExpandedChange = { expanded = it },
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            OutlinedTextField(
                                                value = selectedBusinessType
                                                    ?: LocalizationManager.getString(R.string.select_business_category),
                                                onValueChange = {},
                                                readOnly = true,
                                                trailingIcon = {
                                                    ExposedDropdownMenuDefaults.TrailingIcon(
                                                        expanded = expanded
                                                    )
                                                },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .menuAnchor(),
                                                shape = RoundedCornerShape(16.dp),
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                                    unfocusedBorderColor = MaterialTheme.colorScheme.onSurface.copy(
                                                        alpha = 0.2f
                                                    ),
                                                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                                                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                                                )
                                            )

                                            ExposedDropdownMenu(
                                                expanded = expanded,
                                                onDismissRequest = { expanded = false },
                                                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                                            ) {
                                                businessTypes.forEach { businessType ->
                                                    DropdownMenuItem(
                                                        text = { Text(businessType) },
                                                        onClick = {
                                                            selectedBusinessType = businessType
                                                            expanded = false
                                                            viewModel.fetchBusinessesFromPinCodeAndCategory(
                                                                pincode.toInt(),
                                                                businessType
                                                            )
                                                        }
                                                    )
                                                }
                                            }
                                        }
                                    }

                                    // Search Button
                                    if (showSearchButton && businessTypes.isEmpty()) {
                                        Button(
                                            onClick = {
                                                if (pincode.length == 6) {
                                                    viewModel.fetchBusinessTypesFromPinCode(
                                                        pincode.toInt()
                                                    )
                                                    showSearchButton = false
                                                }
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(56.dp),
                                            shape = RoundedCornerShape(16.dp),
                                            enabled = pincode.length == 6,
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = MaterialTheme.colorScheme.primary,
                                                disabledContainerColor = MaterialTheme.colorScheme.onSurface.copy(
                                                    alpha = 0.12f
                                                )
                                            )
                                        ) {
                                            Text(
                                                LocalizationManager.getString(R.string.search_directory),
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Medium
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Loading State
                        if (viewModel.isLoading.value) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillParentMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(48.dp)
                                    )
                                }
                            }
                        }

                        // Error State
                        if (viewModel.operationState.value is UserProfileEvent.Error) {
                            item {
                                Card(
                                    modifier = Modifier.fillMaxWidth(),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.error.copy(
                                            alpha = 0.1f
                                        )
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(16.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Clear,
                                            contentDescription = "Error",
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                        Spacer(modifier = Modifier.width(12.dp))
                                        Text(
                                            text = viewModel.errorMessage.value,
                                            color = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            }
                        }

                        // Business List
                        if (selectedBusinessType != null && !viewModel.isLoading.value) {
                            items(businessList) { business ->
                                BusinessCard(business = business)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard(business: UserData) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            // Business Name - Always show
            Text(
                text = business.userBusinessName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Owner Name - Show if available
            if (!business.userName.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${LocalizationManager.getString(R.string.owner)}: ${business.userName}",
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            // Business Address - Show if available
            if (!business.userBusinessAddress.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = LocalizationManager.getString(R.string.location),
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = business.userBusinessAddress,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Email - Show if available
            if (!business.userBusinessEmail.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Email,
                        contentDescription = "Email",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = business.userBusinessEmail,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Website - Show if available
            if (!business.userBusinessWebsite.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Website",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = business.userBusinessWebsite,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }

            // Description - Show if available
            if (!business.userBusinessDescription.isNullOrBlank()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = business.userBusinessDescription,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }

            // Phone Number and Call Button - Always show (at the bottom)
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        contentDescription = LocalizationManager.getString(R.string.phone_number),
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = business.userBusinessPhone,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                }

                FilledTonalButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_DIAL).apply {
                            data = Uri.parse("tel:${business.userBusinessPhone}")
                        }
                        context.startActivity(intent)
                    },
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                        contentDescription = LocalizationManager.getString(R.string.call),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}
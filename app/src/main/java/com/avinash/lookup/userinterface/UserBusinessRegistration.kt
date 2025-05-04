//
//
//package com.avinash.lookup.userinterface
//
//import androidx.compose.foundation.*
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.avinash.lookup.dataclass.BusinessType
//import com.avinash.lookup.dataclass.UserData
//import com.avinash.lookup.viemodel.UserProfile
//import com.avinash.lookup.viemodel.UserProfileEvent
//import java.util.UUID
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun UserBusinessRegistration(
//    UserProfile: UserProfile,
//    navController: NavController
//) {
//    var userName by remember { mutableStateOf("") }
//    var businessName by remember { mutableStateOf("") }
//    var selectedBusinessType by remember { mutableStateOf("") }
//    var customBusinessType by remember { mutableStateOf("") }
//    var pinCode by remember { mutableStateOf("") }
//    var address by remember { mutableStateOf("") }
//    var phone by remember { mutableStateOf("") }
//    var email by remember { mutableStateOf("") }
//    var website by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//    var isOtherSelected by remember { mutableStateOf(false) }
//    var expanded by remember { mutableStateOf(false) }
//
//    val allBusinessTypes by UserProfile.allBusinessTypes.collectAsState()
//
//    val businessTypes = listOf(
//        "Plumber", "Electrician", "Carpenter", "Painter", "Mechanic",
//        "Gardener", "Driver", "Cook", "Maid", "Tutor", "Doctor",
//        "Lawyer", "Accountant", "Architect", "Interior Designer",
//        "Photographer", "Event Planner", "Hair Stylist", "Makeup Artist",
//        "Fashion Designer", "Tailor", "Boutique", "Gym Trainer",
//        "Yoga Instructor", "Dance Instructor", "Musician", "Singer",
//        "Artist", "Writer", "Blogger", "Other"
//    )
//
//    val operationState = UserProfile.operationState.value
//    val isLoading = UserProfile.isLoading.value
//
//    // Function to reset all fields
//    fun resetFields() {
//        userName = ""
//        businessName = ""
//        selectedBusinessType = ""
//        customBusinessType = ""
//        pinCode = ""
//        address = ""
//        phone = ""
//        email = ""
//        website = ""
//        description = ""
//        isOtherSelected = false
//        expanded = false
//    }
//
//    // Handle operation state changes
//    LaunchedEffect(operationState) {
//        when (operationState) {
//            is UserProfileEvent.Success -> {
//                resetFields()
//            }
//            is UserProfileEvent.Error -> {
//            }
//            else -> {} // Handle other states if needed
//        }
//    }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {
//                    Text(
//                        text = "Business Registration",
//                        fontSize = 28.sp,
//                        fontWeight = FontWeight.Bold,
//                        color = Color(0xFF86B9F5)
//                    )
//                },
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = Color(0xFF1E1E1E)
//                )
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFF121212))
//                .padding(paddingValues)
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .verticalScroll(rememberScrollState())
//                    .padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp),
//                    shape = RoundedCornerShape(24.dp),
//                    colors = CardDefaults.cardColors(
//                        containerColor = Color(0xFF1E1E1E)
//                    ),
//                    elevation = CardDefaults.cardElevation(8.dp)
//                ) {
//                    Column(
//                        modifier = Modifier
//                            .padding(24.dp)
//                            .fillMaxWidth(),
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        SectionHeader(text = "Required Information")
//
//                        // Common text field colors
//                        val textFieldColors = OutlinedTextFieldDefaults.colors(
//                            focusedBorderColor = Color(0xFF86B9F5),
//                            unfocusedBorderColor = Color(0xFF6E97CC).copy(alpha = 0.5f),
//                            focusedLabelColor = Color(0xFF9CCAFF),
//                            unfocusedLabelColor = Color(0xFF6E97CC),
//                            cursorColor = Color(0xFF86B9F5),
//                            focusedTextColor = Color(0xFFE1E1E1),
//                            unfocusedTextColor = Color(0xFFE1E1E1)
//                        )
//
//                        OutlinedTextField(
//                            value = userName,
//                            onValueChange = { userName = it },
//                            label = { Text("Your Name *") },
//                            modifier = StandardTextFieldModifier,
//                            shape = RoundedCornerShape(12.dp),
//                            colors = textFieldColors
//                        )
//
//                OutlinedTextField(
//                    value = businessName,
//                    onValueChange = { businessName = it },
//                    label = { Text("Business Name *") },
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    colors = textFieldColors
//                )
//                        if (!isOtherSelected) {
//                            ExposedDropdownMenuBox(
//                                expanded = expanded,
//                                onExpandedChange = { expanded = !expanded },
//                                modifier = StandardTextFieldModifier
//                            ) {
//                                OutlinedTextField(
//                                    value = selectedBusinessType,
//                                    onValueChange = {},
//                                    readOnly = true,
//                                    label = { Text("Business Type *") },
//                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
//                                    modifier = Modifier
//                                        .fillMaxWidth()
//                                        .menuAnchor(),
//                                    shape = RoundedCornerShape(12.dp),
//                                    colors = textFieldColors
//                                )
//                                ExposedDropdownMenu(
//                                    expanded = expanded,
//                                    onDismissRequest = { expanded = false },
//                                    modifier = Modifier.background(Color(0xFF1E1E1E))
//                                ) {
//                                    allBusinessTypes.forEach { type ->
//                                        DropdownMenuItem(
//                                            text = { Text(type, color = Color(0xFFE1E1E1)) },
//                                            onClick = {
//                                                if (type == "Other") {
//                                                    isOtherSelected = true
//                                                    selectedBusinessType = ""
//                                                } else {
//                                                    selectedBusinessType = type
//                                                }
//                                                expanded = false
//                                            },
//                                            colors = MenuDefaults.itemColors(
//                                                textColor = Color(0xFFE1E1E1)
//                                            )
//                                        )
//                                    }
//                                }
//                            }
//                        } else {
//                            OutlinedTextField(
//                                value = customBusinessType,
//                                onValueChange = { customBusinessType = it },
//                                label = { Text("Custom Business Type *") },
//                                modifier = StandardTextFieldModifier,
//                                shape = RoundedCornerShape(12.dp),
//                                colors = textFieldColors,
//                                trailingIcon = {
//                                    IconButton(onClick = {
//                                        isOtherSelected = false
//                                        customBusinessType = ""
//                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.Close,
//                                            contentDescription = "Clear selection",
//                                            tint = Color(0xFF6E97CC)
//                                        )
//                                    }
//                                }
//                            )
//                        }
//
//                OutlinedTextField(
//                    value = pinCode,
//                    onValueChange = { if (it.length <= 6) pinCode = it },
//                    label = { Text("Pin Code *") },
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Number,
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    colors = textFieldColors
//                )
//
//                OutlinedTextField(
//                    value = phone,
//                    onValueChange = { if (it.length <= 10) phone = it },
//                    label = { Text("Phone Number *") },
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Phone,
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    colors = textFieldColors
//                )
//
//                SectionHeader(
//                    text = "Optional Information",
//                    topPadding = 24.dp
//                )
//
//                OutlinedTextField(
//                    value = address,
//                    onValueChange = { address = it },
//                    label = { Text("Business Address (Optional)") },
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    minLines = 2,
//                    colors = textFieldColors
//                )
//
//                OutlinedTextField(
//                    value = email,
//                    onValueChange = { email = it },
//                    label = { Text("Email Address (Optional)") },
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Email,
//                        imeAction = ImeAction.Next
//                    ),
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    colors = textFieldColors
//                )
//
//                OutlinedTextField(
//                    value = website,
//                    onValueChange = { website = it },
//                    label = { Text("Website (Optional)") },
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    colors = textFieldColors
//                )
//
//                OutlinedTextField(
//                    value = description,
//                    onValueChange = { description = it },
//                    label = { Text("Business Description (Optional)") },
//                    modifier = StandardTextFieldModifier,
//                    shape = RoundedCornerShape(12.dp),
//                    minLines = 3,
//                    colors = textFieldColors
//                )
//
//                Button(
//                    onClick = {
//                        val finalBusinessType = if (isOtherSelected) customBusinessType else selectedBusinessType
//                        val businessType = BusinessType().apply {
//                            when (finalBusinessType) {
//                                "Plumber" -> plumber = businessName
//                                "Electrician" -> electrician = businessName
//                                "Carpenter" -> carpenter = businessName
//                                "Painter" -> painter = businessName
//                                "Mechanic" -> mechanic = businessName
//                                "Gardener" -> gardener = businessName
//                                "Driver" -> driver = businessName
//                                "Cook" -> cook = businessName
//                                "Maid" -> maid = businessName
//                                "Tutor" -> tutor = businessName
//                                "Doctor" -> doctor = businessName
//                                "Lawyer" -> lawyer = businessName
//                                "Accountant" -> accountant = businessName
//                                "Architect" -> architect = businessName
//                                "Interior Designer" -> interiorDesigner = businessName
//                                "Photographer" -> photographer = businessName
//                                "Event Planner" -> eventPlanner = businessName
//                                "Hair Stylist" -> hairStylist = businessName
//                                "Makeup Artist" -> makeupArtist = businessName
//                                "Fashion Designer" -> fashionDesigner = businessName
//                                "Tailor" -> tailor = businessName
//                                "Boutique" -> boutique = businessName
//                                "Gym Trainer" -> gymTrainer = businessName
//                                "Yoga Instructor" -> yogaInstructor = businessName
//                                "Dance Instructor" -> danceInstructor = businessName
//                                "Musician" -> musician = businessName
//                                "Singer" -> singer = businessName
//                                "Artist" -> artist = businessName
//                                "Writer" -> writer = businessName
//                                "Blogger" -> blogger = businessName
//                                else -> other = businessName
//                            }
//                        }
//
//                        val userData = UserData(
//                            userPinCode = pinCode.toInt(),
//                            userId = UUID.randomUUID().toString(),
//                            userName = userName,
//                            userBusinessName = businessName,
//                            userBusinessType = businessType,
//                            userBusinessAddress = address,
//                            userBusinessPhone = phone,
//                            userBusinessEmail = email,
//                            userBusinessWebsite = website,
//                            userBusinessDescription = description
//                        )
//                        UserProfile.addUserPinCodeToFireStore(userData, finalBusinessType)
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(vertical = 24.dp)
//                        .height(56.dp),
//                    shape = RoundedCornerShape(16.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Color(0xFF86B9F5),
//                        contentColor = Color(0xFF1E1E1E)
//                    ),
//                    enabled = !isLoading && userName.isNotBlank() &&
//                            businessName.isNotBlank() &&
//                            (selectedBusinessType.isNotBlank() || customBusinessType.isNotBlank()) &&
//                            pinCode.length == 6 &&
//                            phone.length == 10
//                ) {
//                    if (isLoading) {
//                        CircularProgressIndicator(
//                            modifier = Modifier.size(24.dp),
//                            color = Color(0xFF1E1E1E)
//                        )
//                    } else {
//                        Text(
//                            "Register Business",
//                            fontSize = 18.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//                    }
//                }
//                    }
//                }
//            }
//
//            // Show loading indicator
//            if (isLoading) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color(0x80000000)),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator(
//                        color = Color(0xFF86B9F5)
//                    )
//                }
//            }
//
//            // Show messages
//            when (operationState) {
//                is UserProfileEvent.Success -> {
//                    LaunchedEffect(Unit) {
//
//                    }
//                }
//                is UserProfileEvent.Error -> {
//                    val message = (operationState as UserProfileEvent.Error).message
//                }
//                else -> {}
//            }
//        }
//    }
//}
//
//@Composable
//private fun SectionHeader(
//    text: String,
//    topPadding: Dp = 0.dp
//) {
//    Text(
//        text = text,
//        fontSize = 18.sp,
//        fontWeight = FontWeight.Medium,
//        color = Color(0xFF9CCAFF),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(top = topPadding, bottom = 16.dp)
//    )
//}
//
//private val StandardTextFieldModifier = Modifier
//    .fillMaxWidth()
//    .padding(vertical = 8.dp)


package com.avinash.lookup.userinterface

import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.avinash.lookup.LocalizationManager
import com.avinash.lookup.R
import com.avinash.lookup.dataclass.BusinessType
import com.avinash.lookup.dataclass.UserData
import com.avinash.lookup.findActivity
import com.avinash.lookup.viemodel.UserProfile
import com.avinash.lookup.viemodel.UserProfileEvent
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBusinessRegistration(
    UserProfile: UserProfile,
    navController: NavController
) {
    var userName by remember { mutableStateOf("") }
    var businessName by remember { mutableStateOf("") }
    var selectedBusinessType by remember { mutableStateOf("") }
    var customBusinessType by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isOtherSelected by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val allBusinessTypes by UserProfile.allBusinessTypes.collectAsState()

    val operationState = UserProfile.operationState.value
    val isLoading = UserProfile.isLoading.value

    val context = LocalContext.current
    // Function to reset all fields
    fun resetFields() {
        userName = ""
        businessName = ""
        selectedBusinessType = ""
        customBusinessType = ""
        pinCode = ""
        address = ""
        phone = ""
        email = ""
        website = ""
        description = ""
        isOtherSelected = false
        expanded = false
    }

    // Handle operation state changes
    LaunchedEffect(operationState) {
        when (operationState) {
            is UserProfileEvent.Success -> {
                resetFields()
            }
            is UserProfileEvent.Error -> {
            }
            else -> {} // Handle other states if needed
        }
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.business_registration),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF86B9F5)
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

                                // Update locale
                                LocalizationManager.setLocale(it, newLanguage)

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
                            color = Color(0xFF86B9F5)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1E1E1E)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1E1E1E)
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SectionHeader(text = stringResource(R.string.required_information))

                        // Common text field colors
                        val textFieldColors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF86B9F5),
                            unfocusedBorderColor = Color(0xFF6E97CC).copy(alpha = 0.5f),
                            focusedLabelColor = Color(0xFF9CCAFF),
                            unfocusedLabelColor = Color(0xFF6E97CC),
                            cursorColor = Color(0xFF86B9F5),
                            focusedTextColor = Color(0xFFE1E1E1),
                            unfocusedTextColor = Color(0xFFE1E1E1)
                        )

                        OutlinedTextField(
                            value = userName,
                            onValueChange = { userName = it },
                            label = { Text(stringResource(R.string.your_name)) },
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = businessName,
                            onValueChange = { businessName = it },
                            label = { Text(stringResource(R.string.business_name)) },
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        if (!isOtherSelected) {
                            ExposedDropdownMenuBox(
                                expanded = expanded,
                                onExpandedChange = { expanded = !expanded },
                                modifier = StandardTextFieldModifier
                            ) {
                                OutlinedTextField(
                                    value = selectedBusinessType,
                                    onValueChange = {},
                                    readOnly = true,
                                    label = { Text(stringResource(R.string.business_type)) },
                                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .menuAnchor(),
                                    shape = RoundedCornerShape(12.dp),
                                    colors = textFieldColors
                                )
                                ExposedDropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false },
                                    modifier = Modifier.background(Color(0xFF1E1E1E))
                                ) {
                                    allBusinessTypes.forEach { type ->
                                        DropdownMenuItem(
                                            text = {
                                                val localizedType = getLocalizedBusinessType(type)
                                                Text(localizedType, color = Color(0xFFE1E1E1))
                                            },
                                            onClick = {
                                                if (type == "Other") {
                                                    isOtherSelected = true
                                                    selectedBusinessType = ""
                                                } else {
                                                    selectedBusinessType = type
                                                }
                                                expanded = false
                                            },
                                            colors = MenuDefaults.itemColors(
                                                textColor = Color(0xFFE1E1E1)
                                            )
                                        )
                                    }
                                }
                            }
                        } else {
                            OutlinedTextField(
                                value = customBusinessType,
                                onValueChange = { customBusinessType = it },
                                label = { Text(stringResource(R.string.custom_business_type)) },
                                modifier = StandardTextFieldModifier,
                                shape = RoundedCornerShape(12.dp),
                                colors = textFieldColors,
                                trailingIcon = {
                                    IconButton(onClick = {
                                        isOtherSelected = false
                                        customBusinessType = ""
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = "Clear selection",
                                            tint = Color(0xFF6E97CC)
                                        )
                                    }
                                }
                            )
                        }

                        OutlinedTextField(
                            value = pinCode,
                            onValueChange = { if (it.length <= 6) pinCode = it },
                            label = { Text(stringResource(R.string.pin_code)) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = phone,
                            onValueChange = { if (it.length <= 10) phone = it },
                            label = { Text(stringResource(R.string.phone_number)) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Phone,
                                imeAction = ImeAction.Next
                            ),
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        SectionHeader(
                            text = stringResource(R.string.optional_information),
                            topPadding = 24.dp
                        )

                        OutlinedTextField(
                            value = address,
                            onValueChange = { address = it },
                            label = { Text(stringResource(R.string.business_address)) },
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            minLines = 2,
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(stringResource(R.string.email_address)) },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Next
                            ),
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = website,
                            onValueChange = { website = it },
                            label = { Text(stringResource(R.string.website)) },
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            colors = textFieldColors
                        )

                        OutlinedTextField(
                            value = description,
                            onValueChange = { description = it },
                            label = { Text(stringResource(R.string.business_description)) },
                            modifier = StandardTextFieldModifier,
                            shape = RoundedCornerShape(12.dp),
                            minLines = 3,
                            colors = textFieldColors
                        )

                        Button(
                            onClick = {
                                val finalBusinessType = if (isOtherSelected) customBusinessType else selectedBusinessType
                                val businessType = BusinessType().apply {
                                    when (finalBusinessType) {
                                        "Plumber" -> plumber = businessName
                                        "Electrician" -> electrician = businessName
                                        "Carpenter" -> carpenter = businessName
                                        "Painter" -> painter = businessName
                                        "Mechanic" -> mechanic = businessName
                                        "Gardener" -> gardener = businessName
                                        "Driver" -> driver = businessName
                                        "Cook" -> cook = businessName
                                        "Maid" -> maid = businessName
                                        "Tutor" -> tutor = businessName
                                        "Doctor" -> doctor = businessName
                                        "Lawyer" -> lawyer = businessName
                                        "Accountant" -> accountant = businessName
                                        "Architect" -> architect = businessName
                                        "Interior Designer" -> interiorDesigner = businessName
                                        "Photographer" -> photographer = businessName
                                        "Event Planner" -> eventPlanner = businessName
                                        "Hair Stylist" -> hairStylist = businessName
                                        "Makeup Artist" -> makeupArtist = businessName
                                        "Fashion Designer" -> fashionDesigner = businessName
                                        "Tailor" -> tailor = businessName
                                        "Boutique" -> boutique = businessName
                                        "Gym Trainer" -> gymTrainer = businessName
                                        "Yoga Instructor" -> yogaInstructor = businessName
                                        "Dance Instructor" -> danceInstructor = businessName
                                        "Musician" -> musician = businessName
                                        "Singer" -> singer = businessName
                                        "Artist" -> artist = businessName
                                        "Writer" -> writer = businessName
                                        "Blogger" -> blogger = businessName
                                        else -> other = businessName
                                    }
                                }

                                val userData = UserData(
                                    userPinCode = pinCode.toInt(),
                                    userId = UUID.randomUUID().toString(),
                                    userName = userName,
                                    userBusinessName = businessName,
                                    userBusinessType = businessType,
                                    userBusinessAddress = address,
                                    userBusinessPhone = phone,
                                    userBusinessEmail = email,
                                    userBusinessWebsite = website,
                                    userBusinessDescription = description
                                )
                                UserProfile.addUserPinCodeToFireStore(userData, finalBusinessType)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 24.dp)
                                .height(56.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF86B9F5),
                                contentColor = Color(0xFF1E1E1E)
                            ),
                            enabled = !isLoading && userName.isNotBlank() &&
                                    businessName.isNotBlank() &&
                                    (selectedBusinessType.isNotBlank() || customBusinessType.isNotBlank()) &&
                                    pinCode.length == 6 &&
                                    phone.length == 10
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color(0xFF1E1E1E)
                                )
                            } else {
                                Text(
                                    stringResource(R.string.register_business),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Show loading indicator
            if (isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0x80000000)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = Color(0xFF86B9F5)
                    )
                }
            }

            // Show messages
            when (operationState) {
                is UserProfileEvent.Success -> {
                    LaunchedEffect(Unit) {
                        // Success handling if needed
                    }
                }
                is UserProfileEvent.Error -> {
                    val message = (operationState as UserProfileEvent.Error).message
                    // Error handling if needed
                }
                else -> {}
            }
        }
    }
}

@Composable
private fun SectionHeader(
    text: String,
    topPadding: Dp = 0.dp
) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        color = Color(0xFF9CCAFF),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = topPadding, bottom = 16.dp)
    )
}

@Composable
private fun getLocalizedBusinessType(type: String): String {
    return when (type) {
        "Plumber" -> stringResource(R.string.plumber)
        "Electrician" -> stringResource(R.string.electrician)
        "Carpenter" -> stringResource(R.string.carpenter)
        "Painter" -> stringResource(R.string.painter)
        "Mechanic" -> stringResource(R.string.mechanic)
        "Gardener" -> stringResource(R.string.gardener)
        "Driver" -> stringResource(R.string.driver)
        "Cook" -> stringResource(R.string.cook)
        "Maid" -> stringResource(R.string.maid)
        "Tutor" -> stringResource(R.string.tutor)
        "Doctor" -> stringResource(R.string.doctor)
        "Lawyer" -> stringResource(R.string.lawyer)
        "Accountant" -> stringResource(R.string.accountant)
        "Architect" -> stringResource(R.string.architect)
        "Interior Designer" -> stringResource(R.string.interior_designer)
        "Photographer" -> stringResource(R.string.photographer)
        "Event Planner" -> stringResource(R.string.event_planner)
        "Hair Stylist" -> stringResource(R.string.hair_stylist)
        "Makeup Artist" -> stringResource(R.string.makeup_artist)
        "Fashion Designer" -> stringResource(R.string.fashion_designer)
        "Tailor" -> stringResource(R.string.tailor)
        "Boutique" -> stringResource(R.string.boutique)
        "Gym Trainer" -> stringResource(R.string.gym_trainer)
        "Yoga Instructor" -> stringResource(R.string.yoga_instructor)
        "Dance Instructor" -> stringResource(R.string.dance_instructor)
        "Musician" -> stringResource(R.string.musician)
        "Singer" -> stringResource(R.string.singer)
        "Artist" -> stringResource(R.string.artist)
        "Writer" -> stringResource(R.string.writer)
        "Blogger" -> stringResource(R.string.blogger)
        "Other" -> stringResource(R.string.other)
        else -> type
    }
}

private val StandardTextFieldModifier = Modifier
    .fillMaxWidth()
    .padding(vertical = 8.dp)
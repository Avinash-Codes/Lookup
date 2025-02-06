package com.avinash.lookup.userinterface

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avinash.lookup.dataclass.UserData
import com.avinash.lookup.viemodel.UserProfile
import com.avinash.lookup.viemodel.UserProfileEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusinessSearchScreen(
    viewModel: UserProfile,
    modifier: Modifier = Modifier
) {
    var pincode by remember { mutableStateOf("") }
    var selectedBusinessType by remember { mutableStateOf<String?>(null) }
    var showBusinessList by remember { mutableStateOf(false) }

    val businessTypes by viewModel.businessTypes.collectAsState()
    val businessList by viewModel.businessList.collectAsState()

//    val businessTypes = listOf(
//        "Plumber", "Electrician", "Carpenter", "Painter", "Mechanic",
//        "Gardener", "Driver", "Cook", "Maid", "Tutor", "Doctor",
//        "Lawyer", "Accountant", "Architect", "Interior Designer",
//        "Photographer", "Event Planner", "Hair Stylist", "Makeup Artist",
//        "Fashion Designer", "Tailor", "Boutique", "Gym Trainer",
//        "Yoga Instructor", "Dance Instructor", "Musician", "Singer",
//        "Artist", "Writer", "Blogger"
//    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Search Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Find Local Businesses",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = pincode,
                    onValueChange = {
                        if (it.length <= 6) pincode = it
                        selectedBusinessType = null
                        showBusinessList = false
                    },
                    label = { Text("Enter Pincode") },
                    keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                Button(
                    onClick = {
                        if (pincode.length == 6) {
                            showBusinessList = true
                            selectedBusinessType = null
                        }
                        viewModel.fetchBusinessTypesFromPinCode(pincode.toInt())
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    enabled = pincode.length == 6
                ) {
                    Text("Search")
                }
            }
        }

        // Loading Indicator
        if (viewModel.isLoading.value) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        // Error Message
        if (viewModel.operationState.value is UserProfileEvent.Error) {
            Text(
                text = viewModel.errorMessage.value,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        if (showBusinessList && !viewModel.isLoading.value) {
            if (selectedBusinessType == null) {
                if (businessTypes.isEmpty()) {
                    Text(
                        text = "No businesses available in this pincode",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else {
                    Text(
                        text = "Select Business Type",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(businessTypes) { businessType ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedBusinessType = businessType
                                        viewModel.fetchBusinessesFromPinCodeAndCategory(
                                            pincode.toInt(),
                                            businessType
                                        )
                                    },
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text(
                                        text = businessType.toString(),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "View businesses in this category",
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Log.d("BusinessType", businessType.toString())
                                }
                            }
                        }
                    }
                }
            } else {
                // Show businesses of selected type
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedBusinessType ?: "",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Medium
                        )
                        TextButton(onClick = {
                            selectedBusinessType = null
                        }) {
                            Text("Change")
                        }
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(businessList) { business ->
                            BusinessCard(business = business)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessCard(business: UserData) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = business.userBusinessName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = business.userBusinessAddress)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "📞 ${business.userBusinessPhone}",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
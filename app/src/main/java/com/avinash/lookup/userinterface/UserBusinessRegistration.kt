package com.avinash.lookup.userinterface

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.avinash.lookup.dataclass.BusinessType
import com.avinash.lookup.dataclass.UserData
import com.avinash.lookup.viemodel.UserProfile
import java.util.UUID

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserBusinessRegistration(
    UserProfile: UserProfile
) {
    var userName by remember { mutableStateOf("") }
    var businessName by remember { mutableStateOf("") }
    var selectedBusinessType by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var website by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }

    val businessTypes = listOf(
        "Plumber", "Electrician", "Carpenter", "Painter", "Mechanic",
        "Gardener", "Driver", "Cook", "Maid", "Tutor", "Doctor",
        "Lawyer", "Accountant", "Architect", "Interior Designer",
        "Photographer", "Event Planner", "Hair Stylist", "Makeup Artist",
        "Fashion Designer", "Tailor", "Boutique", "Gym Trainer",
        "Yoga Instructor", "Dance Instructor", "Musician", "Singer",
        "Artist", "Writer", "Blogger"
    )

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
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Business Registration",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Your Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = businessName,
                    onValueChange = { businessName = it },
                    label = { Text("Business Name") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    OutlinedTextField(
                        value = selectedBusinessType,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Business Type") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor(),
                        shape = RoundedCornerShape(12.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        businessTypes.forEach { type ->
                            DropdownMenuItem(
                                text = { Text(type) },
                                onClick = {
                                    selectedBusinessType = type
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = pinCode,
                    onValueChange = { if (it.length <= 6) pinCode = it },
                    label = { Text("Pin Code") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Business Address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 2
                )

                OutlinedTextField(
                    value = phone,
                    onValueChange = { if (it.length <= 10) phone = it },
                    label = { Text("Phone Number") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = website,
                    onValueChange = { website = it },
                    label = { Text("Website (Optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Business Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 3
                )

                Button(
                    onClick = {
                        // Create BusinessType instance based on selection
                        val businessType = BusinessType(
                            plumber = if (selectedBusinessType == "Plumber") businessName else "",
                            electrician = if (selectedBusinessType == "Electrician") businessName else "",
                            blogger = if (selectedBusinessType == "Blogger") businessName else "",
                            carpenter = if (selectedBusinessType == "Carpenter") businessName else "",
                            painter = if (selectedBusinessType == "Painter") businessName else "",
                            mechanic = if (selectedBusinessType == "Mechanic") businessName else "",
                            gardener = if (selectedBusinessType == "Gardener") businessName else "",
                            driver = if (selectedBusinessType == "Driver") businessName else "",
                            cook = if (selectedBusinessType == "Cook") businessName else "",
                            maid = if (selectedBusinessType == "Maid") businessName else "",
                            tutor = if (selectedBusinessType == "Tutor") businessName else "",
                            doctor = if (selectedBusinessType == "Doctor") businessName else "",
                            lawyer = if (selectedBusinessType == "Lawyer") businessName else "",
                            accountant = if (selectedBusinessType == "Accountant") businessName else "",
                            architect = if (selectedBusinessType == "Architect") businessName else "",
                            interiorDesigner = if (selectedBusinessType == "Interior Designer") businessName else "",
                            photographer = if (selectedBusinessType == "Photographer") businessName else "",
                            eventPlanner = if (selectedBusinessType == "Event Planner") businessName else "",
                            hairStylist = if (selectedBusinessType == "Hair Stylist") businessName else "",
                            makeupArtist = if (selectedBusinessType == "Makeup Artist") businessName else "",
                            fashionDesigner = if (selectedBusinessType == "Fashion Designer") businessName else "",
                            tailor = if (selectedBusinessType == "Tailor") businessName else "",
                            boutique = if (selectedBusinessType == "Boutique") businessName else "",
                            gymTrainer = if (selectedBusinessType == "Gym Trainer") businessName else "",
                            yogaInstructor = if (selectedBusinessType == "Yoga Instructor") businessName else "",
                            danceInstructor = if (selectedBusinessType == "Dance Instructor") businessName else "",
                            musician = if (selectedBusinessType == "Musician") businessName else "",
                            singer = if (selectedBusinessType == "Singer") businessName else "",
                            artist = if (selectedBusinessType == "Artist") businessName else "",
                            writer = if (selectedBusinessType == "Writer") businessName else "",
                        )

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
                        UserProfile.addUserPinCodeToFireStore(userData, selectedBusinessType)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        "Register Business",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
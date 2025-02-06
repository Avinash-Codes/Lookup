package com.avinash.lookup.viemodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avinash.lookup.dataclass.UserData
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

sealed class UserProfileEvent {
    data class Success(val message: String): UserProfileEvent()
    data class Error(val message: String): UserProfileEvent()
    object Loading: UserProfileEvent()
    object Idle: UserProfileEvent()
}

class UserProfile: ViewModel() {
    private val pincodeCollectionReference = Firebase.firestore.collection("pincodes")

    private val _operationState = mutableStateOf<UserProfileEvent>(UserProfileEvent.Idle)
    val operationState: State<UserProfileEvent> = _operationState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _successMessage = mutableStateOf("")
    val successMessage: State<String> = _successMessage

    private val _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    private val _businessTypes = MutableStateFlow<List<String>>(emptyList())
    val businessTypes: StateFlow<List<String>> = _businessTypes

    private val _businessList = MutableStateFlow<List<UserData>>(emptyList())
    val businessList: StateFlow<List<UserData>> = _businessList

    fun addUserPinCodeToFireStore(userProfile: UserData, businessCategory: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _operationState.value = UserProfileEvent.Loading

                // Check if user exists in any pincode and business category
                val allPincodesSnapshot = pincodeCollectionReference.get().await()
                var userExists = false

                for (pincodeDoc in allPincodesSnapshot) {
                    val businessCategories = pincodeDoc.reference.collection("businesses").get().await()
                    for (businessCategory in businessCategories) {
                        val userDoc = businessCategory.reference.collection("providers")
                            .document(userProfile.userId).get().await()
                        if (userDoc.exists()) {
                            userExists = true
                            break
                        }
                    }
                    if (userExists) break
                }

                if (userExists) {
                    _operationState.value = UserProfileEvent.Error("User already exists in another location")
                    return@launch
                }

                // Get reference to the pincode document
                val pincodeDocRef = pincodeCollectionReference.document(userProfile.userPinCode.toString())

                // Create or update pincode document
                val pincodeData = hashMapOf(
                    "pincode" to userProfile.userPinCode,
                    "updatedAt" to System.currentTimeMillis()
                )
                pincodeDocRef.set(pincodeData).await()

                // Add user to the appropriate business category
                val businessCategoryRef = pincodeDocRef
                    .collection("businesses")
                    .document(businessCategory)

                // Create or update business category document
                val businessCategoryData = hashMapOf(
                    "category" to businessCategory,
                    "updatedAt" to System.currentTimeMillis()
                )
                businessCategoryRef.set(businessCategoryData).await()

                // Add user details to providers collection under the business category
                val providerRef = businessCategoryRef.collection("providers")
                    .document(userProfile.userId)
                providerRef.set(userProfile).await()

                _operationState.value = UserProfileEvent.Success("Profile added successfully")
                _successMessage.value = "Profile added successfully"

            } catch (e: Exception) {
                _operationState.value = UserProfileEvent.Error(e.message ?: "An unknown error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchBusinessTypesFromPinCode(pinCode: Int) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _operationState.value = UserProfileEvent.Loading
                _businessTypes.value = emptyList()
                _businessList.value = emptyList()

                val pincodeDocRef = pincodeCollectionReference.document(pinCode.toString())
                val pincodeDoc = pincodeDocRef.get().await()

                if (!pincodeDoc.exists()) {
                    _operationState.value = UserProfileEvent.Error("No businesses found for this pincode")
                    return@launch
                }

                val businessCategories = pincodeDocRef.collection("businesses").get().await()

                val businessTypesList = mutableListOf<String>()
                for (businessCategory in businessCategories) {
                    val providersSnapshot = businessCategory.reference.collection("providers").get().await()
                    if (providersSnapshot.documents.isNotEmpty()) {
                        businessTypesList.add(businessCategory.id)
                    }
                }

                if (businessTypesList.isEmpty()) {
                    _operationState.value = UserProfileEvent.Error("No businesses available in this pincode")
                } else {
                    _businessTypes.value = businessTypesList
                    _operationState.value = UserProfileEvent.Success("Business types fetched successfully")
                }

            } catch (e: Exception) {
                _operationState.value = UserProfileEvent.Error(e.message ?: "An unknown error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchBusinessesFromPinCodeAndCategory(pinCode: Int, businessCategory: String) {
        Log.d("UserProfile", "Fetching businesses for pincode: $pinCode and category: $businessCategory")
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _operationState.value = UserProfileEvent.Loading
                _businessList.value = emptyList()

                val pincodeDocRef = pincodeCollectionReference.document(pinCode.toString())
                val businessCategoryRef = pincodeDocRef
                    .collection("businesses")
                    .document(businessCategory)
                    .collection("providers")
                    .get().await()
                Log.d("UserProfile", "Fetched ${businessCategoryRef.documents.size} documents from providers collection")

                val businessList = businessCategoryRef.documents.mapNotNull { it.toObject(UserData::class.java) }
                Log.d("UserProfile", "Mapped documents to businessList with size: ${businessList.size}")

                if (businessList.isEmpty()) {
                    Log.d("UserProfile", "No businesses found in this category")
                    _operationState.value = UserProfileEvent.Error("No businesses found in this category")
                } else {
                    _businessList.value = businessList
                    Log.d("UserProfile", "Businesses fetched successfully with size: ${businessList.size}")
                    _operationState.value = UserProfileEvent.Success("Businesses fetched successfully")
                }

            } catch (e: Exception) {
                Log.e("UserProfile", "Error fetching businesses: ${e.message}", e)
                _operationState.value = UserProfileEvent.Error(e.message ?: "An unknown error occurred")
            } finally {
                _isLoading.value = false
            }
        }
    }

}
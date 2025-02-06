package com.avinash.lookup.dataclass

data class UserData(
    val userPinCode: Int,
    val userId: String,
    val userName: String,
    val userBusinessName: String,
    val userBusinessType: BusinessType,
    val userBusinessAddress: String,
    val userBusinessPhone: String,
    val userBusinessEmail: String,
    val userBusinessWebsite: String,
    val userBusinessDescription: String,
){
    constructor(): this(0, "", "", "", BusinessType(), "", "", "", "", "")
}
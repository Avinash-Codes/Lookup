package com.avinash.lookup.dataclass

data class BusinessType(
    var plumber: String = "",
    var electrician: String = "",
    var carpenter: String = "",
    var painter: String = "",
    var mechanic: String = "",
    var gardener: String = "",
    var driver: String = "",
    var cook: String = "",
    var maid: String = "",
    var tutor: String = "",
    var doctor: String = "",
    var lawyer: String = "",
    var accountant: String = "",
    var architect: String = "",
    var interiorDesigner: String = "",
    var photographer: String = "",
    var eventPlanner: String = "",
    var hairStylist: String = "",
    var makeupArtist: String = "",
    var fashionDesigner: String = "",
    var tailor: String = "",
    var boutique: String = "",
    var gymTrainer: String = "",
    var yogaInstructor: String = "",
    var danceInstructor: String = "",
    var musician: String = "",
    var singer: String = "",
    var artist: String = "",
    var writer: String = "",
    var blogger: String = "",
    var other: String = ""
)
{
    constructor(): this("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
}

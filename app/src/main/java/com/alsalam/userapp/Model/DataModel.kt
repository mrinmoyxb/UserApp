package com.alsalam.userapp.Model


// SIGN IN
data class SignUpUser(
    val name: String,
    val studentID: String,
    val email: String,
    val password: String
){
    constructor(): this("", "", "", "")
}

data class StudentInfo(
    val studentName: String,
    val studentId: String,
    val rollNo: String,
    val dateOfBirth: String,
    // additional
    var fatherName: String,
    var motherName: String,
    var village: String,
    var postOffice: String,
    var policeStation: String,
    var district: String,
    var grade: String,
    var pin: String,
    var mobileNo: String,
    var admissionDate: String,
    var admissionFees: String,
    var monthlyFees: String,
    var imageUrl: String? = null,

    ){
    constructor() : this("", "", "","", "", "", "", "", "", "", "", "", "", "", "", "", "")
}

data class PDFDataModel(
    val fileName: String,
    val uploadedDate: Long,
    val url: String? = null
){
    constructor(): this("", 0)
}

data class ResultPDFDataModel(
    val studentId: String,
    val uploadedDate: Long,
    val url: String? = null
){
    constructor(): this("", 0)
}


// PAYMENTS ----------------------------------------------------------------------------------------//
enum class PaymentTypes{
    AdmissionFees,
    TuitionFees,
    HostelFees,
    OtherFees,
    LateFees,
    DefaultFees
}

data class StudentFee(
    val studentName: String,
    val studentRollNo: String,
    val studentPaymentFor: PaymentTypes,
    val studentPaymentAmount: Double,
    val date: Long,
    val studentFeesPaid: Boolean,
    val dateSetByAdmin: String
){
    constructor(): this("","", PaymentTypes.DefaultFees, 0.0, 0, false, "")
}

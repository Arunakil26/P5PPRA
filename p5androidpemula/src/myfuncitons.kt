fun main() {
//    Kelas()
    Urutan("A")
    Urutan("B")
    Urutan("C")
    Urutan("D")
    Urutan("E")
    Namakelas()
}

//fun Kelas(){
//    val urutan = listOf("XIA","XIB","XIC","XID","XIE")

//    println(urutan)

//}

fun Namakelas(){
    val nama = mutableMapOf(
        "XIA" to "Hely",
        "XIB" to "Estre",
        "XIC" to "Croxa",
        "XID" to "D'extri",
        "XIE" to "Xentra"
    )
    println(nama)
}

fun Urutan(fname: String){
    println("XI" + fname)
}


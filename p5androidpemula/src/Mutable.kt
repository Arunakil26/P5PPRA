fun main() {
    val Barang = mutableListOf ("Kamera","Laptop","Handphone")
    var Tas = setOf("Buku","Pena")
    Barang.addAll (2, listOf("Tablet","kabel"))
    Barang[1] = "Tripod"
    Barang -= (listOf("Kamera","Laptop"))
    Tas += (listOf("Buku"))
    Barang.removeAt(0)

    println(Barang)
    print(Tas)

}
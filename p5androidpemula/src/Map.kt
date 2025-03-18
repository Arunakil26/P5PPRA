fun main() {
 //   val ibuNegara = mapOf( //immutable
    val ibuNegara = mutableMapOf(
        "Indonesia" to "Jakarta",
        "Malaysia" to "Kuala Lumpur",
        "Singapura" to "Singapura"
    )// Membuat imutable Map
//    println(ibuNegara)

//    ibuNegara["Cina"] = "Beijin" //Menambahkan
    ibuNegara["Indonesia"] = "Batam" //Update atau diganti
    println(ibuNegara)// Memanggil element pada indeks yang di pilih
}
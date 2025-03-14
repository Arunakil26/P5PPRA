fun main(){
 kucing()
    ras()
}

fun kucing(){
    var bloodtype: Char = 'A'
    println("Bloodtype = $bloodtype")
}

fun ras(){
    var race = mutableMapOf(
        "Silly" to "Orange",
        "Cute" to "British Short Hair",
        "Dumb looking" to "Caracal"
    )
    println("Race = ${race["Silly"]}")

}
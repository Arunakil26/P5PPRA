fun main(){
    abil()
    hours()
    genre( )
}

fun abil(){
    var status = "Blek"
    println(status)
}

fun hours(){
    var playtime = listOf(
        "1K", "2K", "3K"
    )  
    println(playtime)
}

fun genre(){
    var isgood = mapOf(
        "PVP" to "Player-Versus-Player"
    )
    println(isgood["PVP"])
}
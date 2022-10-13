import controllers.BasureroController
import kotlin.system.measureTimeMillis

fun main() {
    val basurero = BasureroController
    measureTimeMillis {
        basurero.loadData()

        basurero.processData()

        basurero.saveData()
    }.also{
        println("Tiempo de ejecución: ${it}.ms")
    }
}
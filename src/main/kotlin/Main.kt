import controllers.BasureroController
import kotlin.system.measureTimeMillis

suspend fun main() {
    measureTimeMillis {
        BasureroController.loadData()

        BasureroController.saveData()
    }.also{
        println("Tiempo de ejecución: ${it}.ms")
    }
}
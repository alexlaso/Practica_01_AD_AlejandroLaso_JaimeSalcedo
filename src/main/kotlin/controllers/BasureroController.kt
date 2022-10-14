package controllers

import models.Contenedor
import models.Residuo
import models.TipoContenedor
import models.loadFromCsvFile
import mu.KotlinLogging
import java.io.File
import kotlin.math.roundToInt
import kotlin.system.exitProcess

private val logger =KotlinLogging.logger{}

object BasureroController {
    val contenedores= Contenedor.loadFromCsvFile(File("data${File.separator}contenedores_varios.csv"))
    val residuos =Residuo.loadFromCsvFile(File("data${File.separator}modelo_residuos_2021.csv"))


    fun loadData() {

            //logger.debug{"Cargando..."}

            println("Datos de los contenedores cargados: ${contenedores.size}")
        //contenedores.forEach { println(it.toString())} // Metodo para que se visualice por linea y no a porron
            println("Datos de los residuos cargados: ${residuos.size}")
            println("El proceso se ha ejecutado sin errores")
}


    fun processData(){
        println("\n\t Cantidad de contenedores de cada tipo agrupado por distrito\n")
        val organicos = contenedores.filter { it.tipo==TipoContenedor.ORGANICA }
        val resto = contenedores.filter { it.tipo==TipoContenedor.RESTO}
        val envases = contenedores.filter { it.tipo==TipoContenedor.ENVASES}
        val vidrio = contenedores.filter { it.tipo==TipoContenedor.VIDRIO}
        val papelcarton = contenedores.filter { it.tipo==TipoContenedor.PAPEL_Y_CARTON}

        println("Orgánicos \uF0E0 ${organicos.groupingBy {it.distrito}.eachCount()}")
        println("Resto \uF0E0 ${resto.groupingBy{it.distrito}.eachCount()}")
        println("Envases \uF0E0 ${envases.groupingBy {it.distrito}.eachCount()}")
        println("Vidrio \uF0E0 ${vidrio.groupingBy {it.distrito}.eachCount()}")
        println("Papel y Cartón \uF0E0 ${papelcarton.groupingBy {it.distrito}.eachCount()}")

        println("\n \t Media de contenedores de cada tipo por distrito \n")
        println("Orgánicos redondeado \uF0E0 ${organicos.groupBy{ it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Resto redondeado \uF0E0 ${resto.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Envases redondeado \uF0E0 ${envases.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Vidrio por distrito redondeado \uF0E0 ${vidrio.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Papel y cartón redondeado \uF0E0 ${papelcarton.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")

        println("")
    }

    fun saveData(){
        logger.debug { "Guardando datos..." }

        val informeJson= logger.debug { "Guardando JSON" }
    }

}
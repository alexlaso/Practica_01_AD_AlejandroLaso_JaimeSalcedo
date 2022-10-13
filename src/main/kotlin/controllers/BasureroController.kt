package controllers

import kotlinx.coroutines.*
import models.Contenedor
import models.Residuo
import models.TipoContenedor
import models.loadFromCsvFile
import mu.KotlinLogging
import java.io.File
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
        cantidadTipoDistrito()
    }

    fun saveData(){
        logger.debug { "Guardando datos..." }

        val informeJson= logger.debug { "Guardando JSON" }
    }

    fun cantidadTipoDistrito(){
        println("Agrupado por distrito")
        val organicosporDistrito = contenedores.filter { it.tipo==TipoContenedor.ORGANICA }
        println("Orgánicos por distrito \uF0E0 ${organicosporDistrito.groupingBy {it.distrito}.eachCount()}")
        val restoporDistrito = contenedores.filter { it.tipo==TipoContenedor.RESTO}
        println("Resto por distrito \uF0E0 ${restoporDistrito.groupingBy {it.distrito}.eachCount()}")
        val envasesporDistrito = contenedores.filter { it.tipo==TipoContenedor.ENVASES}
        println("Envases por distrito \uF0E0 ${envasesporDistrito.groupingBy {it.distrito}.eachCount()}")
        val vidrioporDistrito = contenedores.filter { it.tipo==TipoContenedor.VIDRIO}
        println("Vidrio por distrito \uF0E0 ${vidrioporDistrito.groupingBy {it.distrito}.eachCount()}")
        val papelcartonporDistrito = contenedores.filter { it.tipo==TipoContenedor.PAPEL_Y_CARTON}
        println("Papel y Cartón por distrito \uF0E0 ${papelcartonporDistrito.groupingBy {it.distrito}.eachCount()}")

        println("Media de contenedores organicos por distrito redondeado \uF0E0 ${organicosporDistrito.groupBy{ it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de restos por distrito redondeado \uF0E0 ${restoporDistrito.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de envases por distrito redondeado \uF0E0 ${envasesporDistrito.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de vidrio por distrito redondeado \uF0E0 ${vidrioporDistrito.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de papel y cartón por distrito redondeado \uF0E0 ${papelcartonporDistrito.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
    }
}
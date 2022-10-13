package controllers

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
        val organicos = contenedores.filter { it.tipo==TipoContenedor.ORGANICA }
        println("Orgánicos por distrito \uF0E0 ${organicos.groupingBy {it.distrito}.eachCount()}")
        val resto = contenedores.filter { it.tipo==TipoContenedor.RESTO}
        println("Resto por distrito \uF0E0 ${resto.groupingBy {it.distrito}.eachCount()}")
        val envases = contenedores.filter { it.tipo==TipoContenedor.ENVASES}
        println("Envases por distrito \uF0E0 ${envases.groupingBy {it.distrito}.eachCount()}")
        val vidrio = contenedores.filter { it.tipo==TipoContenedor.VIDRIO}
        println("Vidrio por distrito \uF0E0 ${vidrio.groupingBy {it.distrito}.eachCount()}")
        val papelcarton = contenedores.filter { it.tipo==TipoContenedor.PAPEL_Y_CARTON}
        println("Papel y Cartón por distrito \uF0E0 ${papelcarton.groupingBy {it.distrito}.eachCount()}")

        println("Media de contenedores organicos por distrito redondeado \uF0E0 ${organicos.groupBy{ it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de restos por distrito redondeado \uF0E0 ${resto.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de envases por distrito redondeado \uF0E0 ${envases.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de vidrio por distrito redondeado \uF0E0 ${vidrio.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Media de contenedores de papel y cartón por distrito redondeado \uF0E0 ${papelcarton.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
    }
}
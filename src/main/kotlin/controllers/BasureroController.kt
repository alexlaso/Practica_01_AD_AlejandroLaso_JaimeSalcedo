package controllers

import models.*
import mu.KotlinLogging
import java.io.File
import java.lang.Double.sum
import kotlin.math.roundToInt
import kotlin.collections.List as List1

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


    fun processData() {
        println("\n\t Cantidad de contenedores de cada tipo agrupado por distrito\n")
        // datos de los contenedores de cada tipo
        val contendeorOrganico = contenedores.filter { it.tipo==TipoContenedor.ORGANICA }
        val contenedorResto = contenedores.filter { it.tipo==TipoContenedor.RESTO}
        val contenedorEnvases = contenedores.filter { it.tipo==TipoContenedor.ENVASES}
        val contenedorVidrio = contenedores.filter { it.tipo==TipoContenedor.VIDRIO}
        val contenedorPapelCarton = contenedores.filter { it.tipo==TipoContenedor.PAPEL_Y_CARTON}

        //datos de los residuos de cada tipo

        val residuosResto = residuos.filter {it.tipo == TipoResiduo.RESTO}
        val residuosEnvases = residuos.filter { it.tipo == TipoResiduo.ENVASES }
        val residuosVidrio = residuos.filter { it.tipo == TipoResiduo.VIDRIO }
        val residuosOrganica = residuos.filter { it.tipo == TipoResiduo.ORGANICA }
        val residuosPapelCarton = residuos.filter { it.tipo == TipoResiduo.PAPEL_Y_CARTON }
        val residuosPuntosLimpios = residuos.filter { it.tipo == TipoResiduo.PUNTOS_LIMPIOS }
        val residuosCartonComercial = residuos.filter { it.tipo == TipoResiduo.CARTON_COMERCIAL }
        val residuosVidrioComercial = residuos.filter { it.tipo == TipoResiduo.VIDRIO_COMERCIAL }
        val residuosPilas = residuos.filter { it.tipo == TipoResiduo.PILAS }
        val residuosAnimalesMuertos = residuos.filter { it.tipo == TipoResiduo.ANIMALES_MUERTOS }
        val residuosRCD = residuos.filter { it.tipo == TipoResiduo.RCD }
        val residuosRopaUsada = residuos.filter { it.tipo == TipoResiduo.CONTENEDORES_DE_ROPA_USADA }
        val residuosUnknown = residuos.filter { it.tipo == TipoResiduo.UNKNOWN }

        println("Orgánicos \uF0E0 ${contendeorOrganico.groupingBy {it.distrito}.eachCount()}")
        println("Resto \uF0E0 ${contenedorResto.groupingBy{it.distrito}.eachCount()}")
        println("Envases \uF0E0 ${contenedorEnvases.groupingBy {it.distrito}.eachCount()}")
        println("Vidrio \uF0E0 ${contenedorVidrio.groupingBy {it.distrito}.eachCount()}")
        println("Papel y Cartón \uF0E0 ${contenedorPapelCarton.groupingBy {it.distrito}.eachCount()}")

        println("\n \t Media de contenedores de cada tipo por distrito \n")

        println("Orgánicos redondeado \uF0E0 ${contendeorOrganico.groupBy{ it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Resto redondeado \uF0E0 ${contenedorResto.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Envases redondeado \uF0E0 ${contenedorEnvases.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Vidrio por distrito redondeado \uF0E0 ${contenedorVidrio.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Papel y cartón redondeado \uF0E0 ${contenedorPapelCarton.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")
        println("Papel y cartón redondeado \uF0E0 ${contenedorPapelCarton.groupBy { it.distrito }.map {it.value.size}.average().roundToInt()}")

        println("\n \t Media de toneladas de basura de cada tipo por distrito \n")

        println("Resto \uF0E0 ${residuosResto.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}}}")
        println("Envases \uF0E0 ${residuosEnvases.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Vidrio \uF0E0 ${residuosVidrio.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Organica \uF0E0 ${residuosOrganica.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Papel y Cartón \uF0E0 ${residuosPapelCarton.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Puntos Limpios \uF0E0 ${residuosPuntosLimpios.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Cartón comercial \uF0E0 ${residuosCartonComercial.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Vidrio Comercial \uF0E0 ${residuosVidrioComercial.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Pilas \uF0E0 ${residuosPilas.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Animales Muertos \uF0E0 ${residuosAnimalesMuertos.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("RCD \uF0E0 ${residuosRCD.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Ropa usada \uF0E0 ${residuosRopaUsada.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")
        println("Unknown \uF0E0 ${residuosUnknown.groupBy{it.distrito}.map{it.value.sumOf{it.toneladas}.roundToInt()}}")

        println("\n \t Máximo, mínimo, media y desviación de toneladas anuales recogidas por cada tipo de basura agrupadas por distrito\n")

        println("\n \t Máximo \n")

        println("Resto \uF0E0 ${residuosResto.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Envases \uF0E0 ${residuosEnvases.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Vidrio \uF0E0 ${residuosVidrio.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Orgánica \uF0E0 ${residuosOrganica.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Papel y Cartón \uF0E0 ${residuosPapelCarton.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Puntos limpios \uF0E0 ${residuosPuntosLimpios.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Cartón comercial \uF0E0 ${residuosCartonComercial.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Vidrio comercial \uF0E0 ${residuosVidrioComercial.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Pilas \uF0E0 ${residuosPilas.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Animales muertos \uF0E0 ${residuosAnimalesMuertos.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("RCD \uF0E0 ${residuosRCD.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Ropa usada \uF0E0 ${residuosRopaUsada.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Unknown \uF0E0 ${residuosUnknown.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")

        println("\n \t Mínimo \n")

        println("Resto \uF0E0 ${residuosResto.groupBy{it.distrito}.map{;it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Envases \uF0E0 ${residuosEnvases.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Vidrio \uF0E0 ${residuosVidrio.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Orgánica \uF0E0 ${residuosOrganica.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Papel y Cartón \uF0E0 ${residuosPapelCarton.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Puntos limpios \uF0E0 ${residuosPuntosLimpios.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Cartón comercial \uF0E0 ${residuosCartonComercial.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Vidrio comercial \uF0E0 ${residuosVidrioComercial.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Pilas \uF0E0 ${residuosPilas.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Animales muertos \uF0E0 ${residuosAnimalesMuertos.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("RCD \uF0E0 ${residuosRCD.groupBy{it.distrito}.map{ it.value.maxOf{it.toneladas}.roundToInt()}}")
        println("Ropa usada \uF0E0 ${residuosRopaUsada.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")
        println("Unknown \uF0E0 ${residuosUnknown.groupBy{it.distrito}.map{ it.value.minOf{it.toneladas}.roundToInt()}}")

        println("\n \t Medias \n")

        println("Resto \uF0E0 ${residuosResto.groupBy{it.mes;it.distrito}.map{it.key}}")

    }

    fun saveData(){
        logger.debug { "Guardando datos..." }

        val informeJson= logger.debug { "Guardando JSON" }
    }


}
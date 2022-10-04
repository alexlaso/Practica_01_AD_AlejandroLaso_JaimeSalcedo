package controllers

import kotlinx.coroutines.*
import models.Contenedor
import models.Residuo
import models.loadFromCsvFile
import mu.KotlinLogging
import java.io.File
import kotlin.system.exitProcess

private val logger =KotlinLogging.logger{}

object BasureroController {
    private lateinit var contenedores: List<Contenedor>
    private lateinit var residuos: List<Residuo>

    suspend fun loadData() = coroutineScope{
        logger.debug{"Cargando..."}
        try {
            val myScope = CoroutineScope(Dispatchers.IO)

            val listaCSVContenedorAsync = myScope.async {Contenedor.loadFromCsvFile(File("data/contenedores_varios.csv"))}
            val listaCSVResiduoAsync = myScope.async {Residuo.loadFromCsvFile(File("data/modelo_residuos_2021.csv"))}

            val listaCSVContenedor = listaCSVContenedorAsync.await()
            val listaCSVResiduo = listaCSVResiduoAsync.await()

            contenedores = listaCSVContenedor.distinctBy {it.id}.sortedBy {it.id}
            residuos = listaCSVResiduo

            logger.debug { "Datos de los contenedores cargados: ${listaCSVContenedor.size}"}
            logger.debug { "Datos de los residuos cargados: ${listaCSVResiduo.size}"}
        }catch (e: Exception) {
            System.err.println("Error: ${e.message}")
            exitProcess(1)
        }
        logger.debug { "El proceso se ha ejecutado sin errores" }
    }

    suspend fun saveData() = coroutineScope {
        logger.debug { "Guardando datos..." }
        val myScope = CoroutineScope(Dispatchers.IO)

        val informeJson=myScope.launch {
            logger.debug { "Guardando JSON" }

        }
    }
}
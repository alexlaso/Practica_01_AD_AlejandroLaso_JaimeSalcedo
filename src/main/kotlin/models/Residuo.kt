package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import java.io.File
import java.io.FileNotFoundException
import java.util.ResourceBundle

private val logger = KotlinLogging.logger {}

@Serializable
@SerialName("residuo")

data class Residuo(
    val año: Int,
    val mes: String,
    val lote: Int,
    val residuo: String,
    val distrito: Int,
    val nombre_distrito: String,
    val toneladas: Double
){
    companion object
}

fun Residuo.Companion.loadFromCsvFile(csvFile: File): List<Residuo>{
    logger.debug{"Leyendo datos de: ${csvFile.absolutePath}"}
    if (!csvFile.exists()){
        logger.error {"File not found: ${csvFile.absolutePath}"}
        throw FileNotFoundException("File not found: ${csvFile.absolutePath}")
    }
    return csvFile.readLines().drop(1)
        .map{ line -> line.split(";")}
        .map{
                fields ->
            Residuo(
                año = fields[0].toInt(),
                mes = fields[1],
                lote = fields[2].toInt(),
                residuo = fields[3],
                distrito = fields[4].toInt(),
                nombre_distrito = fields[5],
                toneladas = fields[6].toDouble()
            )
        }

}
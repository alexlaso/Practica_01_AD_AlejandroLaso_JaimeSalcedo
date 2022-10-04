package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import java.io.File
import java.io.FileNotFoundException

private val logger = KotlinLogging.logger {}

@Serializable
@SerialName("contenedor")
data class Contenedor(
    val id: Int,
    val tipo: String,
    val modelo:String,
    val descripcion_modelo: String,
    val cantidad: Int,
    val lote: Int,
    val distrito: String,
    val barrio: Int,
    val tipo_via: String,
    val nombre_via: String,
    val numero_via: Int,
    val coordenada_x: Double,
    val coordenada_y: Double,
    val tag: String
){
    companion object
}

fun Contenedor.Companion.loadFromCsvFile(csvFile: File): List<Contenedor>{
    logger.debug{"Leyendo datos de: ${csvFile.absolutePath}"}
    if (!csvFile.exists()){
        logger.error {"File not found: ${csvFile.absolutePath}"}
        throw FileNotFoundException("File not found: ${csvFile.absolutePath}")
    }
    return csvFile.readLines().drop(1)
        .map{ line -> line.split(";")}
        .map{
            fields ->
            Contenedor(
                id = fields[0].toInt(),
                tipo = fields[1],
                modelo = fields[2],
                descripcion_modelo = fields[3],
                cantidad = fields[4].toInt(),
                lote = fields[5].toInt(),
                distrito = fields[6],
                barrio = fields[7].toInt(),
                tipo_via = fields[8],
                nombre_via = fields[9],
                numero_via = fields[10].toInt(),
                coordenada_x = fields[11].toDouble(),
                coordenada_y = fields[12].toDouble(),
                tag = fields[15]
            )
    }

}
enum class Tipo(){
    ORGANICA, PAPELCARTON
}
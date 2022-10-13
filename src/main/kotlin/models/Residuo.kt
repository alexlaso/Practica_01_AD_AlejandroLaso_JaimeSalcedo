package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import mu.KotlinLogging
import java.io.File
import java.io.FileNotFoundException

private val logger = KotlinLogging.logger {}

@Serializable
@SerialName("residuo")

data class Residuo(
    val ano: Int,
    val mes: String,
    val lote: Int,
    val residuo: TipoResiduo,
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
                ano = fields[0].toInt(),
                mes = fields[1],
                lote = fields[2].toInt(),
                residuo = identificarResiduo(fields[3]),
                distrito = fields[4].toInt(),
                nombre_distrito = fields[5],
                toneladas = reemplazo(fields[6])
            )
        }

}

fun reemplazo(toneladas: String):Double{
    val toneladasDos : String = toneladas.replace(",",".")
    return toneladasDos.toDouble()
}

fun identificarResiduo(c:String):TipoResiduo{
    return when(c){
        "RESTO" -> TipoResiduo.RESTO
        "ENVASES" -> TipoResiduo.ENVASES
        "VIDRIO" -> TipoResiduo.VIDRIO
        "VIDRIO COMERCIAL" -> TipoResiduo.VIDRIO_COMERCIAL
        "ORGANICA" -> TipoResiduo.ORGANICA
        "PAPEL-CARTON" -> TipoResiduo.PAPEL_Y_CARTON
        "CARTON COMERCIAL" -> TipoResiduo.CARTON_COMERCIAL
        "RCD" -> TipoResiduo.RCD
        "PUNTOS LIMPIOS" -> TipoResiduo.PUNTOS_LIMPIOS
        "CONTENEDORES DE ROPA" -> TipoResiduo.CONTENEDORES_DE_ROPA_USADA
        "PILAS" -> TipoResiduo.PILAS
        "ANIMALES MUERTOS" -> TipoResiduo.ANIMALES_MUERTOS
        else -> TipoResiduo.UNKNOWN
    }
}
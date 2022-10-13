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
    val tipo: TipoContenedor,
    val modelo:String,
    val descripcion_modelo: String,
    val cantidad: Int,
    val lote: Int,
    val distrito: String,
    //val barrio: String,
    val tipo_via: String,
    val nombre_via: String,
    val numero_via: String,
    val coordenada_x: String,
    val coordenada_y: String,
    val tag: String


){
    companion object

    override

    override fun toString(): String {
        return "\nContenedor(id=$id, tipo=$tipo, modelo='$modelo', descripcion_modelo='$descripcion_modelo', cantidad=$cantidad, lote=$lote, distrito='$distrito', tipo_via='$tipo_via', nombre_via='$nombre_via', numero_via='$numero_via', coordenada_x='$coordenada_x', coordenada_y='$coordenada_y', tag='$tag')"
    }
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
                tipo = identificarContendedor(fields[1]),
                modelo = fields[2],
                descripcion_modelo = fields[3],
                cantidad = fields[4].toInt(),
                lote = fields[5].toInt(),
                distrito = fields[6],
                //barrio = fields[7],
                tipo_via = fields[8],
                nombre_via = fields[9],
                numero_via = fields[10],
                coordenada_x = fields[11],
                coordenada_y = fields[12],
                tag = fields[15]
            )
    }

}


fun identificarContendedor(c:String): TipoContenedor{
    return when (c){
        "ORGANICA" -> TipoContenedor.ORGANICA
        "RESTO" -> TipoContenedor.RESTO
        "ENVASES" -> TipoContenedor.ENVASES
        "VIDRIO" -> TipoContenedor.VIDRIO
        "PAPEL-CARTON" -> TipoContenedor.PAPEL_Y_CARTON
        else -> TipoContenedor.UNKNOWN
    }

}
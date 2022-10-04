package models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import serializers.LocalDateTimeSerializer
import serializers.UUIDSerializer
import java.io.File
import java.time.LocalDateTime
import java.util.*

private val logger = KotlinLogging.logger{}

@Serializable
@SerialName("informe")
data class Informe(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID = UUID.randomUUID(),
    @Serializable(with = LocalDateTimeSerializer::class)
    val creation:LocalDateTime = LocalDateTime.now(),
    ){companion object}

fun Informe.Companion.writeToJSONFile(informe: Informe, jsonFile: File){
    logger.debug { "Escribiendo JSON: ${jsonFile.absolutePath}" }
    val json = Json {prettyPrint=true}
    jsonFile.writeText(json.encodeToString(informe))
    logger.debug { "Fichero completado: ${jsonFile.absolutePath}" }
}
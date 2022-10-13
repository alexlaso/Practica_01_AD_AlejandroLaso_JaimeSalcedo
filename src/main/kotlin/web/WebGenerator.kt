package web

class WebGenerator(
    val distrito: String,
    val contenedoresDistrito: Int,
    val toneladasDistritoResiduoTotales: Int,
    val maximoResiduos: String,
    val minimoResiduos: String,
    val mediaResiduos: String,
    val desviaciónResiduos: String,

    val mediaContenedoresTipo: Int,
    val mediaContenedoresDistrito: Int,
    val mediaToneladasAnuales: String,
    val recogidaDistrito: String,
    val tipoResiduoDistrito: String,
    val maximo: String,
    val minimo: String,
    val media: String,
    val desviacion: String,

    val tiempoGeneracion: Long,
    val fechaGeneracion: String,) {
    fun generateResume(): String{
        return """
            <!DOCTYPE html>
            <html lang="en">
            <head>
            <meta charset="UTF-8" />
            <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="stylesheet" href="style.css"/>
            <title>Resumen de recogidas de basura y reciclaje de “Distrito”</title>
            </head>
            <body>
            <!-- Archivo Madrid -->
            <h1 align="center">
            </body>
            </html>
        """.trimIndent()
    }
}
import java.io.File
import kotlin.system.exitProcess

class CSVReader {
    private fun checkCSVResiduosIsValid(csvName: String, delimiter: String) {
        val csvFile = File(csvName)
        if (!csvFile.exists()) {
            println("File ${csvFile.name} does not exist.")
            exitProcess(1706)
        } else if (!csvFile.canRead()) {
            println("File ${csvFile.name} cannot be read.")
            exitProcess(1705)
        } else if (!csvFile.name.endsWith(".csv")) {
            println("File ${csvFile.name} is not a csv file.")
            exitProcess(1704)
        }

        val cabecera = csvFile.readLines().firstOrNull()
        if (cabecera == null) {
            println("File ${csvFile.name} is empty. Use a valid CSV file.")
            exitProcess(1707)
        }

        val arguments = cabecera.split(delimiter)
        var allOK = true

        for (index in arguments.indices) {
            when (index) {
                0 -> if (arguments[index] != "Año") {
                    allOK = false
                }
                1 -> if (arguments[index] != "Mes") {
                    allOK = false
                }
                2 -> if (arguments[index] != "Lote") {
                    allOK = false
                }
                3 -> if (arguments[index] != "Residuo") {
                    allOK = false
                }
                4 -> if (arguments[index] != "Distrito") {
                    allOK = false
                }
                5 -> if (arguments[index] != "Nombre Distrito") {
                    allOK = false
                }
                6 -> if (arguments[index] != "Toneladas") {
                    allOK = false
                }
                else -> allOK = false
            }
        }

        if (!allOK) {
            println("File ${csvFile.name} has an incorrect format.")
            exitProcess(1708)
        }
    }
}
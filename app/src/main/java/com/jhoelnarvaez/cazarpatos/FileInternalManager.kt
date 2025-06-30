package com.jhoelnarvaez.cazarpatos

import android.app.Activity
import android.content.Context

class FileInternalManager(val actividad: Activity) : FileHandler {
    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val contenido = datosAGrabar.first + System.lineSeparator() + datosAGrabar.second
        actividad.openFileOutput("fichero_interno.txt", Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(contenido)
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        return try {
            val contenido = actividad.openFileInput("fichero_interno.txt").bufferedReader().use {
                it.readText()
            }
            val datos = contenido.split(System.lineSeparator())
            val email = datos.getOrNull(0) ?: ""
            val clave = datos.getOrNull(1) ?: ""
            Pair(email, clave)
        } catch (e: Exception) {
            Pair("", "")
        }
    }
}

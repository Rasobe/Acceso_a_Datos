package UD02_manejo_de_ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UD2_11_leer_archivo_v2 {

	public static void main(String[] args) {

		try {

			String fichero = new String(Files.readAllBytes(Paths.get("Acceso_a_datos/ficheros/Ev1/UD02/ej11.json")));
			// Parseamos el string para que no salga deprecated.
			JsonArray peliculas = JsonParser.parseString(fichero).getAsJsonArray();

			for (JsonElement jsonElement : peliculas) {
				JsonObject pelicula = jsonElement.getAsJsonObject();
				System.out.println("Id: " + pelicula.get("id").getAsInt() + "\nTitulo: " + pelicula.get("titulo")
						+ "\nAnyo creación: " + pelicula.get("anyo").getAsInt() + "\nDescripción: "
						+ pelicula.get("descripcion").getAsString() + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

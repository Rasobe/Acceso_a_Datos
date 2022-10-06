package UD02_repaso;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import UD02_objetos.Pelicula;

public class ej11 {

	public static void main(String[] args) {

		/**
		 * UD2.11 Crea un paquete de nombre UD2_11 e incluye en él todas las clases
		 * necesarias para crear en disco un fichero JSON con la información que aparece
		 * en el ejercicio anterior. Incluye también las dos clases que recorren el
		 * documento JSON creado que se han explicado en los materiales...
		 */

		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1, "El señor de los Anillos: la Comunidad del Anillo", 2001,
				"Ambientada en la tierra Media, cuenta la historia del Señor Oscuro Sauron, que es´ta buscando el Anillo Único, el cual ha acabado en poder de hobbit Frodo Bolsón (Elijah Wood)..."));
		peliculas.add(new Pelicula(2, "El Señor de los Anillos: las dos torres", 2002,
				"La trama de la película comienza tras la disolución de la Compañía del Anillo."));
		peliculas.add(new Pelicula(3, "El Señor de los Anillos: el retorno del rey", 2003,
				"Trata sobre la última parte del viaje que emprendieron los nueve compañeros (de los cuales quedan solamente ocho) para slavar la Tierra Media de la oscuridad impuesta por Sauron."));

		Gson gson = new Gson();
		
		try {

			// crear archivo
			
			FileWriter fw = new FileWriter("Acceso_a_datos/ficheros/Ev1/UD02/repaso_ej11.json");
			gson.toJson(peliculas, fw);
			fw.close();
			System.out.println("Archivo creado correctamente!");
			
			// leer archivoo
			
			String fichero = new String(Files.readAllBytes(Paths.get("Acceso_a_datos/ficheros/Ev1/UD02/repaso_ej11.json")));
			JsonArray lista = JsonParser.parseString(fichero).getAsJsonArray();
			
			for (JsonElement jsonElement : lista) {
				JsonObject obj = jsonElement.getAsJsonObject();
				System.out.println(obj.get("id").getAsInt() + " - " + obj.get("titulo").getAsString() + " - " + obj.get("anyo").getAsInt() + " - " + obj.get("descripcion").getAsString());
				
			}
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

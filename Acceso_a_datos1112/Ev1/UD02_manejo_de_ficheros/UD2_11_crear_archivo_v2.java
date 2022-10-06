package UD02_manejo_de_ficheros;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import UD02_objetos.Pelicula;

public class UD2_11_crear_archivo_v2 {

	public static void main(String[] args) {

try {
			
			Gson gson = new Gson();
			
			// Creamos las películas y las almacenamos en una lista.
			
			List<Pelicula> peliculas = Arrays.asList(
					new Pelicula(1, "El señor de los Anillos: la Comunidad del Anillo", 2001,
							"Ambientada en la tierra Media, cuenta la historia del Señor Oscuro Sauron, que es´ta buscando el Anillo Único, el cual ha acabado en poder de hobbit Frodo Bolsón (Elijah Wood)..."),
					new Pelicula(2, "El Señor de los Anillos: las dos torres", 2002,
							"La trama de la película comienza tras la disolución de la Compañía del Anillo."), 
					new Pelicula(3, "El Señor de los Anillos: el retorno del rey", 2003,
							"Trata sobre la última parte del viaje que emprendieron los nueve compañeros (de los cuales quedan solamente ocho) para slavar la Tierra Media de la oscuridad impuesta por Sauron.")
					);
			
			// Creamos un FileWriter para escribir el archivo JSON de la siguiente manera.
			
			FileWriter fw = new FileWriter("ficheros/Ev1/UD02/ej11.json");
			gson.toJson(peliculas, fw);
			fw.close();
			System.out.println("Archivo creado correctamente!");
			
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}

	}

}

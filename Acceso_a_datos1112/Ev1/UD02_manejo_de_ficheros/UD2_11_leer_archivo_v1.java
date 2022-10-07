package UD02_manejo_de_ficheros;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import UD02_objetos.Pelicula;

public class UD2_11_leer_archivo_v1 {

	public static void main(String[] args) {

		try {

			Gson gson = new Gson();

			String ficheros = new String(Files.readAllBytes(Paths.get("ficheros/Ev1/UD02/ej11.json")));
			List<Pelicula> peliculas = Arrays.asList(gson.fromJson(ficheros, Pelicula[].class));
			
			peliculas.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

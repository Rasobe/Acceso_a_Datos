package UD02_repaso;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Gson_prueba {

	public static void main(String[] args) {
		
		Gson gson = new Gson();
		
		try {

			// leer
			
			String todo = new String(Files.readAllBytes(Paths.get("ficheros/Ev1/ud02/repaso_ej11.json")));
			JsonArray lista = JsonParser.parseString(todo).getAsJsonArray();
			
			lista.forEach(pelicula -> {
				JsonObject objeto = pelicula.getAsJsonObject();
				
				System.out.println(objeto.get("id").getAsInt() + " - " + objeto.get("titulo").getAsString() + " - " + objeto.get("anyo").getAsInt() + " - " + objeto.get("descripcion").getAsString());
				
			});
			
			// escribir
			
			FileWriter fw = new FileWriter("ficheros/Ev1/ud02/repaso_ej11_1.json");
			gson.toJson(lista, fw);
			fw.close();
			System.out.println("Creado con éxito");
			
			// leer de nuevo
			
			String leer_todo = new String(Files.readAllBytes(Paths.get("ficheros/Ev1/ud02/repaso_ej11.json")));
			JsonArray lista_todo = JsonParser.parseString(leer_todo).getAsJsonArray();
			
			lista_todo.forEach(System.out::println);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}

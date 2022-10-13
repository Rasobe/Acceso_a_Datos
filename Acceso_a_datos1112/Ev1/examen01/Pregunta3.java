package examen01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Pregunta3 {

	public static void main(String[] args) {

		Gson gson = new Gson();
		Scanner s = new Scanner(System.in);
		
		// pedimos datos por teclado
		System.out.println("Introduce el nombre de un oficio: ");
		String oficio = s.next().toLowerCase();
		
		while (!oficio.matches("[a-zA-Z]+")) {
			System.out.println("Escribe un oficio válido: ");
			oficio = s.next().toLowerCase();
		}
		
		// recogemos la lista de empleados para trabajar con ella
		try {
			String todo = new String(Files.readAllBytes(Paths.get("ficheros/Ev1/Examenes/Ex1/empleados.json")));
			JsonArray lista = JsonParser.parseString(todo).getAsJsonArray();
			
			int contador = 0;
			double salario_minimo = 0;
			
			// recorremos la lista de empleados
				for (JsonElement jsonElement : lista) {
					JsonObject j = jsonElement.getAsJsonObject();
					if (j.get("oficio").getAsString().equalsIgnoreCase(oficio)) {
						if (contador == 0) {
							salario_minimo = j.get("salario").getAsDouble();
						}
						contador++;
						
						if (salario_minimo >= j.get("salario").getAsDouble()) {
							salario_minimo = j.get("salario").getAsDouble();
						}
						
					}
				}
			
				if (contador == 0) {
					System.out.println("No existen empelado de oficio: " + oficio.toUpperCase());
				} else {
					System.out.println(contador + " empelados de oficio: " + oficio.toUpperCase());
					System.out.println("El salario mínimo de " + oficio.toUpperCase() + " es " + salario_minimo);
				}
				 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

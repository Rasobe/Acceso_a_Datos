package manejo_de_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UD2_05 {

	public static void main(String[] args) {

		/**
		 * UD2.5 Realiza una clase UD2_5 que sea capaz de ordenar alfabéticamente las
		 * líneas contenidas en un fichero de texto (puedes crearlo con el bloc de
		 * notas). El nombre del fichero que contiene las líneas se debe pasar como
		 * argumento. El nombre del fichero resultado ya ordenado debe ser el mismo que
		 * el original añadiéndole la coletilla _sort al nombre. Incluye también
		 * tratamiento de excepciones.
		 */

		try {
			System.out.println("Leyendo archivo...");
			BufferedReader br = new BufferedReader(new FileReader("ficheros/Ev1/UD2/ej05.txt"));
			String linea;
			List<String> lista = new ArrayList<>();
			while ((linea = br.readLine()) != null) {
				lista.add(linea);
			}
			br.close();
			System.out.println("Ordenando archivo...");
			lista.forEach(System.out::println);
			System.out.println();
			// Con la función de la línea 33 podemos ordenar las listas que esten formadas obligatoriamente de STRINGS
			Collections.sort(lista);
			// Método para recorrer una lista de forma rápida
			lista.forEach(System.out::println);
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/Ev1/UD2/ej05_sort.txt"));
			for (String lineaOrdenada : lista) {
				bw.write(lineaOrdenada);
				bw.newLine();
			}
			System.out.println("Archivo ordenado y creado correctamente.");
			bw.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

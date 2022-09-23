package manejo_de_ficheros;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class UD2_06_texto_junto {

	public static void main(String[] args) {
		
		/**
		 * UD2.6 Realiza una clase UD2_6 que indique cuántas veces aparece una palabra
		 * dentro de un fichero de texto (puedes crearlo con el bloc de notas). Tanto el
		 * nombre del fichero como la palabra se deben pasar como argumentos. No
		 * distinguir mayúsculas/minúsculas. Incluye también tratamiento de excepciones.
		 */

		Scanner s = new Scanner(System.in);
		System.out.print("Introduce la palabra a buscar: ");
		String palabra_a_buscar = s.next();
		File f = new File("ficheros/Ev1/UD2/ej06_junto.txt");

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String linea = "";
			int contador = 0;
			int posicion_caracter = 0;
			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					if ((posicion_caracter = linea.toLowerCase().indexOf(palabra_a_buscar.toLowerCase())) != -1) {
						contador++;
						linea = linea.substring(posicion_caracter + 1, linea.length());
					}
				}
			}
			if (contador == 0) {
				System.out.println("La palabra '" + palabra_a_buscar + "' no se ha encontrado en el texto.");
			} else if (contador == 1) {
				System.out.println("La palabra '" + palabra_a_buscar + "' sale en el texto " + contador + " vez.");
			} else {
				System.out.println("La palabra '" + palabra_a_buscar + "' sale en el texto " + contador + " veces.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package manejo_de_ficheros;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UD2_06 {

	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {

		/**
		 * UD2.6 Realiza una clase UD2_6 que indique cuántas veces aparece una palabra
		 * dentro de un fichero de texto (puedes crearlo con el bloc de notas). Tanto el
		 * nombre del fichero como la palabra se deben pasar como argumentos. No
		 * distinguir mayúsculas/minúsculas. Incluye también tratamiento de excepciones.
		 */
		
		try {
			System.out.print("Pon una palabra a buscar en el texto: ");
			String palabra_a_buscar = s.next();
			BufferedReader br = new BufferedReader(new FileReader("ficheros/Ev1/UD2/ej06.txt"));
			String linea;
			String texto = "";
			int contador = 0;
			while ((linea = br.readLine()) != null) {
				texto += linea + " ";
			}
			StringTokenizer st = new StringTokenizer(texto, " ");
			while (st.hasMoreTokens()) {
				if (st.nextToken().equalsIgnoreCase(palabra_a_buscar)) {
					contador++;
				}
			}
			if (contador == 0) {
				System.out.println("No se ha encontrado '" + palabra_a_buscar + "' en el texto.");
			} else if (contador == 1) {
				System.out.println("La palabra '" + palabra_a_buscar + "' se ha encontrado " + contador + " vez.");
			} else {
				System.out.println("La palabra '" + palabra_a_buscar + "' se ha encontrado " + contador + " veces.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

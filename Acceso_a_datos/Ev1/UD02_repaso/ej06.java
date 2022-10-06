package UD02_repaso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ej06 {

	public static void main(String[] args) {

		/**
		 * UD2.6 Realiza una clase UD2_6 que indique cuántas veces aparece una palabra
		 * dentro de un fichero de texto (puedes crearlo con el bloc de notas). Tanto el
		 * nombre del fichero como la palabra se deben pasar como argumentos. No
		 * distinguir mayúsculas/minúsculas. Incluye también tratamiento de excepciones.
		 * Pistas: - Métodos para cadenas substring e indexOf.
		 */

		File f = new File("Acceso_a_datos/ficheros/Ev1/UD02/repaso_ej06.txt");
		
		int contador = 0;
		String palabra_a_buscar = "JOse";
		String texto = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String linea;
			while((linea = br.readLine()) != null) {
				texto += linea.toLowerCase();
			}
			br.close();
			
			while (texto.indexOf(palabra_a_buscar.toLowerCase()) != -1) {
				texto = texto.substring(texto.indexOf(palabra_a_buscar.toLowerCase()) + palabra_a_buscar.length());
				contador++;
			}
			
			System.out.println(contador);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

package UD02_repaso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ej04 {

	public static void main(String[] args) throws IOException {

		/**
		 * UD2.4 Realiza una clase UD2_4 que guarde en un fichero con nombre pares.txt
		 * los números pares que hay entre 0 y 500, un número en cada línea del fichero.
		 * Seguidamente lee el fichero y muéstralo por la consola. Incluye también
		 * tratamiento de excepciones.
		 */

		// Crear archivo

		File f = new File("Acceso_a_datos/ficheros/Ev1/UD02/repaso_pares.txt");
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		System.out.println("Creando archivo...");
		for (int i = 2; i <= 500; i++) {
			if (i % 2 == 0) {
				bw.write(String.valueOf(i));
				bw.newLine();
			}

		}
		bw.close();
		System.out.println("Archivo creado correctamente!");

		// Leer archivo

		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea_leida;
		while ((linea_leida = br.readLine()) != null) {
			System.out.println(linea_leida);
		}

	}

}

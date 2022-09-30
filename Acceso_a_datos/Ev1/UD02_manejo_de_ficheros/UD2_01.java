package UD02_manejo_de_ficheros;

import java.io.File;

public class UD2_01 {

	public static void main(String[] args) {

		/**
		 * UD2.1 Realiza una clase UD2_1 que muestre nombre, longitud, si se puede leer,
		 * si se puede escribir de todos los archivos ocultos de la carpeta Windows de
		 * tu disco; sólo de los que se encuentran en la carpeta principal de Windows,
		 * no en sus subcarpetas.
		 */

		File f = new File("C://WINDOWS");
		if (f.isDirectory()) {
			File[] listFiles = f.listFiles();
			for (File file : listFiles) {
				if (file.isHidden()) {
					System.out.println("Nombre: " + file.getName());
					System.out.println("Tamaño fichero: " + file.length());
					System.out.println("¿Se puede leer? " + file.canRead());
					System.out.println("¿Se puede escribir? " + file.canWrite());
					System.out.println("");
				}
			}
		} else {
			System.out.println("No es un directorio válido.");
		}

	}

}
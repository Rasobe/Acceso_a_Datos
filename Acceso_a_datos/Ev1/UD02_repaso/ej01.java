package UD02_repaso;

import java.io.File;

public class ej01 {

	public static void main(String[] args) {

		/**
		 * UD2.1 Realiza una clase UD2_1 que muestre nombre, longitud, si se puede leer,
		 * si se puede escribir de todos los archivos ocultos de la carpeta Windows de
		 * tu disco; sólo de los que se encuentran en la carpeta principal de Windows,
		 * no en sus subcarpetas.
		 */
		
		File directorio = new File("C://WINDOWS");
		File[] lista_archivos = directorio.listFiles();
		
		for (File file : lista_archivos) {
			if (file.isHidden()) {
				System.out.println(file.getName() + " - " + file.length() + " - " + file.canRead() + " - " + file.canWrite());
			}
		}

	}

}

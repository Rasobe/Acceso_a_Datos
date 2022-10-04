package UD02_repaso;

import java.io.File;
import java.io.IOException;

public class ej03 {

	public static void main(String[] args) {

		/**
		 * UD2.3 Realiza una clase UD2_3 que complete la clase EjemploClaseFile02 de los
		 * materiales borrando el directorio y el fichero creados en ella (primero borra
		 * el fichero y después el directorio pues no se permite borrar directorios no
		 * vacíos).
		 */
		
		// Creacion de directorio y fichero
		
		boolean resultado;
		File directorio = new File("C:/DAM2");
		resultado = directorio.mkdir();
		if (resultado) {
			System.out.println("Directorio creado.");
		} else {
			System.out.println("No se puede crear el directorio.");
			System.exit(-1);
		}
		try {
			File fichero = new File("C:/DAM2/Alberto.txt");
			resultado = fichero.createNewFile();
			if (resultado) {
				System.out.println("Archivo creado.");
			} else {
				System.out.println("No se pudo crear el archivo.");
			}
		} catch (IOException e) {
			System.out.println("Se produjo un error: " + e.getMessage());
		}
		
		// Eliminación de directorio y fichero
		 
		
		File f = new File("C:/DAM2/Alberto.txt");
		boolean eliminado = false;
		if (f.exists()) {
			eliminado = f.delete();
			if (eliminado) {
				System.out.println("Archivo elminado correctamente.");				
			} else {
				System.out.println("No se pudo eliminar el archivo.");
			}
		} else {
			System.out.println("No se pudo encontrar el archivo.");
		}
		
		f = new File("C:/DAM2");
		if (f.exists()) {
			eliminado = f.delete();
			if (eliminado) {
				System.out.println("Archivo eliminado correctamente.");
			} else {
				System.out.println("No se pudo eliminar el archivo.");
			}
		} else {
			System.out.println("No se pudo encontrar el directorio.");			
		}
	}

}

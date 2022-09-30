package UD02_manejo_de_ficheros;

import java.io.File;
import java.io.IOError;
import java.io.IOException;

public class UD2_03 {

	public static void main(String[] args) {

		/**
		 * UD2.3 Realiza una clase UD2_3 que complete la clase EjemploClaseFile02 de los
		 * materiales borrando el directorio y el fichero creados en ella (primero borra
		 * el fichero y después el directorio pues no se permite borrar directorios no
		 * vacíos).
		 */
		
		/**
		* // CREAR DIRECTORIO Y ARCHIVO
		* File directorio = new File("C://DAM2");
		* if (directorio.mkdir()) {
		* 	 System.out.println("Directorio creado");
		* } else {
		* 	 System.out.println("No se pudo crear el directorio");
		*  	 // Posiblemente exista
		*	 System.exit(-1);
		* }
		* try {
		*	 File fichero = new File("C://DAM2//Alberto.txt");
		*	 if (fichero.createNewFile()) {
		*		 System.out.println("Archivo creado correctamente");
		*	 } else {
		*		 System.out.println("No se ha podido crear el archivo");
		*	 }
		* } catch (IOException e) {
		*	 System.out.println("Se produjo el error: " + e.getMessage());
		* }
		*/
		
		// BORRAR DIRECTORIO Y ARCHIVO
				File fichero = new File("C://DAM2//Alberto.txt");
				if (fichero.delete()) {
					System.out.println("El archivo se ha borrado correctamente");
				} else {
					System.out.println("No se ha podido borrar el archivo");
				}
				
				File directorio = new File("C://DAM2");
				if (directorio.delete()) {
					System.out.println("Se ha borrado correctamente el directorio");
				} else {
					System.out.println("No se ha podido borrar el directorio");
				}
	}

}

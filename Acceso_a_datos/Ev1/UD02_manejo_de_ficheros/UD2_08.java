package UD02_manejo_de_ficheros;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import UD02_objetos.Profesor;

public class UD2_08 {

	public static void main(String[] args) {

		/**
		 * UD2.8 Adaptación de los ejemplos vistos en los materiales con las clases
		 * ObjectInputStream y ObjectOuputStream. Realiza una clase UD2_8 que pida al
		 * usuario datos de varios profesores (nombre y la antigüedad) y los inserte en
		 * el fichero antiguedad.dat_obj.dat. Si el fichero no existe se creará con los
		 * nuevos datos introducidos, en caso contrario se añadirán por el final. Antes
		 * de finalizar el código se recorrerá el fichero para visualizar su contenido.
		 * Prueba varias veces la ejecución de la clase.
		 */

		Scanner s = new Scanner(System.in);
		System.out.print("Introduce la cantidad de profes que quieres escribir: ");
		List<Profesor> lista = new ArrayList<>();
		String num_profes = s.next();

		// Comprobamos si ha puesto una cantidad correcta.
		while (!num_profes.matches("[0-9]+")) {
			System.out.println("Introduce una cantidad de profesores válida: ");
			num_profes = s.next();
		}

		for (int i = 0; i < Integer.valueOf(num_profes); i++) {
			System.out.print("\nIntroduce el nombre del profesor: ");
			String nombre = s.next();

			// Comprobamos si es una cadena.
			while (!nombre.matches("[a-zA-Z]+")) {
				System.out.println("Introduce un nombre válido: ");
				nombre = s.next();
			}
			System.out.print("Introduce la antigüedad de " + nombre + ": ");
			String antiguedad = s.next();

			// Comprobamos si ha puesto una antigüedad correcta.
			while (!antiguedad.matches("[0-9]{1,2}")) {
				System.out.println("Introduce una antigüedad válida: ");
				antiguedad = s.next();
			}
			lista.add(new Profesor(nombre, Integer.valueOf(antiguedad)));
		}

		File f = new File("Acceso_a_datos/ficheros/Ev1/UD02/antiguedad_obj.dat");
		if (!f.exists()) { // Si el archivo NO existe...
			System.out.println("Creando archivo...");
			try {
				f.createNewFile();
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				for (Profesor profesor : lista) {
					oos.writeObject(profesor);
				}
				System.out.println("Archivo creado correctamente!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else { // Si el archivo existe...
						
			// Escribimos los nuevos profesores al final de la lista
			
			try {
				FileOutputStream fos = new FileOutputStream(f, true);
				MiObjectOutputStream moos = new MiObjectOutputStream(fos);
				for (Profesor profesor : lista) {
					moos.writeObject(profesor);
				}
				moos.close();
			} catch (IOException e) {
			}
			
			// Imprimimos la lista nueva de profesores una vez hemos implementado todos los profesores.
			
			List<Profesor> lista_profesores_archivo = new ArrayList<>();
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
				while (true) {
					System.out.println(ois.readObject());
				}
			} catch (IOException e) {
			} catch (ClassNotFoundException e) {
			}
		}

	}

}

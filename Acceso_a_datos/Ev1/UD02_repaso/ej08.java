package UD02_repaso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import UD02_manejo_de_ficheros.MiObjectOutputStream;
import UD02_objetos.Profesor;

public class ej08 {

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
		
		System.out.println("¿Cuantos profesores quieres crear?");
		int num = s.nextInt();

		File f = new File("Acceso_a_datos/ficheros/Ev1/UD02/repaso_ej08.txt");
		List<Profesor> lista = new ArrayList<>();
		String nombre;
		int antiguedad;
		Profesor p;
		
		for (int i = 0; i < num; i++) {
			System.out.println("Nombre profesor:");
			nombre = s.next();
			System.out.println("Antigüedad:");
			antiguedad = s.nextInt();

			p = new Profesor(nombre, antiguedad);
			lista.add(p);
		}
		
		try {
			if (!f.exists()) {
				ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
				for (Profesor profesor : lista) {
					oos.writeObject(profesor);
				}
				System.out.println("Archivo creado con éxito!");
			} else {
				FileOutputStream fos = new FileOutputStream(f, true);
				MiObjectOutputStream moos = new MiObjectOutputStream(fos);
				
				for (Profesor profesor : lista) {
					moos.writeObject(profesor);
				}
				moos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

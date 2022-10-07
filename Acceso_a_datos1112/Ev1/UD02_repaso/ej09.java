package UD02_repaso;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class ej09 {

	public static void main(String[] args) throws IOException {

		/**
		 * UD2.9 Adaptación de los ejemplos vistos en los materiales con la clase
		 * RandomAccessFile. Realiza una clase UD2_9 que pida al usuario el
		 * identificador del profesor y lo borre del fichero ProfesFPSierraGuara.dat.
		 * Borrar un dato simplemente consiste en poner su campo id dentro del fichero a
		 * 0 para indicar que ese registro no existe y su posición está libre. Se deberá
		 * controlar que:
		 * 
		 * - El identificador del profesor esté dentro de los límites del fichero. - El
		 * identificador del profesor debe existir. Si ha sido borrado previamente se
		 * advertirá de la situación. - Antes de finalizar el código visualizar de
		 * manera secuencial todos los registros del fichero para comprobar la
		 * operación.
		 * 
		 */

		Scanner s = new Scanner(System.in);

		// creamos los profesores con arrays normales
		String profesores[] = { "Luis Aldea", "Raúl Solano", "Oskaras el boss", "Pablo Samperiz" };
		int departamentos[] = { 10, 20, 20, 30 };
		Double antiguedades[] = { 29.5, 12.5, 42.3, 19.2 };

		// Escribimos los profesores

		File f = new File("ficheros/Ev1/UD02/repaso_ej09.txt");
		RandomAccessFile raf = new RandomAccessFile(f, "rw");
		StringBuffer sb;

		for (int i = 0; i < antiguedades.length; i++) {
			raf.writeInt(i + 1);
			sb = new StringBuffer(profesores[i]);
			sb.setLength(20);
			raf.writeChars(sb.toString());
			raf.writeInt(departamentos[i]);
			raf.writeDouble(antiguedades[i]);
		}
		
//		System.out.println("Escribe la ID del profesor que quieras eliminar: ");
//		int id_borrar = s.nextInt();
//		
//		while (id_borrar <= 0 || id_borrar > profesores.length) {
//			System.out.println("Escribo una ID dentro de los limites: ");
//			id_borrar = s.nextInt();
//		}
		
		// Leemos profesores
		
		raf.seek(0);
		int posicion = 0;
		String nombre_entero = "";
		while (raf.getFilePointer() < raf.length()) {
			nombre_entero = "";
			System.out.println(raf.readInt());
			for (int i = 0; i < 20; i++) {
				nombre_entero += raf.readChar();
			}
			System.out.println(nombre_entero);
			System.out.println(raf.readInt());
			System.out.println(raf.readDouble());
			posicion += 56;
		}
		raf.close();

	}

}

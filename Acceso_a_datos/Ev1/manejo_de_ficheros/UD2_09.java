package manejo_de_ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UD2_09 {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.print("Introduzca el ID del profesor que desee borrar: ");
		String id = s.next();
		int posicion = (Integer.valueOf(id) + 1) * 56;

		// Comprobamos si es una ID válida.
		while (!id.matches("[0-9]+")) {
			System.out.print("Introduzca una ID válida: ");
			id = s.next();
		}

		File f = new File("ficheros/Ev1/UD2/ProfesFPSierraGuara.dat");
		try {

			/**
			 * Creamos el archivo donde podremos leer y escribir con "rw". si queremos solo
			 * leer ponemos "r".
			 */
			
			RandomAccessFile archivo = new RandomAccessFile(f, "rw");
			if (posicion > archivo.length() - 56) {
				System.out.println("No se ha encontrado ningún profesor con esta ID. (" + id + ")");
			} else {
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

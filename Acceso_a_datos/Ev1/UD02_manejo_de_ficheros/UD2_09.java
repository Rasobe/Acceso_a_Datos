package UD02_manejo_de_ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UD2_09 {

	public static void main(String[] args) {

		/**
		 * Adaptación de los ejemplos vistos en los materiales con la clase
		 * RandomAccessFile. Realiza una clase UD2_9 que pida al usuario el
		 * identificador del profesor y lo borre del fichero ProfesFPSierraGuara.dat.
		 * Borrar un dato simplemente consiste en poner su campo id dentro del fichero a
		 * 0 para indicar que ese registro no existe y su posición está libre. Se deberá
		 * controlar que: 
		 * 
		 * 	- El identificador del profesor esté dentro de los límites del
		 * fichero. 
		 * 	- El identificador del profesor debe existir. Si ha sido borrado
		 * previamente se advertirá de la situación. 
		 * 	- Antes de finalizar el código
		 * visualizar de manera secuencial todos los registros del fichero para
		 * comprobar la operación.
		 */

		Scanner s = new Scanner(System.in);
		System.out.print("Introduzca el ID del profesor que desee borrar: ");
		String id = s.next();
		int posicion = (Integer.valueOf(id) - 1) * 56;

		// Comprobamos si es una ID válida.
		while (!id.matches("[1-9][0-9]*")) {
			System.out.print("Introduzca una ID válida: ");
			id = s.next();
		}

		File f = new File("ficheros/Ev1/UD02/ProfesFPSierraGuara.dat");
		try {

			/**
			 * Creamos el archivo donde podremos leer y escribir con "rw". si queremos solo
			 * leer ponemos "r".
			 */

			RandomAccessFile archivo = new RandomAccessFile(f, "rw");
			if (posicion > archivo.length() - 56) {
				System.out.println("No se ha encontrado ningún profesor con esta ID. (" + id + ")");
			} else {
				archivo.seek(posicion); // nos posicionamos
				int id2 = archivo.readInt(); // leemos la id de la posicion actual
				if (id2 == 0) {
					System.out.println("El profesor con este ID ya ha sido borrado anteriormente.");
					archivo.seek(0); // reposicionamos el puntero para que no esté mal posicionado el puntero
				} else {
					archivo.seek(posicion); // posicionamos de nuevo el puntero donde el profesor
					archivo.writeInt(0); // reescribimos su ID a 0 para indicar que está borrado
					archivo.seek(0); // nos volvemos a posicionar en la posición inicial para no posicionar de manera
										// incorrecta al puntero.
				}

			}

			// Mostramos la lista de profesores para comprobar si lo hemos borrado
			// correctamente

			int id_profesor;
			int departamento;
			double antiguedad;
			char profesores[] = new char[20];

			System.out.println("ID    Profesor    Departamento    Antigüedad");
			
			while (archivo.getFilePointer() != archivo.length()) {
				//	archivo.seek(0); nos volvemos a posicionar en el inicio pero esto no haría falta
				id_profesor = archivo.readInt();	// obtenemos el ID del profesor
				for (int i = 0; i < profesores.length; i++) {
					profesores[i] = archivo.readChar();
				}
				String profesor = new String(profesores);
				departamento = archivo.readInt();
				antiguedad = archivo.readDouble();
				
				System.out.println(id_profesor + "	" + profesor + "	" + departamento + "	" + antiguedad);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

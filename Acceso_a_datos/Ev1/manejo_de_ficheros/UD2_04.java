package manejo_de_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UD2_04 {

	public static void main(String[] args) throws IOException {

		/**
		 * UD2.4 Realiza una clase UD2_4 que guarde en un fichero con nombre pares.txt
		 * los números pares que hay entre 0 y 500, un número en cada línea del fichero.
		 * Seguidamente lee el fichero y muéstralo por la consola. Incluye también
		 * tratamiento de excepciones.
		 */

		BufferedWriter bw = new BufferedWriter(new FileWriter("ficheros/Ev1/UD2/pares.txt"));
		for (int i = 2; i < 500; i++) {
			if (i % 2 == 0) {
				bw.write(String.valueOf(i));	// Convertimos el número entero en cadena para que al leer el fichero, se pueda leer correctamente
				bw.newLine();
			}
		}
		bw.close();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader("ficheros/Ev1/UD2/pares.txt"));
			String linea;
			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

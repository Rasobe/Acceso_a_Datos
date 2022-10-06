package UD02_repaso;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ej07 {

	public static void main(String[] args) {

		/**
		 * UD2.7 Realiza una clase UD2_7 que guarde 20 números enteros aleatorios
		 * comprendidos entre 1 y 5 en el fichero puntuación.dat. Completa el código
		 * abriendo el fichero para visualizarlos todos por la consola indicando al
		 * final cuántas
		 */

		File f = new File("Acceso_a_datos/ficheros/Ev1/UD02/repaso_ej07.txt");
		List<Integer> lista_fichero = null;
		int contador;
		
		try {
			DataOutputStream oos = new DataOutputStream(new FileOutputStream(f));
			List<Integer> lista = new ArrayList<>();

			Random r = new Random();
			
			for (int i = 0; i < 20; i++) {
//				lista.add((int) Math.round(Math.random() * 4) + 1);
				lista.add(r.nextInt(5) + 1);
			}

			for (Integer i : lista) {
				oos.writeInt(i);
			}
			oos.close();
			System.out.println("Archivo creado correctamente.");

			DataInputStream dis = new DataInputStream(new FileInputStream(f));
			
			lista_fichero = new ArrayList<>();
			
			while (true) {
				lista_fichero.add(dis.readInt());
			}

		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
				
		for (int i = 1; i <= 5; i++) {
			contador = 0;
			for (Integer num : lista_fichero) {
				if (num == i) {
					contador++;
				}
			}
			System.out.println("El número " + i + " se ha repetido " + contador + " veces.");
		}

	}

}

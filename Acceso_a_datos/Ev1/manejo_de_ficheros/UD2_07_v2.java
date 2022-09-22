package manejo_de_ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UD2_07_v2 {

	public static void main(String[] args) {
		List<Integer> lista = null;
		int numero = 0;

		// Creamos el archivo con los 20 números del 1 al 5.
		try {
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("ficheros/Ev1/UD2/puntuacion.dat"));
			// Random nos genera números aleatorios desde el 1 hasta el 5 incluidos los dos.
			// Ya que si no ponemos el +1 de la línea 18, nos saldrían números del 0 al 4.
			Random r = new Random();
			for (int i = 0; i < 20; i++) {
				dos.writeInt(r.nextInt(5) + 1);
			}
			dos.close();
			System.out.println("Archivo creado y escrito correctamente.");

			// Leemos el archivo anteriormente creado.
			DataInputStream dis = new DataInputStream(new FileInputStream("ficheros/Ev1/UD2/puntuacion.dat"));
			lista = new ArrayList<>();
			while (true) {
				lista.add(dis.readInt());
			}

		} catch (IOException e) {
		}

		// Imprimimos todos los números del archivo
		System.out.println("\nImprimiendo todos los números... \n");
		// lista.forEach(System.out::println); <-- Esta forma es la más rápida para
		// imprimir List.
		for (Integer num : lista) {
			System.out.print(num + " ");
		}
		System.out.println("\n"); // Separador

		// Aquí vemos cuántas veces sale cada número y lo imprimimos por pantalla.
		int contador;
		for (int i = 1; i <= 5; i++) {
			contador = 0;
			for (Integer num : lista) {
				if (num == i) {
					contador++;
				}
			}
			System.out.println("El número " + i + " se repite " + contador + " veces.");
		}
	}

}

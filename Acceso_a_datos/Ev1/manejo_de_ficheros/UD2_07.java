package manejo_de_ficheros;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

public class UD2_07 {

	public static void main(String[] args) {

		// Creamos el archivo con los 20 números del 1 al 5.
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Acceso_a_datos/ficheros/Ev1/UD2/puntuacion.dat"));
			// Random nos genera números aleatorios desde el 1 hasta el 5 incluidos los dos.
			// Ya que si no ponemos el +1 de la línea 18, nos saldrían números del 0 al 4.
			Random r = new Random();
			for (int i = 0; i < 20; i++) {
				bw.write(String.valueOf(r.nextInt(5) + 1));
				if (i != 19) {
					bw.newLine();
				}
			}
			bw.close();
			System.out.println("Archivo creado y escrito correctamente.");
			
			// Leemos el archivo anteriormente creado.
			BufferedReader br = new BufferedReader(new FileReader("Acceso_a_datos/ficheros/Ev1/UD2/puntuacion.dat"));
			String linea = "";
			List<String> lista = new ArrayList<>();
			while ((linea = br.readLine()) != null) {
				lista.add(linea);
			}
			
			// Imprimimos todos los números del archivo
			System.out.println("\nImprimiendo todos los números... \n");
			// lista.forEach(System.out::println); 										<--	Esta forma es la más rápida para imprimir List.
			for (String numero : lista) {
				System.out.print(numero + " ");
			}
			System.out.println("\n");	// Separador
			
			// Aquí vemos cuántas veces sale cada número y lo imprimimos por pantalla.
			int contador;
			for (int i = 1; i <= 5; i++) {
				contador = 0;
				for (String numero : lista) {
					if (numero.equals(String.valueOf(i))) {
						contador++;
					}
				}
				System.out.println("El número " + i + " se repite " + contador + " veces.");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

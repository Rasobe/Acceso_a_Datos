package UD02_repaso;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ej05 {

	public static void main(String[] args) throws IOException {

		/**
		 * UD2.5 Realiza una clase UD2_5 que sea capaz de ordenar alfabéticamente las
		 * líneas contenidas en un fichero de texto (puedes crearlo con el bloc de
		 * notas). El nombre del fichero que contiene las líneas se debe pasar como
		 * argumento. El nombre del fichero resultado ya ordenado debe ser el mismo que
		 * el original añadiéndole la coletilla _sort al nombre. Incluye también
		 * tratamiento de excepciones.
		 */
		
		if (args.length == 0) {
			System.out.println("No hay argumentos que leer.");
		} else {
			File f = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(f));
			String linea;
			List<String> lista = new ArrayList<>();
			while((linea = br.readLine()) != null) {
				lista.add(linea);
			}
			Collections.sort(lista);

			BufferedWriter bw = new BufferedWriter(new FileWriter(args[0].substring(0, args[0].length() - 4) + "_sort.txt"));
			for (String string : lista) {
				bw.write(string);
				bw.newLine();
			}
			bw.close();
			System.out.println("Archivo creado y ordenado con éxito!");
		}

	}

}

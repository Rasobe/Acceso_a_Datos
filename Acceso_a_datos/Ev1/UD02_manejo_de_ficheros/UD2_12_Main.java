package UD02_manejo_de_ficheros;

import java.io.File;

public class UD2_12_Main {

	public static void main(String[] args) {

		/**
		 * UD2.12 Realiza una clase UD2_12 con un método principal que reciba dos
		 * argumentos, un directorio y una extensión de fichero, e indique los datos
		 * (nombre, tamaño y fecha de creación) de los ficheros del directorio que
		 * tienen esa extensión. Pista: Interfaz FilenameFilter. Deberá comprobarse al
		 * principio del código que el directorio enviado como primer argumento existe,
		 * en caso contrario se indicará y finalizará su ejecución.
		 */

		UD2_12_FilenameFilter ej = new UD2_12_FilenameFilter();

		if (args.length != 0) {
			ej.accept(new File(args[0]), args[1]);
		} else {
			System.out.println("Faltan argumentos.");
		}

	}

}

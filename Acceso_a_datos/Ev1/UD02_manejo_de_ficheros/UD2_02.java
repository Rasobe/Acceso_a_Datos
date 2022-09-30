package UD02_manejo_de_ficheros;

import java.io.File;

import javax.imageio.metadata.IIOMetadataFormatImpl;

public class UD2_02 {

	public static void main(String[] args) {

		/**
		 * UD2.2 Realiza una clase UD2_2 cuyo método principal reciba como argumento una
		 * cadena con la trayectoria de un directorio o fichero e indique si existe
		 * realmente o no dicho directorio o fichero. Si el método principal no recibe
		 * ningún argumento se indicará por pantalla y finalizará su ejecución.
		 */

		if (args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				File f = new File(args[i]);
				if (!f.getName().equals("") && f.exists()) {
					if (f.isDirectory()) {
						System.out.println("Estamos ante un directorio.");
						System.out.println(f);
					} else {
						System.out.println("Estamos ante un fichero.");
						System.out.println(f);
					}
				} else {
					System.out.println("No se ha escrito ningún archivo o directorio o no existe.");
					System.out.println(f.exists());
					System.exit(-1);
				}
			}
		} else {
			System.out.println("No hay argumentos que inspeccionar.");
		}

	}

}

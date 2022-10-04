package UD02_repaso;

import java.io.File;

public class ej02 {

	public static void main(String[] args) {

		/**
		 * UD2.2 Realiza una clase UD2_2 cuyo método principal reciba como argumento una
		 * cadena con la trayectoria de un directorio o fichero e indique si existe
		 * realmente o no dicho directorio o fichero. Si el método principal no recibe
		 * ningún argumento se indicará por pantalla y finalizará su ejecución.
		 */
		
		if (args.length == 0) {
			System.out.println("No se han recibido argumentos.");
		} else {
			File f = new File(args[0]);
			if (f.exists()) {
				if (f.isDirectory()) {
					System.out.println("Estamos ante un directorio.");
				} else {
					System.out.println("Estamos ante un fichero.");
				}
			} else {
				System.out.println("No existe esta ruta ni como directorio ni como fichero.");
			}
		}

	}

}

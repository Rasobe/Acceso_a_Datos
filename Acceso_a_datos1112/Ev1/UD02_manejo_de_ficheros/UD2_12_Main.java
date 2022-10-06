package UD02_manejo_de_ficheros;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

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

//		if (args.length == 0) {
//			System.out.println("No se ha recibido ningun argumento");
//			System.exit(0);
//		} else {
//			File directorio = new File(args[0]);
//			// Creo una lista filtrandola por el valor de args[1]
//			File[] lista = directorio.listFiles(new FilenameFilter() {
//				@Override
//				public boolean accept(File file, String name) {
//					// TODO Auto-generated method stub
//					if (name.endsWith(args[1])) {
//						return true;
//					}
//					return false;
//				}
//
//			});
//
//			for (File fichero : lista) {
//
//				System.out.println("Nombre:\t" + fichero.getName());
//				System.out.println("Tamaño:\t" + fichero.length() + " bytes");
//
//
		// Para sacar fecha de creacion del archivo
		
		File my_file = new File("C:\\Users\\rasob\\Desktop\\Juegos");
		
		BasicFileAttributes file_att = null;
		
		try {
			file_att = Files.readAttributes(my_file.toPath(), BasicFileAttributes.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.printf("File Creation Time %s ", file_att.creationTime());
			
		

	}

}

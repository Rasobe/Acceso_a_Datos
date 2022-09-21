package manejo_de_ficheros;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.filechooser.FileNameExtensionFilter;

public class UD2_12_FilenameFilter implements FilenameFilter {

	public boolean accept(File directorio, String ext) {
		if (directorio.isDirectory() && directorio.exists()) {
			File[] listaFicheros = directorio.listFiles();
			boolean encontrado = false;
			for (File file : listaFicheros) {
				if (file.getName().contains(ext)) {
					encontrado = true;
					System.out.println("Nombre: " + file.getName());
					System.out.println("Tamaño: " + file.length());

					// Fecha de creación de un archivo
					BasicFileAttributes atributos_basicos = null;
					try {
						atributos_basicos = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
					} catch (IOException e) {
						e.printStackTrace();
					}

					FileTime tiempo_crudo = atributos_basicos.creationTime();

					SimpleDateFormat formato_dia = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

					String tiempo_formateado = formato_dia.format(new Date(tiempo_crudo.toMillis()));

					System.out.println("Fecha de creación: " + tiempo_formateado + "\n");
				}
			}
			if (!encontrado) {
				System.out.println("Archivos no encontrados con la extensión: " + ext);
			}
		} else {
			System.out.println("El directorio o la extensión no es valido.");
			return false;
		}
		return true;
	}

}

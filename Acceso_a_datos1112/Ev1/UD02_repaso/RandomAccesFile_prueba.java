package UD02_repaso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import UD02_objetos.Pelicula;

public class RandomAccesFile_prueba {

	public static void main(String[] args) {

		File f = new File("ficheros/ev1/ud02/repaso_randomaccesfile.dat");

		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1, "El señor de perros", 2000, "Gran pelicula, mejor actor."));
		peliculas.add(new Pelicula(2, "Cars 4", 2022, "Gran pelicula, mejor coche."));
		peliculas.add(new Pelicula(3, "Toy Story 22", 2032, "Buen muñeco señora."));

		try {

			// Crear archivo

			RandomAccessFile raf = new RandomAccessFile(f, "rw");

			peliculas.forEach(p -> {

				StringBuffer sbTitulo, sbDescripcion;

				try {

					raf.writeInt(p.getId());
					sbTitulo = new StringBuffer(p.getTitulo());
					sbTitulo.setLength(20);
					raf.writeChars(sbTitulo.toString());
					raf.writeInt(p.getAnyo());
					sbDescripcion = new StringBuffer(p.getDescripcion());
					sbDescripcion.setLength(20);
					raf.writeChars(sbDescripcion.toString());

				} catch (IOException e) {
					e.printStackTrace();
				}

			}); // 40 + 40 + 4 + 4 = 88

			System.out.println("Archivo creado con éxito!");

			// Leer archivo

			String titulo;
			String descripcion;

			int posicion = 0;
			raf.seek(posicion);
			while (raf.getFilePointer() != raf.length()) {
				titulo = "";
				descripcion = "";
				System.out.println(raf.readInt());
				for (int i = 0; i < 20; i++) {
					titulo += raf.readChar();
				}
				System.out.println(titulo);
				System.out.println(raf.readInt());
				for (int i = 0; i < 20; i++) {
					descripcion += raf.readChar();
				}
				System.out.println(descripcion);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

package UD02_manejo_de_ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import UD02_objetos.Pelicula;

public class UD2_10 {

	public static void main(String[] args) {

		/**
		 * UD2.10 Crea un paquete de nombre UD2_10 e incluye en él todas las clases
		 * necesarias para construir el siguiente documento XML. Incluye otra clase que
		 * recorra el documento XML creado y visualice sus datos por la consola.
		 */

		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1, "El señor de los anillos.", 2000, "Gran pelicula, mejor actor."));
		peliculas.add(new Pelicula(2, "Cars 4", 2022, "Gran pelicula, mejor coche."));
		peliculas.add(new Pelicula(3, "Toy Story 22.", 2032, "Buen muñeco señora."));

		XStream xs = new XStream();
		try {
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			xs.toXML(peliculas, new FileOutputStream("ficheros/Ev1/UD02/ej10.xml"));
			System.out.println("Creando archivo...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		List<Pelicula> lista_desde_xml;

		try {
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			lista_desde_xml = (List<Pelicula>) xs.fromXML(new FileInputStream("ficheros/Ev1/UD02/ej10.xml"));

			lista_desde_xml.forEach(System.out::println);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}

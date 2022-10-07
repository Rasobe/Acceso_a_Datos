package UD02_repaso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import UD02_objetos.Pelicula;

public class XML_prueba {

	public static void main(String[] args) {
		
		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1, "El señor de los anillos.", 2000, "Gran pelicula, mejor actor."));
		peliculas.add(new Pelicula(2, "Cars 4", 2022, "Gran pelicula, mejor coche."));
		peliculas.add(new Pelicula(3, "Toy Story 22.", 2032, "Buen muñeco señora."));
		
		try {
			
			File f = new File("ficheros/ev1/ud02/repaso_ej10.xml");
			
			// Crear archivo XML
			
			XStream xs = new XStream();
			FileWriter fw = new FileWriter(f);
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			xs.aliasField("año", Pelicula.class, "anyo");
			xs.toXML(peliculas, fw);
			System.out.println("Archivo creado correctamente");
			
			// Leer archivo XML
			
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			xs.aliasField("año", Pelicula.class, "anyo");
			List<Pelicula> lista = (List<Pelicula>) xs.fromXML(new FileInputStream(f));
			
			lista.forEach(System.out::println);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

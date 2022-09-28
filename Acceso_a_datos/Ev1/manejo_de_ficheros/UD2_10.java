package manejo_de_ficheros;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import objetos.Pelicula;

public class UD2_10 {
	
	public static void main(String[] args) {
		
		List<Pelicula> peliculas = new ArrayList<>();
		peliculas.add(new Pelicula(1, "El señor de los anillos.", 2000, "Gran pelicula, mejor actor."));
		peliculas.add(new Pelicula(2, "Cars 4", 2022, "Gran pelicula, mejor coche."));
		peliculas.add(new Pelicula(3, "Toy Story 22.", 2032, "Buen muñeco señora."));
		
		XStream xs = new XStream();
		try {
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			xs.toXML(peliculas, new FileOutputStream("ficheros/Ev1/UD2/ej10.xml"));
			System.out.println("Creando archivo...");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Pelicula> lista_desde_xml;
		
		try {
			xs.alias("Peliculas", List.class);
			xs.alias("Pelicula", Pelicula.class);
			lista_desde_xml = (List<Pelicula>) xs.fromXML(new FileInputStream("ficheros/Ev1/UD2/ej10.xml"));
			
			lista_desde_xml.forEach(System.out::println);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

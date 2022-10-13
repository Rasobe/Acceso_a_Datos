package examen01;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {

	public static void main(String[] args) {

		File f = new File("ficheros/Ev1/Examenes/Ex1/libros.dat");
		ListaLibros listaLibros = new ListaLibros();

		// leer fichero

		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			while (true) {
				listaLibros.add((Libro) ois.readObject());
			}
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (EOFException e) {
			System.out.println("Fin de fichero");
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
		} catch (ClassNotFoundException e) {
			System.out.println("No se encuentra la clase");
		} catch (Exception e) {
			System.out.println("Se ha producido un error");
		}

		// crear archivo xml

		File f2 = new File("ficheros/Ev1/Examenes/Ex1/libros.xml");
		XStream xs = new XStream(new DomDriver("UTF-8"));
		try {
			xs.alias("ListaLibros", ListaLibros.class);
			xs.addImplicitCollection(ListaLibros.class, "lista");
			xs.toXML(listaLibros, new FileOutputStream(f2));
			System.out.println("Archivo XML creado correctamente!");
			
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no encontrado");
		} catch (IOException e) {
			System.out.println("Error al leer el fichero");
		} catch (Exception e) {
			System.out.println("Se ha producido un error");
		}

	}

}

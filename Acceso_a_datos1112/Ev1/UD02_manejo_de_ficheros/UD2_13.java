package UD02_manejo_de_ficheros;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import UD02_objetos.Cliente_13;

public class UD2_13 {

	public static void main(String[] args) {

		/**
		 * UD2.13 Realiza una clase UD2_13 que almacene como objetos en un fichero
		 * ventas.dat los datos básicos de los clientes como son el nombre completo
		 * (String), teléfono (String), dirección, (String), nif (String) y moroso
		 * (String SI/NO). Deberá codificarse para ellos 2 métodos: 
		 * 
		 * 	- Introducir en el fichero anterior los datos de los clientes que se pedirán por teclado y se
		 * irán añadiendo al fichero. El atributo moroso no se incluirá en el fichero
		 * (aun así debe pedirse por teclado). 
		 * 	- Visualizar los datos del fichero.
		 * 
		 * Pista: Modificador transient a la hora de declarar el atributo moroso de la
		 * clase anterior.
		 */

		List<Cliente_13> lista = new ArrayList<>();

		lista.add(new Cliente_13("Raúl Solano Berdiel", "626587641", "Pasaje Arco Iris", "18097052P", "NO"));
		lista.add(new Cliente_13("Christian Ortíz Benamed", "626984542", "Calle Hospital San Jorge", "1805234P", "NO"));
		lista.add(new Cliente_13("Alberto Lorés Bolea", "501654354", "Calle Teruel", "18082352P", "SI"));

		// Creamos fichero con MOROSO CIFRADO

		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("Acceso_a_datos/ficheros/Ev1/UD02/ej13.dat"));
			oos.writeObject(lista);
			oos.close();
			System.out.println("Archivo creado con éxito!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Leer fichero

		List<Cliente_13> lista_sacada_fichero = new ArrayList<>();

		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("Acceso_a_datos/ficheros/Ev1/UD02/ej13.dat"));
			lista_sacada_fichero = (List<Cliente_13>) ois.readObject();
			lista_sacada_fichero.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}

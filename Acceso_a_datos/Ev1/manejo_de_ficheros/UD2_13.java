package manejo_de_ficheros;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import objetos.Cliente;

public class UD2_13 {

	public static void main(String[] args) {
		
		List<Cliente> lista = new ArrayList<>();
		
		lista.add(new Cliente("Raúl Solano Berdiel", "626587641", "Pasaje Arco Iris", "18097052P", "NO"));
		lista.add(new Cliente("Christian Ortíz Benamed", "626984542", "Calle Hospital San Jorge", "1805234P", "NO"));
		lista.add(new Cliente("Alberto Lorés Bolea", "501654354", "Calle Teruel", "18082352P", "SI"));
		
		// Creamos fichero con MOROSO CIFRADO
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Acceso_a_datos/ficheros/Ev1/UD2/ej13.dat"));
			oos.writeObject(lista);
			oos.close();
			System.out.println("Archivo creado con éxito!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Leer fichero
				
		List<Cliente> lista_sacada_fichero = new ArrayList<>();
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Acceso_a_datos/ficheros/Ev1/UD2/ej13.dat"));
			lista_sacada_fichero = (List<Cliente>) ois.readObject();
			lista_sacada_fichero.forEach(System.out::println);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}

package examen01;


import java.util.ArrayList;
import java.util.List;

public class ListaLibros {
	
	private List<Libro> lista = new ArrayList<Libro>();

	public ListaLibros() {
	}

	public void add(Libro libro) {
		lista.add(libro);
	}

	public List<Libro> getListaLibros() {
		return lista;
	}

}

package primero;
// Generated 25-feb-2020 15:22:25 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Familia generated by hbm2java
 */
public class Familia implements java.io.Serializable {

	private String cod;
	private String nombre;
	private Set productos = new HashSet(0);

	public Familia() {
	}

	public Familia(String cod, String nombre) {
		this.cod = cod;
		this.nombre = nombre;
	}

	public Familia(String cod, String nombre, Set productos) {
		this.cod = cod;
		this.nombre = nombre;
		this.productos = productos;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getProductos() {
		return this.productos;
	}

	public void setProductos(Set productos) {
		this.productos = productos;
	}

}
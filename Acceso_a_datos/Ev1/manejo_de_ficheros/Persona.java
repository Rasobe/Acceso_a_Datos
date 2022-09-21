package manejo_de_ficheros;

import java.util.Date;

public class Persona {

	private String nombre;
	private int edad;
	private Date fecha_nac;

	/**
	 * 
	 * @param nombre Nombre de la persona.
	 * @param edad Edad de la persona.
	 * @param fecha_nac Fecha de nacimiento de la persona.
	 */
	public Persona(String nombre, int edad, Date fecha_nac) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.fecha_nac = fecha_nac;
	}

	/**
	 * Obtienes el nombre de la persona.
	 * @return String 
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Pones el nombre a la persona
	 * @param nombre String 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(Date fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", edad=" + edad + ", fecha_nac=" + fecha_nac + "]";
	}

}

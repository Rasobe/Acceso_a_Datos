package objetos;

import java.io.Serializable;

public class Cliente implements Serializable {

	private String nombre_completo;
	private String telefono;
	private String direccion;
	private String nif;
	private transient String moroso;	// con transient lo que hacemos es que al querer serializar la clase, no se serializará

	public Cliente(String nombre_completo, String telefono, String direccion, String nif, String moroso) {
		super();
		this.nombre_completo = nombre_completo;
		this.telefono = telefono;
		this.direccion = direccion;
		this.nif = nif;
		this.moroso = moroso;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getMoroso() {
		return moroso;
	}

	public void setMoroso(String moroso) {
		this.moroso = moroso;
	}

	@Override
	public String toString() {
		return "\nNombre completo: " + nombre_completo + "\nTeléfono=" + telefono + "\nDireccion: " + direccion
				+ "\nNIF=" + nif + ", moroso=" + moroso + "]";
	}

}

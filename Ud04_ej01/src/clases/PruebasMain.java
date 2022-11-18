package clases;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import clases.Socio;

public class PruebasMain {

	public static void main(String[] args) throws SQLException {
		AccesoBdatos abd = new AccesoBdatos();
		
		
		abd.conectar();
		List<Socio> listaSocios = abd.consultaLocalidad("Malaga");
		
		listaSocios.forEach(socio -> {
			System.out.println(socio.getSocioId() + " - " + socio.getNombre() + " - " + socio.getEstatura() + " - " + socio.getEdad() + " - " + socio.getLocalidad());
		});

		
		abd.desconectar();

	}

}

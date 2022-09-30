package UD03_manejo_de_conectores;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import UD03_objetos.Socio;

public class UD03_01_baloncesto {
	
	public static void main(String[] args) {
		
		exportarJson();
		
	}
	
	public static boolean exportarJson() {
		
		AccesoBdatos abd = new AccesoBdatos("baloncesto");
		abd.conectar();
		
		try {
			// con esta consulta sacamos todos los datos de la base de datos de socios.
			PreparedStatement ps = abd.getConnection().prepareStatement("select * from socio");
			// la almacenamos en una tablas que están almacenadas en rs.
			ResultSet rs = ps.executeQuery();
			
			List<Socio> lista = new ArrayList<>();
			
			while (rs.next()) {
				lista.add(new Socio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			
			Gson gson = new Gson();
			FileWriter fw = new FileWriter("ficheros/Ev1/UD03/ej01_baloncesto_socios.json");
			gson.toJson(lista, fw);
			fw.close();
			System.out.println("¡Archivo json creado correctamente!");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JsonIOException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		abd.desconectar();
		
		return false;
	}

}

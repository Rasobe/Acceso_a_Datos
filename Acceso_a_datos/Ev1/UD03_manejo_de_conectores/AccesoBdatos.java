package UD03_manejo_de_conectores;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import UD03_objetos.Socio;

public class AccesoBdatos {
	
	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database;
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url;
	private static String username = "root";
	private static String password = "root";
	
	public Connection conecta;
	
	public AccesoBdatos(String baseDatos) {
		database = baseDatos;
		url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?serverTimezone=Europe/Madrid";
	}

	public void conectar() {
		try {
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, username, password);
			System.out.println("¡Conectado con éxito!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("No se pudo completar la conexión.");
		}
	}
	
	public void crear_fichero() {
		try {
			// con esta consulta sacamos todos los datos de la base de datos de socios.
			PreparedStatement ps = conecta.prepareStatement("select * from socio");
			// la almacenamos en una tablas que están almacenadas en rs.
			ResultSet rs = ps.executeQuery();
			
			List<Socio> lista = new ArrayList<>();
			
			while (rs.next()) {
				lista.add(new Socio(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)));
			}
			
			Gson gson = new Gson();
			FileWriter fw = new FileWriter("Acceso_a_datos/ficheros/Ev1/UD03/ej01_baloncesto_socios.json");
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
	}
	
	public void desconectar(){
		if (conecta !=null) {
			try {
				conecta.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

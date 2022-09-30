package UD03_manejo_de_conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	
	public Connection getConnection() {
		return conecta;
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

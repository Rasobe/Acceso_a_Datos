package UD03_repaso;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class AccesoBdatos {

	/*
	 * rs.next() rs.first() rs.last() rs.absolute(posicion) rs.getRow()
	 */

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database
			+ "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";

	private Connection conecta; 
								

	public void conectar() {
		try {
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, username, password);
			
		} catch (ClassNotFoundException cnf) {
			System.out.println("Clase driver no encontrada");
		} catch (SQLException sqle) {
			System.out.println("Error SQL en la conexion");
		}
	}

	public void desconectar() {
		try {
			if (conecta != null) { 
				conecta.close();
			}
		} catch (SQLException sqle) {
			System.out.println("Error al desconectar");
		}
	}

	
	public int ultimaIdMasUno() {
		int id = 0;
		try {
			PreparedStatement ps = conecta.prepareStatement("select socioID from socio order by socioID desc limit 1");
			ResultSet rs = ps.executeQuery();
			
			
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id + 1;
	}
	
	
	public int anyadirSocio(int socioID, String nombre, int estatura, int edad, String localidad) {
		
		int celdasCambiadas = 0;
		
		try {
			
			PreparedStatement ps = conecta.prepareStatement("insert into socio values (?, ?, ?, ?, ?)");
			ps.setInt(1, socioID);
			ps.setString(2, nombre);
			ps.setInt(3, estatura);
			ps.setInt(4, edad);
			ps.setString(5, localidad);
			celdasCambiadas = ps.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return celdasCambiadas;
	}
	
	public ResultSet obtenerSocioPorId(int id) {
		
		PreparedStatement ps;
		ResultSet rs = null;
		
		try {
			ps = conecta.prepareStatement("SELECT * FROM baloncesto.socio where socioID = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return rs;
	}
	
	public int eliminarSocioPorId(int id) {
		
		PreparedStatement ps;
		int camposBorrados = 0;
		
		try {
			
			ps = conecta.prepareStatement("delete from socio where socioID = ?");
			ps.setInt(1, id);
			camposBorrados = ps.executeUpdate();
			
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return camposBorrados;
	}
	
	public int actualizarSocioPorId(int id, String nombre, int estatura, int edad, String localidad) {
		
		PreparedStatement ps;
		int camposActualizados = 0;
		
		try {
			ps = conecta.prepareStatement("update socio set nombre = ?, estatura = ?, edad = ?, localidad = ? where socioID = ?");
			ps.setString(1, nombre);
			ps.setInt(2, estatura);
			ps.setInt(3, edad);
			ps.setString(4, localidad);			
			ps.setInt(5, id);
			
			camposActualizados = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return camposActualizados;
	}
		
	
}


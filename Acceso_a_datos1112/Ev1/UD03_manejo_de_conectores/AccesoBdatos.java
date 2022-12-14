package UD03_manejo_de_conectores;

import java.sql.CallableStatement;
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

	private Connection conecta; // Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo
								// en el metodo conecta

	public void conectar() {
		try {
			Class.forName(driver);
			conecta = DriverManager.getConnection(url, username, password); // Cuando ejecutemos conectar, iniciaremos
																			// todos los pasos para conectar
			conecta.setAutoCommit(false);
		} catch (ClassNotFoundException cnf) {
			System.out.println("Clase driver no encontrada");
		} catch (SQLException sqle) {
			System.out.println("Error SQL en la conexion");
		}
	}

	public void desconectar() {
		try {
			if (conecta != null) { // Si en conecta hay algo, entonces desconecta. Con esto tenemos un metodo que
									// cierre la conexion
				conecta.setAutoCommit(true);
				conecta.close();
			}
		} catch (SQLException sqle) {
			System.out.println("Error al desconectar");
		}
	}

	// Creamos un metodo que primero mire si la localidad que nos da esta vacia,
	// devuelva un ResultSet con todos los socios.
	// Y si nos manda una localidad que existe, nos devuelva un ResultSet con solo
	// los socios de esa Localidad.
	// Ademas despues contaremos el numero de socios(filas) del ResultSet en la
	// interfaz

	public ResultSet consultaLocalidad(String localidad){
        ResultSet salida = null;
        if(localidad.isEmpty()==false) { //Si localidad es distinto de vacio ejecuta lo de dentro
            try {
//            Statement consulta = conecta.createStatement();
//            ResultSet reg = consulta.executeQuery ("SELECT * FROM socio WHERE localidad like '" + localidad +"'");
                
            CallableStatement consulta = conecta.prepareCall("call llamarPorLocalidad(?)");
            consulta.setString(1, localidad);
            ResultSet reg = consulta.executeQuery();
            	
            if (reg.next()) {//Si hay siguiente quiere decir que ha encontrado coincidencias asi que devuelve el result set de esos socios
                salida = reg;
            }else {//Si no hay siguiente, quiere decir que no ha encontrado coincidencias, en ese caso devuelve null y pondremos que no hay coincidencias
                salida = null;
            }
            }catch(SQLException sqle) {
                System.out.println("Error en la consulta para mostrar socios que coincidan en localidad");
            }
        } else {//Si esta vacio ejecuta consulta que devuelva todos los usuarios
            try {
                Statement consulta = conecta.createStatement();
                ResultSet reg = consulta.executeQuery ("SELECT * FROM socio");
                    
                if (reg.next()) { //Si hay siguiente devolvera un result set con todos los socios
                    salida = reg;
                }else {
                    salida = null; //Si no encuentra resultados, devolvera null (en este caso nunca se vera esto, por que en el unico caso de que devolviera null, seria que no funcionara la conexion, o la consulta este mal, en cuyo caso saltara un error)
                }
                }catch(SQLException sqle) {
                    System.out.println("Error en la consulta para mostrar todos los socios");
                }
        }
        return salida;
    }
	
	public int anyadirSocio(String nombre, int estatura, int edad, String localidad) {
		
		PreparedStatement ps;
		
		try {
			ps = conecta.prepareStatement("insert into socio values (?, ?, ?, ?, ?)");
			ps.setInt(1, ultimaIdDisponible());
			ps.setString(2, nombre);
			ps.setInt(3, estatura);
			ps.setInt(4, edad);
			ps.setString(5, localidad);
			int resultado = ps.executeUpdate();
			conecta.commit();
			return resultado;
		} catch (SQLException e) {
			try {
				conecta.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return 0;
		}

	}	
	
	public int ultimaIdDisponible() {
		int id = 0;
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conecta.prepareStatement("SELECT socioID FROM socio order by socioID desc limit 1");
			rs = ps.executeQuery();
			
			if(rs.next()) {
				id = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id + 1;
	}
	public void eliminarSocio(int id) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conecta.prepareStatement("delete from socio where socioID = ?");
			ps.setInt(1, id);
			ps.executeUpdate();
			conecta.commit();
			JOptionPane.showMessageDialog(null, "Socio eliminado correctamente!", "Eliminado correctamente", JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			try {
				conecta.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "No se pudo eliminar el socio", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actualizarSocio(int id, String nombre, int estatura, int edad, String localidad) {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = conecta.prepareStatement("update socio set nombre = ?, estatura = ?, edad = ?, localidad = ? where socioID = ?");
			ps.setString(1, nombre);
			ps.setInt(2, estatura);
			ps.setInt(3, edad);
			ps.setString(4, localidad);
			ps.setInt(5, id);
			int resultado = ps.executeUpdate();
			conecta.commit();
			if (resultado >= 1) {
				JOptionPane.showMessageDialog(null, "Socio con id " + id + " actualizado correctamente", "Actualizaci??n exitosa", JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "No se ha podido actualizar ning??n socio", "Error al actualizar", JOptionPane.ERROR_MESSAGE);				
			}
			
		} catch (SQLException e) {
			try {
				conecta.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}

//probar la logica en el main antes

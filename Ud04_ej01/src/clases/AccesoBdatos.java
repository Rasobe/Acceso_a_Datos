package clases;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import clases.Socio;


public class AccesoBdatos {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String database = "baloncesto";
	private static String hostname = "localhost";
	private static String port = "3306";
	private static String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database
			+ "?serverTimezone=Europe/Madrid";
	private static String username = "root";
	private static String password = "root";

	private Connection conecta; // Atributo conexion, muy importante. Solo declarado sin valor, para utilizarlo
	
	SessionFactory sesion = SessionFactoryUtil.getSessionFactory();// en el metodo conecta
	Session session;
	
	public void conectar() {
		session = sesion.openSession();
	}

	public void desconectar() {
		session.close();
	}
	
	
	public List<Socio> consultaLocalidad(String localidad){
        Query q = session.createQuery("from Socio as soc where soc.localidad = :localidad");
        q.setString("localidad", localidad);
        List<Socio> lista = q.list();
        
        return lista;
    }
	
	public int anyadirSocio(String nombre, int estatura, int edad, String localidad) {
		
		try {
			Transaction t = session.beginTransaction();
			
			Query q2 = session.createQuery("SELECT socioId from Socio as soc order by socioId desc");
			q2.setMaxResults(1); // LO MISMO QUE LIMIT
			int ultimaPosicion = (int) q2.uniqueResult() + 1;
			Socio s = new Socio(ultimaPosicion, nombre, estatura, edad, localidad);
			session.save(s);
			
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		return 1;
	}	
	
	
	public void eliminarSocio(int id) {
		
		Transaction t = session.beginTransaction();
		Socio socio = (Socio) session.get(Socio.class, id);
		
		if (socio == null) {
			JOptionPane.showMessageDialog(null, "Error al borrar socio.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			session.delete(socio);
			JOptionPane.showMessageDialog(null, "Socio borrado correctamente.", "Borrado exitoso", JOptionPane.INFORMATION_MESSAGE);
		}
		
		t.commit();
		
	}
	
	public void actualizarSocio(int id, String nombre, int estatura, int edad, String localidad) {
		
		Transaction t = session.beginTransaction();
		Socio socio = (Socio) session.get(Socio.class, id);
		if (socio == null) {
			JOptionPane.showMessageDialog(null, "Error al actualizar socio.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Socio actualizado correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);
			socio.setNombre(nombre);
			socio.setEstatura(estatura);
			socio.setEdad(edad);
			socio.setLocalidad(localidad);
			session.update(socio);
			
			t.commit();
			
		}
	}

}

//probar la logica en el main antes

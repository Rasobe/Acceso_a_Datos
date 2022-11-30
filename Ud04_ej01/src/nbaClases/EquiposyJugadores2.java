package nbaClases;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.SessionFactoryUtil;

public class EquiposyJugadores2 {
	
	public static void main(String[] args) {
		
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session s = sf.openSession();
		
		String obtenerEquipos = "select e.nombre FROM Equipos as e order by e.nombre";
		String equiposJugadoresEstadisticas = "select j.codigo, j.nombre, round(avg(es.puntosPorPartido),2) from Jugadores as j, Estadisticas es where j.equipos.nombre = :equipo and j.codigo = es.jugadores.codigo order by j.nombre";
		
		List<String> lista = (List<String>) s.createQuery(obtenerEquipos).list(); 
		
		Query q;
		
		for (String nombreEquipo: lista) {
			System.out.println("=================================================");
			System.out.println("Equipo: " + nombreEquipo);
			q = s.createQuery(equiposJugadoresEstadisticas);
			q.setString("equipo", nombreEquipo);
			List<Object[]> datos = q.list();
			System.out.println(datos.size());
			
		}
		
	}

}

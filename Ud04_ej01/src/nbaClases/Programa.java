package nbaClases;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.SessionFactoryUtil;

public class Programa {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		System.out.print("Introduzca el código del jugador para mostrar sus estadísticas: ");
		int codigo = s.nextInt();

		// CONECTAR
		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session session = sf.openSession();

		String consultaTemporadaJugadorPorId = "FROM jugadores join estadisticas on jugadores.codigo = estadisticas.jugador where jugador.codigo = :id";

		Query q = session.createQuery(consultaTemporadaJugadorPorId);
		q.setInteger("id", codigo);
		
		List<Estadisticas> lista = q.list();
		
		lista.forEach(System.out::println);
		
		
		Jugadores jugador = (Jugadores) session.get(Jugadores.class, codigo);
		
		
		
		System.out.println(jugador.getNombre());

		// DESCONECTAR
		sf.close();
	}

}

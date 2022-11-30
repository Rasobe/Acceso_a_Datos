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

		String consultaTemporadaJugadorPorId = "FROM Jugadores j, Estadisticas e where j.codigo = e.jugadores.codigo and j.codigo = :id";

		Query q = session.createQuery(consultaTemporadaJugadorPorId);
		q.setInteger("id", codigo);
		
		List<Object[]> lista = q.list();
		
		for (int i = 0; i < lista.size(); i++) {
			Object[] fila = lista.get(i);
			Jugadores j = (Jugadores) fila[0];
			Estadisticas e = (Estadisticas) fila[1];
			
			if (i == 0) {
				System.out.printf("DATOS DEL JUGADOR: %d%nNombre: %s%nEquipo: %s%nTemporada\tPtos\tAsis\tTap\tReb%n", j.getCodigo(), j.getNombre(), j.getEquipos().getNombre());
				System.out.println("=================================================");
			}
			System.out.printf("%s\t\t%.1f\t%.1f\t%.1f\t%.1f %n", e.getId().getTemporada(), e.getPuntosPorPartido(), e.getAsistenciasPorPartido(), e.getTaponesPorPartido(), e.getRebotesPorPartido());
			
		}
		System.out.println("=================================================");
		System.out.println("Num de registros: " + lista.size());
		System.out.println("=================================================");

		// DESCONECTAR
		session.close();
	}

}

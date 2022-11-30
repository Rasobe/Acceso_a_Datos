package nbaClases;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import clases.SessionFactoryUtil;

public class EquiposyJugadores {

	public static void main(String[] args) {

		SessionFactory sf = SessionFactoryUtil.getSessionFactory();
		Session s = sf.openSession();

		// FORMA 1
		
		String consultaJugadoresEquipo = "FROM Equipos as e order by e.nombre";

		Query q = s.createQuery(consultaJugadoresEquipo);
		List<Equipos> lista = q.list();

		float puntosPartidos;
		float contador;

		System.out.println("Numero de equipos: " + lista.size());
		for (Equipos e : lista) {
			System.out.println("=================================================");
			System.out.println("Equipo: " + e.getNombre());
			Set jugadores = e.getJugadoreses();
			for (Object obj : jugadores) {
				puntosPartidos = 0;
				contador = 0;
				Jugadores j = (Jugadores) obj;
				System.out.print(j.getCodigo() + ", " + j.getNombre() + ": ");
				Set estaditicas = j.getEstadisticases();
				for (Object est : estaditicas) {
					Estadisticas estadisticasJugador = (Estadisticas) est;
					puntosPartidos += estadisticasJugador.getPuntosPorPartido();
					contador++;
					if (contador == estaditicas.size()) {
						DecimalFormat f = new DecimalFormat("###.##");
						System.out.println(f.format(puntosPartidos / contador));
					}
				}
			}
		}

	}

}

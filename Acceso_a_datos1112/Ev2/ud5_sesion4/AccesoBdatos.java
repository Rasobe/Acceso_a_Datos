package ud5_sesion4;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//
// Alberto Carrera Mart�n - Abril 2020
//

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em;
	public static List<Socio> listaSocios = new ArrayList<>();
	public static int socioRecogido;

	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/socios.odb");
		em = emf.createEntityManager();
	}

	public void desconectar() {
		em.close();
		emf.close();
	}

	public void recorrerTodaLista() {
		listaSocios = em.createQuery("select s from Socio s").getResultList();
		for (Socio socio : listaSocios) {
			System.out.println(socio);
		}
	}
	
	public List<Socio> listaSociosPorLocalidad(String localidad) {
		socioRecogido = 0;
		listaSocios = em.createQuery("select s from Socio s where s.localidad like :localidad").setParameter("localidad", localidad).getResultList();
		return listaSocios;
	}
	
	public Socio pasarAlSiguiente() {
		System.out.println(socioRecogido);
		if (socioRecogido < listaSocios.size()) {
			socioRecogido++;
		}
		return listaSocios.get(socioRecogido);
	}

} // de la clase

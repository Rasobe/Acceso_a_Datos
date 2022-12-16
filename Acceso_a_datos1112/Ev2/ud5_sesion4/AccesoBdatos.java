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

	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/socios.odb");
		em = emf.createEntityManager();
	}

	public void desconectar() {
		em.close();
		emf.close();
	}

	public void recorrerTodaLista() {
		List<Socio> lista = em.createQuery("select s from Socio s").getResultList();
		for (Socio socio : lista) {
			System.out.println(socio);
		}
	}
	
	public List<Socio> listaSociosPorLocalidad(String localidad) {
		return em.createQuery("select s from Socio s where s.localidad like :localidad").setParameter("localidad", localidad).getResultList();
	}

} // de la clase

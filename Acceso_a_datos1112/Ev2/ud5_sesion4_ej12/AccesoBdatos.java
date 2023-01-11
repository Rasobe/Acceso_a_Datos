package ud5_sesion4_ej12;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
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
		listaSocios = em.createQuery("select s from Socio s where s.localidad like :localidad")
				.setParameter("localidad", localidad).getResultList();
		listaSocios.forEach(System.out::println);
		return listaSocios;
	}

	public Socio pasarAlSiguiente() {
		System.out.println(socioRecogido);
		if (socioRecogido < listaSocios.size() - 1) {
			socioRecogido++;
		}
		return listaSocios.get(socioRecogido);
	}

	public Socio pasarAlAnterior() {
		System.out.println(socioRecogido);
		if (socioRecogido > 0) {
			socioRecogido--;
		}
		return listaSocios.get(socioRecogido);
	}

	public void anyadirSocio(Socio s) {
		s.setSocioId(ultimaIdDisponible() + 1);
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		
		JOptionPane.showMessageDialog(null, "Se ha añadido el socio correctamente!");
		
	}

	public int eliminarSocio(int id) {
		em.getTransaction().begin();
		int resultado = em.createQuery("delete from Socio where socioId = :id").setParameter("id", id).executeUpdate();
		em.getTransaction().commit();
		
		JOptionPane.showMessageDialog(null, "Socio eliminado correctamente!");
		
		return resultado;
	}
	
	
	
	public int ultimaIdDisponible() {
		return (int) em.createQuery("select max(socioId) from Socio").getSingleResult();
	}

	public int actualizarSocio(Socio s) {
		em.getTransaction().begin();
		int resultado = em.createQuery("update Socio set nombre = :nombre, estatura = :estatura, edad = :edad, localidad = :localidad where socioId = :id").setParameter("nombre", s.getNombre()).setParameter("estatura", s.getEstatura()).setParameter("edad", s.getEdad()).setParameter("localidad", s.getLocalidad()).setParameter("id", s.getSocioId()).executeUpdate();
		em.getTransaction().commit();

		JOptionPane.showMessageDialog(null, "Socio actualizado correctamente!");
		
		return resultado;
	}

} // de la clase

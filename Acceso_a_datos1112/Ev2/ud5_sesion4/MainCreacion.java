package ud5_sesion4;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//
//Alberto Carrera Mart�n - Abril 2020
//
public class MainCreacion {

	public static void main(String[] args) throws ParseException {
		Socio socio1 = new Socio(1, "Alberto", 180, 45, "Huesca");
		Socio socio2 = new Socio(2, "Rasobu", 104, 5, "Madrid");
		Socio socio3 = new Socio(3, "Miguel", 167, 25, "Huesca");
		Socio socio4 = new Socio(4, "Josemi", 120, 15, "Zaragoza");
		Socio socio5 = new Socio(5, "Santifans", 154, 55, "Madrid");
		Socio socio6 = new Socio(6, "Oskar", 143, 65, "Zaragoza");
		Socio socio7 = new Socio(7, "Pop", 198, 32, "Zaragoza");
		Socio socio8 = new Socio(8, "Mario", 167, 86, "Madrid");
		Socio socio9 = new Socio(9, "Alejandro", 184, 9, "Madrid");
		Socio socio10 = new Socio(10, "Calvieiro", 164, 23, "Zaragoza");
		Socio socio11 = new Socio(11, "Lordenzo", 132, 43, "Huesca");
		Socio socio12 = new Socio(12, "Perezoso", 164, 23, "Madrid");
		Socio socio13 = new Socio(13, "David Doble P", 145, 15, "Huesca");
		Socio socio14 = new Socio(14, "Carlos Ajedrez", 190, 115, "Barcelona");
		Socio socio15 = new Socio(15, "Lurdes", 130, 90, "Zaragoza");
		Socio socio16 = new Socio(16, "Darzaquer", 140, 100, "Zaragoza");

		//
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/socios.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(socio1);
		em.persist(socio2);
		em.persist(socio3);
		em.persist(socio4);
		em.persist(socio5);
		em.persist(socio6);
		em.persist(socio7);
		em.persist(socio8);
		em.persist(socio9);
		em.persist(socio10);
		em.persist(socio11);
		em.persist(socio12);
		em.persist(socio13);
		em.persist(socio14);
		em.persist(socio15);
		em.persist(socio16);
		//
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}

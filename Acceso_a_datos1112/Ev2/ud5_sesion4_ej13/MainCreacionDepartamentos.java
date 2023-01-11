package ud5_sesion4_ej13;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//
//Alberto Carrera Mart�n - Abril 2020
//
public class MainCreacionDepartamentos {

	public static void main(String[] args) throws ParseException {

		Departamentos d1 = new Departamentos((byte) 10, "Informatica", "Huesca", new HashSet<Empleados>());
		Departamentos d2 = new Departamentos((byte) 20, "Marketing", "Madrid", new HashSet<Empleados>());
		Departamentos d3 = new Departamentos((byte) 30, "Recursos humanos", "Zaragoza", new HashSet<Empleados>());
		Departamentos d4 = new Departamentos((byte) 40, "Administracion", "Huesca", new HashSet<Empleados>());

		Empleados e1 = new Empleados((short) 1000, d1, "Solano", "Presidente", null, new Date(),
				(float) 1200.12, (float) 53.2);
		Empleados e2 = new Empleados((short) 1001, d2, "Aleta", "Ministro", (short) 1000, new Date(), (float) 1600.53,
				(float) 5.2);
		Empleados e3 = new Empleados((short) 1002, d3, "Bitrian", "Maestro", (short) 1000, new Date(), (float) 1000.23,
				(float) 56.2);
		Empleados e4 = new Empleados((short) 1003, d2, "Berdiel", "Agricultor", (short) 1001, new Date(),
				(float) 1200.43, (float) 53.2);
		Empleados e5 = new Empleados((short) 1004, d2, "Escudero", "Masajista", (short) 1005, new Date(),
				(float) 2100.12, (float) 53.2);
		Empleados e6 = new Empleados((short) 1005, d1, "Ortíz", "Programador", (short) 1000, new Date(),
				(float) 1900.312, (float) 53.2);
		Empleados e7 = new Empleados((short) 1006, d2, "Blanco", "Profesor Ingles", (short) 1009, new Date(),
				(float) 1200.12, (float) 53.2);
		Empleados e8 = new Empleados((short) 1007, d1, "García", "Masajista", (short) 1001, new Date(), (float) 1800.12,
				(float) 53.2);
		Empleados e9 = new Empleados((short) 1008, d1, "Arnal", "Programador", (short) 1001, new Date(), (float) 900.12,
				(float) 53.2);
		Empleados e10 = new Empleados((short) 1009, d3, "De la Losa", "Masajista", (short) 1001, new Date(),
				(float) 1500.12, (float) 53.2);
		Empleados e11 = new Empleados((short) 1010, d4, "Fuentes", "Ministro", (short) 1001, new Date(),
				(float) 2500.32, (float) 53.2);
		Empleados e12 = new Empleados((short) 1011, d4, "Soriano", "Agricultor", (short) 1001, new Date(),
				(float) 3400.12, (float) 53.2);
		Empleados e13 = new Empleados((short) 1012, d3, "Piñeiro", "Agricultor", (short) 1015, new Date(),
				(float) 8000.12, (float) 53.2);
		Empleados e14 = new Empleados((short) 1013, d1, "Pérez", "Programador", (short) 1000, new Date(),
				(float) 1700.12, (float) 53.2);
		Empleados e15 = new Empleados((short) 1014, d1, "Rámirez", "Programador", (short) 1000, new Date(),
				(float) 1900.12, (float) 53.2);
		Empleados e16 = new Empleados((short) 1015, d1, "Barón", "Programador", (short) 1000, new Date(),
				(float) 1800.12, (float) 53.2);

		//
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("db/empleadoses.odb");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.persist(d1);
		em.persist(d2);
		em.persist(d3);
		em.persist(d4);
		em.persist(e1);
		em.persist(e2);
		em.persist(e3);
		em.persist(e4);
		em.persist(e5);
		em.persist(e6);
		em.persist(e7);
		em.persist(e8);
		em.persist(e9);
		em.persist(e10);
		em.persist(e11);
		em.persist(e12);
		em.persist(e13);
		em.persist(e14);
		em.persist(e15);
		em.persist(e16);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}

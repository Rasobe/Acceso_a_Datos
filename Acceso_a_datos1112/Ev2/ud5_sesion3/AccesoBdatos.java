package ud5_sesion3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
//
// Alberto Carrera Mart�n - Abril 2020
//

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em;

	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleados.odb");
		em = emf.createEntityManager();
	}

	public void desconectar() {
		em.close();
		emf.close();
	}

	public DepartamentoEntity buscarDepartamento(int numDepartamento) {
		return em.find(DepartamentoEntity.class, numDepartamento);
	}// de m�todo buscarDepartamento
		//

	@SuppressWarnings("deprecation")
	public void imprimirDepartamento(int numDepartamento) {
		DepartamentoEntity d = buscarDepartamento(numDepartamento);
		if (d == null)
			System.out.println("No existe el Departamento " + numDepartamento);
		else {
			Set<EmpleadoEntity> empleados = d.getEmpleados();
			String datos = "Datos del departamento " + numDepartamento + ": ";
			datos += "Nombre: " + d.getNombre() + " - Localidad: " + d.getLocalidad() + "\n";
			if (empleados.isEmpty())
				datos += "No tiene empleados en este momento";
			else {
				datos += "Lista de empleados" + "\n";
				datos += "*******************";
			}
			for (EmpleadoEntity empleado : empleados) {
				datos += "\nN�mero de empleado: " + empleado.getEmpnoId() + "\n";
				datos += "Nombre: " + empleado.getNombre() + "\n";
				datos += "Oficio: " + empleado.getOficio() + "\n";
				if (empleado.getDirId() == null)
					datos += "Jefe: No tiene" + "\n";
				else
					datos += "Jefe: " + empleado.getDirId().getNombre() + "\n";
				datos += "A�o de alta: " + (empleado.getAlta().getYear() + 1900) + "\n";
				datos += "Salario: " + empleado.getSalario() + "\n";
				if (empleado.getComision() == null)
					datos += "Comisi�n: No tiene" + "\n";
				else
					datos += "Comisi�n: " + empleado.getComision() + "\n";
			}

			System.out.println(datos);
		}
	} // de m�todo imprimirDepartamento

	public boolean insertarDepartamento(DepartamentoEntity d) {
		if (buscarDepartamento(d.getDptoId()) != null)
			return false;
		em.getTransaction().begin();
		em.persist(d);
		em.getTransaction().commit();
		return true;
	} // de insertarDepartamento

	public boolean modificarDepartamento(DepartamentoEntity d) {
		DepartamentoEntity departamentoBuscado = buscarDepartamento(d.getDptoId());
		if (departamentoBuscado == null)
			return false;
		em.getTransaction().begin();
		departamentoBuscado.setNombre(d.getNombre());
		departamentoBuscado.setLocalidad(d.getLocalidad());
		em.persist(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento

	// Si tiene empleados lo borrar�a igual, dejando en estos el n�mero de
	// Departamento
	// pero el resto de los atributos del departamento a null. Por eso no dejo
	// borrar

	public boolean borrarDepartamentoUbu(int numDepartamento) {
		DepartamentoEntity departamentoBuscado = buscarDepartamento(numDepartamento);
		if (departamentoBuscado == null || !departamentoBuscado.getEmpleados().isEmpty())
			return false;
		em.getTransaction().begin();
		em.remove(departamentoBuscado);
		em.getTransaction().commit();
		return true;
	} // de modificarDepartamento

	public void demoJPQL() {

		Query q1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d");
		System.out.println("Total Departamentos: " + q1.getSingleResult());
		//
		TypedQuery<Long> tq1 = em.createQuery("SELECT COUNT(d) FROM DepartamentoEntity d", Long.class);
		System.out.println("Total Departamentos: " + tq1.getSingleResult());
		//
		TypedQuery<DepartamentoEntity> tq2 = em.createQuery("SELECT d FROM DepartamentoEntity d",
				DepartamentoEntity.class);
		List<DepartamentoEntity> l2 = tq2.getResultList();
		for (DepartamentoEntity r2 : l2) {
			System.out.println("Nombre :  " + r2.getNombre() + ", Localidad: " + r2.getLocalidad());
		}
		//
		TypedQuery<Object[]> tq3 = em.createQuery("SELECT d.nombre, d.localidad FROM DepartamentoEntity  d",
				Object[].class);
		List<Object[]> l3 = tq3.getResultList();
		for (Object[] r3 : l3) {
			System.out.println("Nombre :  " + r3[0] + ", Localidad: " + r3[1]);
		}
		// */
		TypedQuery<Object[]> tq4 = em.createQuery(
				"SELECT d.nombre, d.localidad FROM DepartamentoEntity d" + " WHERE d.dptoId != :n", Object[].class);
		tq4.setParameter("n", 10);
		List<Object[]> l4 = tq4.getResultList();
		for (Object[] r4 : l4) {
			System.out.println("Nombre :  " + r4[0] + ", Localidad: " + r4[1]);
		}

		// EJERCICIO 8

	}// de demoJPQL
//--------------------------------------------------------------------------------------------------------------

	public void ejercicio01() {
		List<Object[]> listaEmpleados = em.createQuery("select nombre, alta from EmpleadoEntity").getResultList();
		for (Object[] o : listaEmpleados) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio02() {
		List<Object[]> listaEmpleados = em.createQuery("select nombre, alta from EmpleadoEntity").getResultList();
		for (Object[] o : listaEmpleados) {
			if (o[0].toString().toLowerCase().contains("carrera")) {
				System.out.println(o[0] + " - " + o[1]);
			}
		}
	}

	public void ejercicio03() {
		List<Object[]> lista = em.createQuery(
				"select nombre, oficio, departamento.nombre from EmpleadoEntity where departamento.nombre like 'I+D' and oficio like 'Empleado'")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}
	}

	public void ejercicio04() {
		List<Object[]> lista = em.createQuery("select nombre, alta from EmpleadoEntity where year(alta) >= 2003")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio05() {
		List<Object[]> lista = em.createQuery(
				"select departamento.nombre, nombre from EmpleadoEntity where departamento.nombre is not null order by departamento.nombre")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio06() {
		List<Object[]> lista = em.createQuery(
				"select departamento.nombre, count(nombre), sum(salario), max(salario) from EmpleadoEntity group by departamento.nombre")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
		}
	}

	public void ejercicio07() {
		List<Object[]> lista = em.createQuery(
				"select departamento.nombre, count(nombre), sum(salario), max(salario) from EmpleadoEntity group by departamento.nombre having count(nombre) >= 5")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2] + " - " + o[3]);
		}
	}

	public void ejercicio08() {
		List<Object[]> lista = em.createQuery(
				"select nombre, dirId.nombre, departamento.dptoId from EmpleadoEntity where dirId.nombre is not null")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}
	}

	public void ejercicio09() {
		List<Object[]> lista = em.createQuery(
				"select departamento.nombre, count(nombre) from EmpleadoEntity group by departamento.nombre having count(nombre) > 0")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio09_2() {
		List<Object[]> lista = em
				.createQuery("select d.nombre, count(e) from DepartamentoEntity d join d.empleados e group by d.nombre")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio10() {
		List<Object[]> lista = em
				.createQuery(
						"select d.nombre, count(e) from DepartamentoEntity d left join d.empleados e group by d.nombre")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio11() {
		List<Object[]> lista = em.createQuery(
				"select departamento.dptoId, nombre, salario from EmpleadoEntity order by departamento.dptoId desc, salario asc")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1] + " - " + o[2]);
		}
	}

	public void ejercicio12() {
		List<Object[]> lista = em.createQuery("select empnoId, nombre from EmpleadoEntity where dirId is null")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public void ejercicio13() {
		List<Object[]> lista = em
				.createQuery("select dptoId, nombre from DepartamentoEntity where empleados.empnoId = 1039")
				.getResultList();
		for (Object[] o : lista) {
			System.out.println(o[0] + " - " + o[1]);
		}
	}

	public int incrementarSalario(int cantidad) {
		em.getTransaction().begin();
		int numActualizaciones = em.createQuery("update EmpleadoEntity set salario = salario + :salario")
				.setParameter("salario", cantidad).executeUpdate();
		em.getTransaction().commit();
		return numActualizaciones;
	}

	public int incrementarSalarioOficio(String oficio, int cantidad) {
		em.getTransaction().begin();
		int numActualizaciones = em
				.createQuery("update EmpleadoEntity set salario = salario + :salario where oficio like :oficio")
				.setParameter("salario", cantidad).setParameter("oficio", oficio).executeUpdate();
		em.getTransaction().commit();
		return numActualizaciones;
	}

	public int incrementarSalarioDepartamento(int numDepartamento, int cantidad) {
		em.getTransaction().begin();
		int numActualizaciones = em.createQuery(
				"update EmpleadoEntity e set e.salario = e.salario + :salario where e.departamento.getDptoId() = :numDepartamento")
				.setParameter("salario", cantidad).setParameter("numDepartamento", numDepartamento).executeUpdate();
		em.getTransaction().commit();
		return numActualizaciones;
	}

	public int borrarEmpleado(int numEmpleado) {
		em.getTransaction().begin();
		int numEliminaciones = em.createQuery("delete from EmpleadoEntity where empnoId = :id")
				.setParameter("id", numEmpleado).executeUpdate();
		em.getTransaction().commit();
		return numEliminaciones;
	}

	public int borrarDepartamento(int numDepartamento) {
		em.getTransaction().begin();
		int numEliminaciones = em.createQuery("delete from DepartamentoEntity where dptoId = :id")
				.setParameter("id", numDepartamento).executeUpdate();
		em.getTransaction().commit();
		return numEliminaciones;
	}

} // de la clase

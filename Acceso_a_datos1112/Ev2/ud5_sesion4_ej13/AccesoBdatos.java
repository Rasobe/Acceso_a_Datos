package ud5_sesion4_ej13;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import com.objectdb.o.JOP;
//
// Alberto Carrera Mart�n - Abril 2020
//

public class AccesoBdatos {
	private EntityManagerFactory emf;
	private EntityManager em;
	public static List<Empleados> listaEmpleados = new ArrayList<>();
	public static List<Departamentos> listaDepartamentos = new ArrayList<>();

	public void conectar() {
		emf = Persistence.createEntityManagerFactory("db/empleadoses.odb");
		em = emf.createEntityManager();
	}

	public void desconectar() {
		em.close();
		emf.close();
	}

	public void insertarEmpleado(Empleados e) {
		if (em.find(Empleados.class, e.getEmpNo()) != null) {
			JOptionPane.showMessageDialog(null, "No se puede insertar un usuario con la misma ID.", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			em.getTransaction().begin();
			em.persist(e);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Empleado insertado exitosamente!", "Insercion exitosa",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void eliminarEmpleado(short id) {

		Empleados e = em.find(Empleados.class, id);
		if (e == null) {
			JOptionPane.showMessageDialog(null, "No se puede borrar un Empleado que no existe!", "Error",
					JOptionPane.ERROR_MESSAGE);
		} else {
			em.getTransaction().begin();
			em.remove(e);
			em.getTransaction().commit();
			JOptionPane.showMessageDialog(null, "Empleado eliminado exitosamente!", "Eliminacion exitosa",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public Empleados seleccionarEmpleadoPorId(short id) {
		Empleados e = em.find(Empleados.class, id);
		if (e == null) {
			JOptionPane.showMessageDialog(null, "No se puede borrar un Empleado que no existe!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} else {
			return e;
		}
	}
	
	public Departamentos recogerDepartamentoPorId(short id) {
		return em.find(Departamentos.class, id);
	}
	
	public Empleados recogerEmpleadoPorId(short id) {
		return em.find(Empleados.class, id);
	}
	
	public List<Departamentos> listaDepartamentos() {
		return em.createQuery("select d from Departamentos d").getResultList();
	}

	public List<Empleados> listaDirectores() {
		return em.createQuery("select e from Empleados e where e.oficio like 'Presidente'").getResultList();
	}
	
	public void actualizarEmpleado(Empleados e) {
		Empleados empleadoAcambiar = em.find(Empleados.class, e.getEmpNo());
		
		if (empleadoAcambiar == null) {
			JOptionPane.showMessageDialog(null, "Empleado para actualizar no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			em.getTransaction().begin();
			
			empleadoAcambiar.setApellido(e.getApellido());
			empleadoAcambiar.setComision(e.getComision());
			empleadoAcambiar.setDepartamentos(e.getDepartamentos());
			empleadoAcambiar.setDir(e.getDir());
			empleadoAcambiar.setFechaAlt(e.getFechaAlt());
			empleadoAcambiar.setOficio(e.getOficio());
			empleadoAcambiar.setSalario(e.getSalario());
			
			em.getTransaction().commit();
			
			JOptionPane.showMessageDialog(null, "Empleado actualizado con exito!", "Actualizacion realizada con exito", JOptionPane.INFORMATION_MESSAGE);
		}
		
	}

} // de la clase

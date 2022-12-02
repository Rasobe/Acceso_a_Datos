package prueba;

import java.util.HashSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import primero.Producto;
import primero.SessionFactoryUtil;
import primero.Tienda;

//

public class AccesoBdatos {
	private SessionFactory sesion = SessionFactoryUtil.getSessionFactory();
	private Session session = sesion.openSession();

	public void conectar() {
		sesion = SessionFactoryUtil.getSessionFactory();
		session = sesion.openSession();
	}

	public void desconectar() {
		session.close();
		sesion.close();
		System.exit(0);
	}

	public int recogerUlitmoCodigoDisponibleTienda() {
		Query q = session.createQuery("SELECT cod FROM Tienda order by cod desc");
		q.setMaxResults(1); // LO MISMO QUE LIMIT
		return (int) q.uniqueResult() + 1;
	}

	// importar nueva tienda
	public boolean pregunta1(String nombre, String telefono) {

		Transaction tx = null;
		if (nombre == null || nombre.isEmpty()) {
			System.out.println("No puede haber un nombre de tienda nulo o vacío.");
			return false;
		} else {
			try {
				tx = session.beginTransaction();
				Tienda t = new Tienda(nombre, telefono, new HashSet());
				session.save(t);
				tx.commit();
				return true;
			} catch (Exception e) {
				System.out.println("Error al insertar");
				tx.rollback();
				return false;
			}
		}
	}

	// actualizar telefono tienda
	public boolean pregunta2(int codigo, String telefono) {
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Tienda t = (Tienda) session.load(Tienda.class, codigo);

			if (t != null) {
				t.setTlf(telefono);
				tx.commit();
				return true;
			} else {
				System.out.println("La tienda no existe.");
				tx.rollback();
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error al actualizar campo teléfono.");
			tx.rollback();
			return false;
		}
	}

	public long pregunta3() {
		return (long) session.createQuery("select count(*) from Familia").uniqueResult();
	}

	public void pregunta4(String codigoP, int codigoT) {
		if (session.createQuery(
				"select s.unidades from Producto p, Stock s, Tienda t where p.cod = s.producto.cod and s.tienda.cod = t.cod and p.cod = :pCod and t.cod = :tCod")
				.setString("pCod", codigoP).setInteger("tCod", codigoT).uniqueResult() == null) {
			System.out.println(0);
		} else {
			System.out.println(session.createQuery(
					"select s.unidades from Producto p, Stock s, Tienda t where p.cod = s.producto.cod and s.tienda.cod = t.cod and p.cod = :pCod and t.cod = :tCod")
					.setString("pCod", codigoP).setInteger("tCod", codigoT).uniqueResult());
		}
	}

	public void pregunta5() {
		System.out.println(
				"Productos distintos: " + session.createQuery("select count(*) from Producto").uniqueResult() + "\n");

		List<Object[]> listaProductos = session.createQuery("select p.nombreCorto, count(s.tienda), s.unidades "
				+ "from Producto p, Stock s where p.cod = s.producto group by p.nombreCorto order by p.nombreCorto")
				.list();

		for (Object[] o : listaProductos) {
			System.out.println("Producto: " + o[0] + ", disponible en " + o[1] + " tienda(s), total Unidades " + o[2]);
		}

	}

	public void pregunta6() {

		List<Producto> lista = session.createQuery("from Producto").list();

		System.out.println("Producto (nombre corto) -\tPrecio -\tFamilia ");
		for (Producto producto : lista) {
			if (producto.getStocks().isEmpty()) {
				System.out.println(producto.getNombreCorto() + "\t" + producto.getPvp() + "\t" + producto.getFamilia().getNombre());
			}
		}
	}

} // de la clase AccesoBdatos

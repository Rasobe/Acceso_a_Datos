package ud5_sesion4;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConsultaSocio extends JFrame {

	Container contenedor;
	JButton botonAnterior, botonSiguiente, botonBuscar, botonAnyadir, botonEliminar, botonActualizar;
	JTextField cajaId, cajaNombre, cajaEstatura, cajaEdad, cajaLocalidad, cajaBuscar;
	JLabel etiqId, etiqNombre, etiqEstatura, etiqEdad, etiqLocalidad, etiqNumSociosLista, etiqCm, etiqAnyos;
	public static List<Socio> listaSociosPorLocalidad;

	public ConsultaSocio() {
		this.setTitle("Busqueda de Socios por localidad");
		contenedor = this.getContentPane();
		contenedor.setLayout(null);

		// Ponemos nombre e inicializamos botones, cajas y etiquetas

		cajaId = new JTextField();
		cajaNombre = new JTextField();
		cajaEstatura = new JTextField();
		cajaEdad = new JTextField();
		cajaLocalidad = new JTextField();
		cajaBuscar = new JTextField();

		etiqId = new JLabel("Socio");
		etiqNombre = new JLabel("Nombre");
		etiqEstatura = new JLabel("Estatura");
		etiqEdad = new JLabel("Edad");
		etiqLocalidad = new JLabel("Localidad");
		etiqNumSociosLista = new JLabel(); // Le pondremos texto despues, cuando mostremos el conteo
		etiqCm = new JLabel("cm.");
		etiqAnyos = new JLabel("años");

		// A�adimos los elementos al contenedor y lo posicionamos
		botonAnterior = new JButton("Anterior");
		contenedor.add(botonAnterior);
		botonAnterior.setBounds(120, 295, 85, 20);
		botonAnterior.addActionListener(new OyenteBoton());
		botonAnterior.setEnabled(false);

		botonSiguiente = new JButton("Siguiente");
		contenedor.add(botonSiguiente);
		botonSiguiente.setBounds(275, 295, 90, 20);
		botonSiguiente.addActionListener(new OyenteBoton());
		botonSiguiente.setEnabled(false);

		botonBuscar = new JButton("Buscar");
		contenedor.add(botonBuscar);
		botonBuscar.setBounds(345, 95, 85, 20);
		botonBuscar.addActionListener(new OyenteBoton());

		botonAnyadir = new JButton("Añadir");
		contenedor.add(botonAnyadir);
		botonAnyadir.setBounds(345, 125, 85, 20);
		botonAnyadir.addActionListener(new OyenteBoton());

		botonEliminar = new JButton("Eliminar");
		contenedor.add(botonEliminar);
		botonEliminar.setBounds(345, 155, 85, 20);
		botonEliminar.addActionListener(new OyenteBoton());

		botonActualizar = new JButton("Actualizar");
		contenedor.add(botonActualizar);
		botonActualizar.setBounds(340, 185, 95, 20);
		botonActualizar.addActionListener(new OyenteBoton());

		contenedor.add(cajaId);
		cajaId.setBounds(120, 70, 45, 20);
		cajaId.setEnabled(false);

		contenedor.add(cajaNombre);
		cajaNombre.setBounds(120, 95, 200, 20);

		contenedor.add(cajaEstatura);
		cajaEstatura.setBounds(120, 125, 45, 20);

		contenedor.add(cajaEdad);
		cajaEdad.setBounds(120, 155, 45, 20);

		contenedor.add(cajaLocalidad);
		cajaLocalidad.setBounds(120, 185, 100, 20);

		contenedor.add(cajaBuscar);
		cajaBuscar.setBounds(345, 70, 85, 20);

		contenedor.add(etiqId);
		etiqId.setBounds(50, 70, 60, 20);

		contenedor.add(etiqNombre);
		etiqNombre.setBounds(50, 95, 60, 20);

		contenedor.add(etiqEstatura);
		etiqEstatura.setBounds(50, 125, 60, 20);

		contenedor.add(etiqEdad);
		etiqEdad.setBounds(50, 155, 60, 20);

		contenedor.add(etiqLocalidad);
		etiqLocalidad.setBounds(50, 185, 60, 20);

		contenedor.add(etiqNumSociosLista);
		etiqNumSociosLista.setBounds(205, 240, 100, 20);

		contenedor.add(etiqCm);
		etiqCm.setBounds(170, 125, 60, 20);

		contenedor.add(etiqAnyos);
		etiqAnyos.setBounds(170, 155, 60, 20);

		// Ponemos la ventana en visible y asignamos tama�o y condicion de cierre
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class OyenteBoton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AccesoBdatos abd = new AccesoBdatos();
			abd.conectar();

			if (event.getSource().equals(botonBuscar)) {
				if (cajaBuscar.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debes introducir una localidad para buscar.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					listaSociosPorLocalidad = abd.listaSociosPorLocalidad(cajaBuscar.getText());
					if (listaSociosPorLocalidad.size() == 0) {
						JOptionPane.showMessageDialog(null, "No se han encontrado socios en esta localidad");
					} else {
						etiqNumSociosLista
								.setText("Socio " + (abd.socioRecogido + 1) + " de " + listaSociosPorLocalidad.size());
						Socio socio = listaSociosPorLocalidad.get(abd.socioRecogido);
						cajaId.setText(String.valueOf(socio.getSocioId()));
						cajaNombre.setText(socio.getNombre());
						cajaEstatura.setText(String.valueOf(socio.getEstatura()));
						cajaEdad.setText(String.valueOf(socio.getEdad()));
						cajaLocalidad.setText(socio.getLocalidad());
						cajaBuscar.setText("");
						botonSiguiente.setEnabled(true);
						botonAnterior.setEnabled(true);
					}
				}
			}

			if (event.getSource().equals(botonSiguiente)) {
				Socio socio = abd.pasarAlSiguiente();
				cajaId.setText(String.valueOf(socio.getSocioId()));
				cajaNombre.setText(socio.getNombre());
				cajaEstatura.setText(String.valueOf(socio.getEstatura()));
				cajaEdad.setText(String.valueOf(socio.getEdad()));
				cajaLocalidad.setText(socio.getLocalidad());
				cajaBuscar.setText("");
				etiqNumSociosLista
						.setText("Socio " + (abd.socioRecogido + 1) + " de " + listaSociosPorLocalidad.size());
			}

			if (event.getSource().equals(botonAnterior)) {
				Socio socio = abd.pasarAlAnterior();
				cajaId.setText(String.valueOf(socio.getSocioId()));
				cajaNombre.setText(socio.getNombre());
				cajaEstatura.setText(String.valueOf(socio.getEstatura()));
				cajaEdad.setText(String.valueOf(socio.getEdad()));
				cajaLocalidad.setText(socio.getLocalidad());
				cajaBuscar.setText("");
				etiqNumSociosLista
						.setText("Socio " + (abd.socioRecogido + 1) + " de " + listaSociosPorLocalidad.size());
			}

			if (event.getSource().equals(botonAnyadir)) {
				if (cajaNombre.getText().isEmpty() && cajaEdad.getText().isEmpty() && cajaEstatura.getText().isEmpty()
						&& cajaLocalidad.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puede haber un campo vacío. Rellena todos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (cajaEdad.getText().matches("[\\D]+") || cajaEstatura.getText().matches("[\\D]+")) {
						JOptionPane.showMessageDialog(null, "No pueden haber letras en un campo de solo digitos.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Socio s = new Socio(0, cajaNombre.getText(), Integer.parseInt(cajaEstatura.getText()),
								Integer.parseInt(cajaEdad.getText()), cajaLocalidad.getText());
						abd.anyadirSocio(s);
					}
				}
			}

			abd.desconectar();
		}
	}

	public static void main(String[] args) {
		ConsultaSocio ventana = new ConsultaSocio();

	}// del main
}// de la clase

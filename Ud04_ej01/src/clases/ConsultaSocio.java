package clases;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Socio;

public class ConsultaSocio extends JFrame {

	Container contenedor;
	JButton boton1, boton2, boton3, btAnyadir, btEliminar, btActualizar;
	JTextField caja1, caja2, caja3, caja4, caja5, caja6;
	JLabel etiq1, etiq2, etiq3, etiq4, etiq5, etiq6, etiq7, etiq8;
	static int ultimo = 0;
	static int posicion = 1;

	public ConsultaSocio() {
		this.setTitle("Busqueda de Socios por localidad");
		contenedor = this.getContentPane();
		contenedor.setLayout(null);

		// Ponemos nombre e inicializamos botones, cajas y etiquetas

		caja1 = new JTextField();
		caja2 = new JTextField();
		caja3 = new JTextField();
		caja4 = new JTextField();
		caja5 = new JTextField();
		caja6 = new JTextField();

		etiq1 = new JLabel("Socio");
		etiq2 = new JLabel("Nombre");
		etiq3 = new JLabel("Estatura");
		etiq4 = new JLabel("Edad");
		etiq5 = new JLabel("Localidad");
		etiq6 = new JLabel(); // Le pondremos texto despues, cuando mostremos el conteo
		etiq7 = new JLabel("cm.");
		etiq8 = new JLabel("años");

		// A�adimos los elementos al contenedor y lo posicionamos
		boton1 = new JButton("Anterior");
		contenedor.add(boton1);
		boton1.setBounds(120, 295, 85, 20);
		boton1.addActionListener(new OyenteBoton());
		boton1.setEnabled(false);

		boton2 = new JButton("Siguiente");
		contenedor.add(boton2);
		boton2.setBounds(275, 295, 90, 20);
		boton2.addActionListener(new OyenteBoton());
		boton2.setEnabled(false);

		boton3 = new JButton("Buscar");
		contenedor.add(boton3);
		boton3.setBounds(345, 95, 85, 20);
		boton3.addActionListener(new OyenteBoton());

		btAnyadir = new JButton("Añadir");
		contenedor.add(btAnyadir);
		btAnyadir.setBounds(345, 125, 85, 20);
		btAnyadir.addActionListener(new OyenteBoton());

		btEliminar = new JButton("Eliminar");
		contenedor.add(btEliminar);
		btEliminar.setBounds(345, 155, 85, 20);
		btEliminar.addActionListener(new OyenteBoton());

		btActualizar = new JButton("Actualizar");
		contenedor.add(btActualizar);
		btActualizar.setBounds(340, 185, 95, 20);
		btActualizar.addActionListener(new OyenteBoton());

		contenedor.add(caja1);
		caja1.setBounds(120, 70, 45, 20);
		caja1.setEnabled(false);

		contenedor.add(caja2);
		caja2.setBounds(120, 95, 200, 20);

		contenedor.add(caja3);
		caja3.setBounds(120, 125, 45, 20);

		contenedor.add(caja4);
		caja4.setBounds(120, 155, 45, 20);

		contenedor.add(caja5);
		caja5.setBounds(120, 185, 100, 20);

		contenedor.add(caja6);
		caja6.setBounds(345, 70, 85, 20);

		contenedor.add(etiq1);
		etiq1.setBounds(50, 70, 60, 20);

		contenedor.add(etiq2);
		etiq2.setBounds(50, 95, 60, 20);

		contenedor.add(etiq3);
		etiq3.setBounds(50, 125, 60, 20);

		contenedor.add(etiq4);
		etiq4.setBounds(50, 155, 60, 20);

		contenedor.add(etiq5);
		etiq5.setBounds(50, 185, 60, 20);

		contenedor.add(etiq6);
		etiq6.setBounds(205, 240, 100, 20);

		contenedor.add(etiq7);
		etiq7.setBounds(170, 125, 60, 20);

		contenedor.add(etiq8);
		etiq8.setBounds(170, 155, 60, 20);

		// Ponemos la ventana en visible y asignamos tama�o y condicion de cierre
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class OyenteBoton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			AccesoBdatos abd = new AccesoBdatos();
			abd.conectar();
			List<Socio> listaSocios = abd.consultaLocalidad(caja6.getText());

			try {
				if (listaSocios != null || !listaSocios.isEmpty()) {

					boton1.setEnabled(true);
					boton2.setEnabled(true);
					
					ultimo = listaSocios.size();
					
					if (event.getSource() == boton3) { // Buscar
						posicion = 0;
						etiq6.setText("Socio " + (posicion + 1) + " de " + ultimo);
						listaSocios.get(posicion);
						
						caja1.setText(String.valueOf(listaSocios.get(posicion).getSocioId()));
						caja2.setText(listaSocios.get(posicion).getNombre());
						caja3.setText(String.valueOf(listaSocios.get(posicion).getEstatura()));
						caja4.setText(String.valueOf(listaSocios.get(posicion).getEdad()));
						caja5.setText(listaSocios.get(posicion).getLocalidad());
					}

					if (event.getSource() == boton2) { // Siguiente
						posicion++;
						if (posicion >= ultimo) {
							posicion = listaSocios.size();
							JOptionPane.showMessageDialog(null, "No existen registros posteriores", "Mensaje",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							etiq6.setText("Socio " + (posicion + 1) + " de " + ultimo);
							listaSocios.get(posicion);

							caja1.setText(String.valueOf(listaSocios.get(posicion).getSocioId()));
							caja2.setText(listaSocios.get(posicion).getNombre());
							caja3.setText(String.valueOf(listaSocios.get(posicion).getEstatura()));
							caja4.setText(String.valueOf(listaSocios.get(posicion).getEdad()));
							caja5.setText(listaSocios.get(posicion).getLocalidad());
						}
					}
					if (event.getSource() == boton1) { // Anterior
						posicion--;
						if (posicion < 0) {
							posicion = 1;
							JOptionPane.showMessageDialog(null, "No existen registros anteriores", "Mensaje",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							etiq6.setText("Socio " + (posicion + 1) + " de " + ultimo);
							listaSocios.get(posicion);

							caja1.setText(String.valueOf(listaSocios.get(posicion).getSocioId()));
							caja2.setText(listaSocios.get(posicion).getNombre());
							caja3.setText(String.valueOf(listaSocios.get(posicion).getEstatura()));
							caja4.setText(String.valueOf(listaSocios.get(posicion).getEdad()));
							caja5.setText(listaSocios.get(posicion).getLocalidad());
						}

					}

				} else {
					JOptionPane.showMessageDialog(null, "No se han encontrado socios de " + caja6.getText(), "Mensaje",
							JOptionPane.INFORMATION_MESSAGE);
					abd.desconectar();
				}

			} catch (Exception e) {
				System.out.println("Error sin especificar, borrar despues");
				e.printStackTrace();
			}

			if (event.getSource() == btAnyadir) {
				if (caja5.getText().isEmpty() || caja2.getText().isEmpty() || caja3.getText().isEmpty()
						|| caja4.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Tienen que estar todos los campos rellenados.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (abd.anyadirSocio(caja2.getText(), Integer.valueOf(caja3.getText()),
							Integer.valueOf(caja4.getText()), caja5.getText()) != 1) {
						JOptionPane.showMessageDialog(null, "Error al añadir un socio.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Socio añadido correctamente!", "Socio añadido",
								JOptionPane.INFORMATION_MESSAGE);

					}
				}

			}

			if (event.getSource() == btEliminar) {
				abd.eliminarSocio(Integer.valueOf(caja1.getText()));
				caja1.setText("");
				caja2.setText("");
				caja3.setText("");
				caja4.setText("");
				caja5.setText("");
				caja6.setText("");
				etiq6.setText("");
				
			}

			if (event.getSource() == btActualizar) {
				if (caja5.getText().isEmpty() || caja2.getText().isEmpty() || caja3.getText().isEmpty()
						|| caja4.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Tienen que estar todos los campos rellenados.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					int id = Integer.valueOf(caja1.getText());
					abd.actualizarSocio(id, caja2.getText(), Integer.valueOf(caja3.getText()), Integer.valueOf(caja4.getText()), caja5.getText());
				}
			}
			
		}
	}

	public static void main(String[] args) {
		ConsultaSocio ventana = new ConsultaSocio();

	}// del main
}// de la clase

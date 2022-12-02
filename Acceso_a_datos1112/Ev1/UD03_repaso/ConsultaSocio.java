package UD03_repaso;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ConsultaSocio extends JFrame {

	Container contenedor;
	JButton boton1, boton2, btBuscar, btAnyadir, btEliminar, btActualizar;
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
		etiq8 = new JLabel("anyos");

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

		btBuscar = new JButton("Buscar");
		contenedor.add(btBuscar);
		btBuscar.setBounds(345, 95, 85, 20);
		btBuscar.addActionListener(new OyenteBoton());

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

		// Ponemos la ventana en visible y asignamos tamaño y condicion de cierre
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	class OyenteBoton implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			AccesoBdatos abd = new AccesoBdatos();
			abd.conectar();
			
			if (event.getSource().equals(btAnyadir)) {
				
				if (caja2.getText().isEmpty() || caja3.getText().isEmpty() || caja4.getText().isEmpty() || caja5.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tienen que estar todos los campos rellenados.", "Error al insertar nuevo socio", JOptionPane.ERROR_MESSAGE);
					
				} else {
					int id = abd.ultimaIdMasUno();
					String nombre = caja2.getText();
					int estatura = Integer.parseInt(caja3.getText());
					int edad = Integer.parseInt(caja4.getText());
					String localidad = caja5.getText();
					
					if (abd.anyadirSocio(id, nombre, estatura, edad, localidad) == 1) {
						JOptionPane.showMessageDialog(null, "¡Se ha añadido el socio correctamente!", "Inserción correcta", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido insertar el socio...", "Error al insertar", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
			
			if (event.getSource().equals(btBuscar)) {
				
				if (caja6.getText().isEmpty()) {
					
				} else {
					ResultSet rs = null;
					
					try {
						
						rs = abd.obtenerSocioPorId(Integer.parseInt(caja6.getText()));
						
						if (rs.next()) {
							
							caja1.setText(String.valueOf(rs.getInt(1)));
							caja2.setText(rs.getString(2));
							caja3.setText(String.valueOf(rs.getInt(3)));
							caja4.setText(String.valueOf(rs.getInt(4)));
							caja5.setText(rs.getString(5));
							
							
						} else {
							JOptionPane.showMessageDialog(null, "No se ha podido encontrar un socio con esta socioID", "Error al buscar", JOptionPane.ERROR_MESSAGE);
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			
			}
			
			if (event.getSource().equals(btActualizar)) {
				
				System.out.println(abd.actualizarSocioPorId(Integer.parseInt(caja1.getText()), caja2.getText(), Integer.parseInt(caja3.getText()), Integer.parseInt(caja4.getText()), caja5.getText()));
				
			}
			
			if (event.getSource().equals(btEliminar)) {
				
				if (caja1.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Debe estar el campo socioID lleno", "Error al borrar", JOptionPane.ERROR_MESSAGE);
				} else {
					
					if (abd.eliminarSocioPorId(Integer.parseInt(caja1.getText())) == 1) {
						JOptionPane.showMessageDialog(null, "Se ha eliminado correctamente el socio con id: " + caja1.getText(), "Eliminación completada", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error con la eliminación", "Eliminación fallida", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				
			}
			
			abd.desconectar();
			
		}
	}

	public static void main(String[] args) {
		ConsultaSocio ventana = new ConsultaSocio();

	}
}// de la clase

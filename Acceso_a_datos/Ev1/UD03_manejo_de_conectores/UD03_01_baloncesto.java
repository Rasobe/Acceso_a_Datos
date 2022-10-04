package UD03_manejo_de_conectores;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import UD03_objetos.Socio;

public class UD03_01_baloncesto {
	
	public static void main(String[] args) {
		
		exportarJson();
		
	}
	
	public static boolean exportarJson() {
		
		AccesoBdatos abd = new AccesoBdatos("baloncesto");
		abd.conectar();
		
		abd.crear_fichero();
		
		abd.desconectar();
		
		return false;
	}

}

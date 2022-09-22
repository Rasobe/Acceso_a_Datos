package manejo_de_ficheros;

import java.io.File;

public class UD2_12_Main {

	public static void main(String[] args) {

		UD2_12_FilenameFilter ej = new UD2_12_FilenameFilter();

		if (args.length != 0) {
			ej.accept(new File(args[0]), args[1]);
		} else {
			System.out.println("Faltan argumentos.");
		}

	}

}

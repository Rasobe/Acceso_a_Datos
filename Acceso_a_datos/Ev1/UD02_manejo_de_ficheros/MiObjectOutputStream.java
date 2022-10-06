package UD02_manejo_de_ficheros;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

	public MiObjectOutputStream(OutputStream arg0) throws IOException, SecurityException {
		super(arg0);
	}

	// Sobrescritura del método
	protected void writeStreamHeader() throws IOException {
	}

}

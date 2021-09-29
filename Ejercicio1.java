package ejercicio0928;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Ejercicio1 {

	 public static void crearConfig(String serializacion) {

		Properties configuracion = new Properties();

		configuracion.setProperty("serializacion", serializacion);

		try  {

			configuracion.store(new FileOutputStream("config.props"),"Fichero de config serializacion");

		} catch (FileNotFoundException fnfe ) { 
			fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
			ioe.printStackTrace();
		}


	}


	public static String leerConfig() {
		Properties configuracion = new Properties();
		String serializacion="";

		try {
			configuracion.load(new FileInputStream("config.props"));
			serializacion = configuracion.getProperty("serializacion");
		} catch (FileNotFoundException fnfe ) { 
			fnfe.printStackTrace();
		} catch (IOException ioe) { 
			ioe.printStackTrace();
		}
		return serializacion;
	}



	public static void main(String[] args) {
		String serializacion="Futbolista";

		crearConfig(serializacion);

		leerConfig();

	}
}

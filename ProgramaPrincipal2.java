package ejercicio0928;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import aeropuertoEscritura.MiObjectOutputStream;
import serializacion.persona.Persona;

public class ProgramaPrincipal2 {

	public static void escribirPersona(Object objeto) {
		
		FileOutputStream fichero = null;
		ObjectOutputStream serializador = null;
		
		try {
			fichero = new FileOutputStream("Futbol.ddr");
			serializador = new ObjectOutputStream(fichero);

            serializador.writeObject(objeto);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) {
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	public static void escribirEquipos(ArrayList<Equipo> listaEquipos) {

		Boolean isOne = true;
		Iterator <Equipo> equipo = listaEquipos.iterator();

		while(equipo.hasNext()) {
			if (isOne) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Futbol.ddr"))){

					oos.writeObject(equipo.next());

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
				}
				isOne = false;
			} else {
				try (MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream("Futbol.ddr", true))){

					moos.writeObject(equipo.next());

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	public static Object leerObjetoPersona() {

		FileInputStream fichero = null;
		ObjectInputStream serializador = null;
		Object objeto = null;

		try {
			fichero = new FileInputStream("Futbol.ddr");
			serializador = new ObjectInputStream(fichero);

			objeto = serializador.readObject();

		} catch (FileNotFoundException fnfe) {
			System.out.println("No se encuentra el fichero especificado");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Tipo de objeto no conocido");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) 
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}

		return objeto;
	}
	
	public static ArrayList<Equipo> leerEquipo() {
		ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Futbol.ddr"))){

			while(true){
				Object objeto = ois.readObject();
				listaEquipos.add((Equipo)objeto);
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
		}catch(EOFException e){
		} catch (IOException ex) {
			Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal2.class.getName()).log(Level.SEVERE, null, ex);
		}

		return listaEquipos;
	}

	/**
	 * The Main1 method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) { // punto de entrada al programa

		String outputConf = "Entrenador";

		Ejercicio1.crearConfig(outputConf);

		String inputConf = Ejercicio1.leerConfig();
		ArrayList<Futbolista> futbolistas = new ArrayList<Futbolista>();
		ArrayList<Entrenador> entrenadores = new ArrayList<Entrenador>();
		ArrayList <Equipo> equipos = new ArrayList<Equipo>();
		LocalDate nacimiento = LocalDate.now();

		switch (inputConf) {
			case "Futbolista":
				Futbolista futbolista;
					for (int j = 0; j < 22; j++) {
						if (j<4) {
							futbolistas.add(futbolista = new Futbolista(1.70f, 70.9f, nacimiento.minusYears(20), "Jugador"+j, j, "Fempa FC", 1523, 80, 69, 74, 100, 45, 85, 76, 100, "delantero", 56, "media"));
						} else if (j>3 && j<12) {
							futbolistas.add(futbolista = new Futbolista(1.75f, 75f, nacimiento.minusYears(22), "Jugador"+j, j, "Fempa FC", 1698, 69, 69, 100, 100, 57, 58, 79, 100, "centrocampista", 98, "media"));
						} else if (j>11 && j<20) {
							futbolistas.add(futbolista = new Futbolista(1.8f, 77f, nacimiento.minusYears(24), "Jugador"+j, j, "Fempa FC", 1698, 90, 75, 86, 84, 57, 75, 79, 70, "defensa", 85, "media"));
						} else if (j>19 && j<22) {
							futbolistas.add(futbolista = new Futbolista(1.9f, 85f, nacimiento.minusYears(28), "Jugador"+j, j, "Fempa FC", 2410, 100, 88, 100, 90, 87, 96, 100, 79, "portero", 100, "alta"));
						}
					}
					escribirPersona(futbolistas);
				break;		
			case "Entrenador":
				Entrenador entrenador;
				for (int i = 0; i < 22; i++) {
					entrenadores.add(entrenador = new Entrenador("Entrenador"+i, nacimiento.minusYears(40), 1.79f, 85, "Equipo"+i, 3652f, 0, 58, 98, 75));
				}
				escribirPersona(entrenadores);
				break;		
			case "Equipo":
				Equipo equipo;
				for (int i = 0; i < 20; i++) {
					equipo = new Equipo(("Equipo"+i), nacimiento.minusYears(28), "Presidente"+i);
					equipos.add(equipo);
				}
				escribirPersona(equipos);
				break;
		}
		
		System.out.println("Se ha guardado lo que pone en configuración serializado. A continuación se muestra lo guardado deserializado.");


		switch (inputConf) {
			case "Futbolista":
				ArrayList<Futbolista> futbolistas2 = (ArrayList<Futbolista>) leerObjetoPersona();
				for (Futbolista futbolista : futbolistas2) {
					System.out.println(futbolista.toString());
				}
				System.out.println(futbolistas2.toString());
				break;		
			case "Entrenador":
				ArrayList<Entrenador> entrenadores2 = (ArrayList<Entrenador>) leerObjetoPersona();
				for (Entrenador entrenador : entrenadores2) {
					System.out.println(entrenador.toString());
				}
				break;		
			case "Equipo":
				ArrayList<Equipo> equipos2 = (ArrayList<Equipo>) leerObjetoPersona();
				for (Equipo equipo : equipos2) {
					System.out.println(equipo.toString());
				}
				break;
		}
		
		//menu();

	}

}

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

public class ProgramaPrincipal {

	public static void escribirPersonas(ArrayList<Persona> listaPersonas) {

		Boolean isOne = true;
		Iterator <Persona> persona = listaPersonas.iterator();

		while(persona.hasNext()) {
			if (isOne) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Futbol.ddr"))){

					oos.writeObject(persona.next());

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				}
				isOne = false;
			} else {
				try (MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream("Futbol.ddr", true))){

					moos.writeObject(persona.next());

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
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
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				}
				isOne = false;
			} else {
				try (MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream("Futbol.ddr", true))){

					moos.writeObject(equipo.next());

				} catch (FileNotFoundException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Persona> leerPersonas() {

		ArrayList<Persona> listaPersonas = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Futbol.ddr"))){

			Object objeto = ois.readObject();
			listaPersonas = (ArrayList<Persona>) objeto;
			

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}catch(EOFException e){
		} catch (IOException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}

		return listaPersonas;
	}
	
	public static ArrayList<Equipo> leerEquipo() {
		ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Futbol.ddr"))){

			while(true){
				Object objeto = ois.readObject();
				listaEquipos.add((Equipo)objeto);
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}catch(EOFException e){
		} catch (IOException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ProgramaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
		}

		return listaEquipos;
	}

	/**
	 * The Main1 method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) { // punto de entrada al programa

		String outputConf = "Futbolista";

		Ejercicio1.crearConfig(outputConf);

		String inputConf = Ejercicio1.leerConfig();
		ArrayList<Persona> personas = new ArrayList<Persona>();
		ArrayList <Equipo> equipos = new ArrayList<Equipo>();
		LocalDate nacimiento = LocalDate.now();

		switch (inputConf) {
			case "Futbolista":
				Futbolista futbolista;
					for (int j = 0; j < 22; j++) {
						if (j<4) {
							personas.add(futbolista = new Futbolista(1.70f, 70.9f, nacimiento.minusYears(20), "Jugador"+j, j, "Fempa FC", 1523, 80, 69, 74, 100, 45, 85, 76, 100, "delantero", 56, "media"));
						} else if (j>3 && j<12) {
							personas.add(futbolista = new Futbolista(1.75f, 75f, nacimiento.minusYears(22), "Jugador"+j, j, "Fempa FC", 1698, 69, 69, 100, 100, 57, 58, 79, 100, "centrocampista", 98, "media"));
						} else if (j>11 && j<20) {
							personas.add(futbolista = new Futbolista(1.8f, 77f, nacimiento.minusYears(24), "Jugador"+j, j, "Fempa FC", 1698, 90, 75, 86, 84, 57, 75, 79, 70, "defensa", 85, "media"));
						} else if (j>19 && j<22) {
							personas.add(futbolista = new Futbolista(1.9f, 85f, nacimiento.minusYears(28), "Jugador"+j, j, "Fempa FC", 2410, 100, 88, 100, 90, 87, 96, 100, 79, "portero", 100, "alta"));
						}
					}
				escribirPersonas(personas);
				break;		
			case "Entrenador":
				Entrenador entrenador;
				for (int i = 0; i < 22; i++) {
					personas.add(entrenador = new Entrenador("Entrenador"+i, nacimiento.minusYears(40), 1.79f, 85, "Equipo"+i, 3652, 0, 58, 98, 75));
				}
				escribirPersonas(personas);
				break;		
			case "Equipo":
				Equipo equipo;
				for (int i = 0; i < 20; i++) {
					equipo = new Equipo(("Equipo"+i), nacimiento.minusYears(28), "Presidente"+i);
				}
				escribirEquipos(equipos);
				break;
		}
		
		System.out.println("Se ha guardado lo que pone en configuración.");
		
		
		personas.clear();
		equipos.clear();
		switch (inputConf) {
			case "Futbolista":
				personas = leerPersonas();
				System.out.println(personas.toString());
				break;		
			case "Entrenador":
				personas = leerPersonas();
				personas.toString();
				break;		
			case "Equipo":
				equipos = leerEquipo();
				equipos.toString();
				break;
		}
		
		//menu();

	}

}

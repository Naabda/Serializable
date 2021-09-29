/*
 * PRG-Clase18
 * 14 ene. 2021
 * Javier Faus Llopis  
 */
package ejercicio0928;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Auto-generated Javadoc
/**
 * The Class Entrenador.
 */
public class Entrenador extends Persona implements Serializable {
	
	//atributos sobre caracteristicas tecnicas
	int liderazgo;//1-100.
	int estrategia;//1-100.
	int comunicacion;//1-100.
	
	//provocamos la ocultaci�n del atributo heredado de Persona cambiando por ejemplo el tipo de dato a double
//	private double peso;
//	
//	@Override
//	getPeso() {
//		return this.peso;
//	}
	
	
	/**
	 * @param nombre
	 * @param fechaNacimiento
	 * @param altura
	 * @param peso
	 * @param nomEquipo
	 * @param sueldo
	 * @param experiencia
	 * @param liderazgo
	 * @param estrategia
	 * @param comunicacion
	 */
	
	// Constructor Sobrecargado con todos los atributos
	public Entrenador(String nombre, LocalDate fechaNacimiento, float altura, float peso, String nomEquipo,
			float sueldo, int experiencia, int liderazgo, int estrategia, int comunicacion) {
		
		super(nombre,fechaNacimiento,altura,peso,experiencia,nomEquipo,sueldo);
		
		this.liderazgo = liderazgo;
		this.estrategia = estrategia;
		this.comunicacion = comunicacion;
	}
	
	// sobrecarga del constructor con solo atributo nombre
	public Entrenador(String nombre) {
		this.setNombre(nombre);
		
	}

	public Entrenador() { // constructor vacio 
		super();
		liderazgo=0;//1-100.
		estrategia=0;//1-100.
		comunicacion=0;//1-100.
	}
	
	public Entrenador(Entrenador e) {
		super((Persona)e);
		this.liderazgo=e.getLiderazgo() ;//1-100.
		this.estrategia=e.getEstrategia();//1-100.
		this.comunicacion=e.getComunicacion();//1-100.
	}
	
	
	public void copiar(Entrenador e) {
		super.copiar((Persona)e);
		this.liderazgo=e.getLiderazgo() ;//1-100.
		this.estrategia=e.getEstrategia();//1-100.
		this.comunicacion=e.getComunicacion();//1-100.
	}
	
	
	@Override
	public String toString() {
		return  super.toString() + ", liderazgo=" + liderazgo + ", estrategia=" + estrategia + ", comunicacion=" + comunicacion + "]";
	}



	/* Metodos implementados */
	
	/**
	 * Calcula el valor que aporta el entrenador al equipo.
	 * @return int
	 */
	public int calcValor() {
		int valor = Math.round((this.liderazgo + this.estrategia + this.comunicacion) /3);
		return valor;
	}
	
	
	
	public void organizarEntrenamiento(ArrayList<Futbolista> jugadores) {
		
	}
	
	
	/*
	 * 
		Para hacer las alineaciones de cada partido el entrenador debe de 
		ordenar sus jugadores (de mejor a peor) en base al valor (m�s es mejor) que aportan al equipo 
		y su posici�n en el terreno de juego de manera que elegir� a los mejores jugadores para el partido
		para cada posici�n (Portero, Defensa, Centro y Delantero) teniendo en cuenta que la alineaci�n
		siempre ser� un 1-4-4-2.
	 */
	
	/*
	 * Obtener la lista de jugadores titulares para un partido.
	 * */
	
	@SuppressWarnings("unchecked")
	public ArrayList<Futbolista> ponerAlineacion( ArrayList<Futbolista> jugadores ) {
		
//		Preparamos las estructuras de datos a utilizar
		
		ArrayList<Futbolista> porteros = new ArrayList<>();
		ArrayList<Futbolista> defensas = new ArrayList<>();
		ArrayList<Futbolista> centrocampistas = new ArrayList<>();
		ArrayList<Futbolista> delanteros = new ArrayList<>();
		
		
//		Parseamos el ArrayList con todos todos los jugadores y los a�adimos seg�n su posici�n en su respectivo ArrayList
		
		for (Futbolista futbolista : jugadores) {
			
			if(futbolista.getPosicion().equalsIgnoreCase("portero"))
				porteros.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("defensa"))
				defensas.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("centrocampista"))
				centrocampistas.add(futbolista);
			
			if(futbolista.getPosicion().equalsIgnoreCase("delantero"))
				delanteros.add(futbolista);
			
		}
//		Ordenamos cada uno de los arrayList en orden "de mejor a peor" con respecto al m�todo calcValor()
		Collections.sort(porteros);
		Collections.sort(defensas);
		Collections.sort(centrocampistas);
		Collections.sort(delanteros);

		
		//Vamos ahora a construir el ArrayList<Futbolista> alineacion en el que ir�n solo los jugadores titulares
		ArrayList<Futbolista> alineacion = new ArrayList<>();
		
		alineacion.addAll(porteros.subList(0, 1));//.subList() empieza en 0 y el �ltimo indicado no se incluye
		alineacion.addAll(defensas.subList(0, 4));
		alineacion.addAll(centrocampistas.subList(0, 4));
		alineacion.addAll(delanteros.subList(0, 2));
		
		return alineacion;
	}
	

	/* Getters and Setters */


	/**
	 * @return the liderazgo
	 */
	public int getLiderazgo() {
		return liderazgo;
	}


	/**
	 * @param liderazgo the liderazgo to set
	 */
	public void setLiderazgo(int liderazgo) {
		this.liderazgo = liderazgo;
	}


	/**
	 * @return the estrategia
	 */
	public int getEstrategia() {
		return estrategia;
	}


	/**
	 * @param estrategia the estrategia to set
	 */
	public void setEstrategia(int estrategia) {
		this.estrategia = estrategia;
	}


	/**
	 * @return the comunicacion
	 */
	public int getComunicacion() {
		return comunicacion;
	}


	/**
	 * @param comunicacion the comunicacion to set
	 */
	public void setComunicacion(int comunicacion) {
		this.comunicacion = comunicacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + comunicacion;
		result = prime * result + estrategia;
		result = prime * result + liderazgo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entrenador other = (Entrenador) obj;
		if (comunicacion != other.comunicacion)
			return false;
		if (estrategia != other.estrategia)
			return false;
		if (liderazgo != other.liderazgo)
			return false;
		return true;
	}

}

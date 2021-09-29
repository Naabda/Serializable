/*
 * PRG-Clase18
 * 14 ene. 2021
 * Javier Faus Llopis 
 */
package ejercicio0928;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The Class Equipo.
 * Opcional: considerar la funcionalidad de hacer fichajes. Tener en cuenta que fichar a un futbolista implica darlo de baja en su respectivo equipo.
 * 
 * @author Javier Faus Llopis
 */
public class Equipo implements Comparable, Serializable{
	
	private String nombre;
	private ArrayList<Futbolista> aEquipo;
	private Entrenador entrenador;
	private String localidad;
	private LocalDate fechFundacion;
	private ArrayList<Futbolista> alineacion;//los once jugadores que seran titulares en un determinado partido
	private ArrayList<Titulo> titulos;
	private float patrimonio;
	private String nombrePresidente;
	private String [] equipacion; // tres equipaciones. Ej: 1."Blanco" , 2. "Azul", 3. "BlanquiAzul"  

	private int puntos; // representa los puntos del equipo en una competicion
	private int patidosGanados=0;
	private int partidosPerdidos=0;
	private int partidosEmpatados=0;

	
	// implementar todos los métodos
	/**
	 * @param int divisor 
	 * Nos indicará entre cuantas personas tendremos que dividir para calcular la media del 
	 * valor global del equipo.
	 * @param int valorEq
	 * Guardará todos los valores de los jugadores y el entrenador, para posteriormente 
	 * procesarlo con el divisor y su resultado lo llevaremos al return
	 * @return int
	 * 
	 * Este método solicitará la alineación al entrenador, cogeremos esos jugadores y junto
	 * con el entrenador calcularemos su promedio de valor y lo retornaremos.
	 */
	public Equipo (String nombrEq, LocalDate fundacion, String presidente) {
		this.nombre = nombrEq;
		this.fechFundacion = fundacion;
		this.patrimonio = 0;
		this.nombrePresidente = presidente;
	}

	public  int calcValor() {//Capacidad de ganar un partido, es la media de sus jugadores alineados y su entrenador.
		int divisor = 0, valorEq = 0;
		
		this.alineacion = entrenador.ponerAlineacion(aEquipo);
		
		for (Futbolista futbolista : this.alineacion) {
			divisor++;
			valorEq += futbolista.calcValor();
		}
		
		valorEq += entrenador.calcValor();
		divisor++;
		
		valorEq = Math.round((valorEq/divisor));
		return valorEq; 
	}
	
	public  void anyadirPuntos(int punto) {
		this.puntos += punto;
	}
	
	public  void anyadirTitulo(Titulo titulo) {
		this.titulos.add(titulo);
	}
	
	/**
	 * @param dinero
	 * Cantidad de dinero que puede ser positiva o 
	 * negativa en función de si es incremento o 
	 * decremento que al sumarlo al patrimonio 
	 * actual lo incrementará o decrementará
	 */
	public  void actualizarPatrimonio (float dinero) {
		this.patrimonio += dinero;
	}
	
	/**
	 * @param fichaje
	 * Recepcionamos el nuevo fichaje.
	 * 
	 * Recibimos un nuevo futbolista y lo añadimos al
	 * ArrayList del equipo.
	 */
	public  void anyadirFutbolista (Futbolista fichaje) {
		this.aEquipo.add(fichaje);
	}
	
	/**
	 * @param int indice
	 * Nos indica en la posicion del ArrayList en la que nos 
	 * encontramos.
	 * @param despedido
	 * Futbolista que se quiere quitar del ArrayList del equipo.
	 * 
	 * Inicializamos el indice en cero, recorremos el ArrayList
	 * del equipo y comprobamos si coincide con el que queremos
	 * eliminar, en caso de coincidir lo elimanos.
	 */
	public  void eliminarFutbolista (Futbolista despedido) {
		int indice = 0;
		for (Futbolista futbolista : aEquipo) {
			if (futbolista.equals(despedido)) {
				this.aEquipo.remove(indice);
			}
			indice++;
		}
	}
	
	public  void cambiarEntrenador (Entrenador nuevo) {
		this.entrenador = nuevo;
	}
	
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", nombrePresidente=" + nombrePresidente + "]";
	}

	@Override
	public int compareTo(Object equipoComparado) {
		/*
		 * El que más puntos tenga.
		 * A empate de puntos se pasa a ver quien tiene más goles.
		 * En caso de empate de todo orden alfabético del nombre de equipo.
		 */
		int resultado=0;

		if( equipoComparado instanceof Equipo ) {

			float puntuacionEquipoComparado=((Equipo)equipoComparado).getPuntos();

			if( this.getPuntos() >  puntuacionEquipoComparado ) {
				resultado = 1; //valor positivo, yo soy mayor.
			}
			else if(this.getPuntos() <  puntuacionEquipoComparado) {
				resultado = -1; // valor negativo, el objetoComparado es mayor
			}
			else {
				resultado=0; // valor cero indica igualdad
			}
			
		} 
		else {
			//si el objeto comparado no es de este tipo equipoComparable devolveremos 0 igualmente, podríamos hacer otras acciones.
			System.err.println("Error: comparando objeto Futbolista con otro tipo de objeto:");
			System.err.print(equipoComparado.getClass());
			
		}

		// Si queremos invertir el orden devuelto para que sea descendente
		resultado*= -1;
	
		return resultado;
	
	}
	
	
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the aEquipo
	 */
	public ArrayList<Futbolista> getaEquipo() {
		return aEquipo;
	}
	/**
	 * @param aEquipo the aEquipo to set
	 */
	public void setaEquipo(ArrayList<Futbolista> aEquipo) {
		this.aEquipo = aEquipo;
	}
	/**
	 * @return the entrenador
	 */
	public Entrenador getEntrenador() {
		return entrenador;
	}
	/**
	 * @param entrenador the entrenador to set
	 */
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return the fechFundacion
	 */
	public LocalDate getFechFundacion() {
		return fechFundacion;
	}
	/**
	 * @param fechFundacion the fechFundacion to set
	 */
	public void setFechFundacion(LocalDate fechFundacion) {
		this.fechFundacion = fechFundacion;
	}
	/**
	 * @return the alineacion
	 */
	public ArrayList<Futbolista> getAlineacion() {
		return alineacion;
	}
	/**
	 * @param alineacion the alineacion to set
	 */
	public void setAlineacion(ArrayList<Futbolista> alineacion) {
		this.alineacion = alineacion;
	}
	/**
	 * @return the titulos
	 */
	public ArrayList<Titulo> getTitulos() {
		return titulos;
	}
	/**
	 * @param titulos the titulos to set
	 */
	public void setTitulos(ArrayList<Titulo> titulos) {
		this.titulos = titulos;
	}
	/**
	 * @return the patrimonio
	 */
	public float getPatrimonio() {
		return patrimonio;
	}
	/**
	 * @param patrimonio the patrimonio to set
	 */
	public void setPatrimonio(float patrimonio) {
		this.patrimonio = patrimonio;
	}
	/**
	 * @return the nombrePresidente
	 */
	public String getNombrePresidente() {
		return nombrePresidente;
	}
	/**
	 * @param nombrePresidente the nombrePresidente to set
	 */
	public void setNombrePresidente(String nombrePresidente) {
		this.nombrePresidente = nombrePresidente;
	}
	/**
	 * @return the equipacion
	 */
	public String[] getEquipacion() {
		return equipacion;
	}
	/**
	 * @param equipacion the equipacion to set
	 */
	public void setEquipacion(String[] equipacion) {
		this.equipacion = equipacion;
	}
	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	/**
	 * @return the patidosGanados
	 */
	public int getPatidosGanados() {
		return patidosGanados;
	}
	/**
	 * @param patidosGanados the patidosGanados to set
	 */
	public void setPatidosGanados(int patidosGanados) {
		this.patidosGanados = patidosGanados;
	}
	/**
	 * @return the partidosPerdidos
	 */
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	/**
	 * @param partidosPerdidos the partidosPerdidos to set
	 */
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	/**
	 * @return the partidosEmpatados
	 */
	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}
	/**
	 * @param partidosEmpatados the partidosEmpatados to set
	 */
	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}
	
}

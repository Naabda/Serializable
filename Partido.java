package ejercicio0928;

import java.time.LocalDate;

/*
 * Opcional: Hacer seguimiento de los jugadores sancionados y/o lesionados. Dicha informacion se pasara a los equipos/competicion tras cada partido.
 * 
 */

public class Partido {

	Equipo local;
	Equipo visitante;
	//int [] resultado;
	String localidad;
	Futbolista mvp;
	//ArrayList<Futbolista> goleadores;
	//Futbolista [] sancionados;
	//Futbolista [] lesionados;
	//Tarjeta [] listaSanciones; // ¿como hacerlo?

	LocalDate fecha;

	/**
	 * @param a
	 * Se asigna como equipo local
	 * @param b
	 * Se asigna como equipo visitante
	 */
	public Partido (Equipo a, Equipo b) {
		this.local = a;
		this.visitante = b;
	}

	/**
	 * @param int valorLocal
	 * Es el valor del equipo calculado multiplicado por un modificador aleatorio de +-10% 
	 * @param int valorVisitante
	 * Es el valor del equipo calculado multiplicado por un modificador aleatorio de +-10% 
	 *  
	 *  Se comprueba que los puntos de diferencia sean mayores de diez para decidir quien gana, en 
	 *  caso que la diferencia sea menor de diez puntos se declara empate.
	 *  
	 */
	public void calcularGanador() {
		
		int valorLocal = Math.round(local.calcValor() * modificadorAleatorio());
		int valorVisitante = Math.round(visitante.calcValor() * modificadorAleatorio());
		
		if ((valorLocal - valorVisitante)>10 && valorLocal > valorVisitante) {
			//Gana equipo local
			asignarPuntos(this.local, this.visitante, false);
			this.local.setPatidosGanados(1);
			this.visitante.setPartidosPerdidos(1);
			
		} else if ((valorLocal - valorVisitante)>10 && valorVisitante > valorLocal) {
			// Gana equipo visitante
			asignarPuntos(this.visitante, this.local, false);
			this.local.setPartidosPerdidos(1);
			this.visitante.setPatidosGanados(1);
		} else {
			// Empate
			asignarPuntos(this.local, this.visitante, true);
			this.local.setPartidosEmpatados(1);
			this.visitante.setPartidosEmpatados(1);
		}
	}

	/**
	 * @param int numero 
	 * 	Obtiene como valor 0 ó 1.
	 * @return float
	 * 	En función del valor obtenido en numero nos
	 * 	retorna un multiplcador de -10% ó +10%.
	 */
	public float modificadorAleatorio() {

		int numero = (int) Math.round(Math.random());

		switch (numero) {
			case 0:
				return 0.90f;
			case 1:
				return 1.10f;
		}
		return 1;
	}



	public void calcularMVP() {			//Quien es el mejor jugador.
		/*En caso de empate a cero se le asigna a suerte a un portero
		 * si hay un máximo goleador a él 
		 * si hay más de uno se sortea a partes iguales.
		 */

	}
	/*	
	public Equipo getGanador() {//En caso de empate resultado null.

	}

	public Equipo getPerdedor() {//En caso de empate resultado null.

	}

	private Futbolista[] getGoleadores() {//Obtenemos del calculo quienes son los goleadores.

	}


	 */	
	public void calcularGoleadores() {//Calcula quienes son los goleadores.
		/*Para la asignacion del gol el porcentaje según posición es:
		 * Delantero 70%, Centro 20%, Defensa 9% y portero 1%.
		 * Y después dividiremos entre los jugadores que hayan en esa categoría.
		 */

	}

	/**
	 * @param int ganador
	 * Recibe el equipo ganador.
	 * @param int perdedor
	 * Recibe el equipo perdedor.
	 * @param boolean empate
	 * Recibe un boleano que indica: 
	 * 		false, se mantienen los parámetros pasados y les asigna 
	 * 	los puntos correspondientes.
	 * 		true, anula los parametros pasados y asigna los puntos 
	 * 	como empate.
	 * 
	 * La asignación de puntos es:
	 * 		3 puntos en caso de ganar.
	 * 		0 puntos en caso de perder.
	 * 		1 punto a cada equipo en caso de empate.
	 *  
	 */
	public void asignarPuntos (Equipo ganador, Equipo perdedor, boolean empate) {
		int puntosG = ganador.getPuntos();
		int puntosP = perdedor.getPuntos();
		if (empate) {
			puntosG += 1;
			puntosP += 1;
		} else {
			puntosG += 3;
		}
	}

	//	public int getGolesLocal(){}
	//	public int getGolesVisitante(){}
	//	
	//	public int getGoles(Equipo equipo) {}




	
	
}
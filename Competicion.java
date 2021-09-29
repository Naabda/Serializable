package ejercicio0928;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Competicion {
	
	static ArrayList<Equipo> equipos = new ArrayList<>();
	static ArrayList<Partido> partidos = new ArrayList<>();
	static ArrayList<Equipo> clasificacion = new ArrayList<>();
	Titulo titulo;
	static String nombreArchivo;
	int numEquipos;
	
	/**
	 * Constructor vacio.
	 */
	public Competicion () {}
	
	/**
	 * 
	 * @param nomCompeticion
	 * @param titulo
	 * @param numEqPaticipantes
	 * 
	 * Constructor de la competicion donde asigna los valores recibidos.
	 */
	public Competicion (String nomCompeticion, Titulo titulo, int numEqPaticipantes) {
		Competicion.nombreArchivo = nomCompeticion;
		this.titulo = titulo;
		this.numEquipos = numEqPaticipantes;
	}
	
	/**
	 * Utilizamos el @param equipos para saber los equipos de la competición.
	 * Con un primer bucle recorremos todos los equipos, con un segundo bucle
	 * los enfrentamos contra los demás equipos de forma que se generan los
	 * partidos de ida en el primero y de vuelta en el segundo bucle.
	 * 
	 * Con el if controlamos que no coincida un partido contra si mismo.
	 * 
	 */
	public static void ordenarPartidosCompeticion() {
		for (int i = 0; i < equipos.size(); i++) {
			for (int j = 0; j < equipos.size(); j++) {
				if (i!=j) {
					Partido partido = new Partido(equipos.get(i), equipos.get(j));
					partidos.add(partido);
				}
			}
		}
	}
	
	/**
	 * Primero llamamos al metodo ordenarPartidosCompeticion()
	 * para que genere los partidos a jugar.
	 * Cogemos el @param partidos y lo recorremos de partido en partido
	 * calculando quien es el ganador o si hay empates a través de @method 
	 * calcularGanador de @class Partido y donde les anotará los puntos 
	 * obtenidos a cada uno.
	 * 
	 */
	public static void jugarLiga() {
		ordenarPartidosCompeticion();
		for (Partido partido : partidos) {
			partido.calcularGanador();
		}
		crearClasificacion();
	}
	
	/**
	 * Este metodo va a crear los ArrayList de entrenadores
	 * jugadores y equipos a traves de la llamada de los
	 * metodos que le corresponda.
	 */
	public void crearDatosEquipos (int numEquipos) {
		this.numEquipos = numEquipos;
		ArrayList<Entrenador> entrenadores = crearEntrenadores();
		ArrayList<Futbolista> jugadores = crearJugadores();
		crearEquipos(entrenadores,jugadores);
	}
	
	//Implementar todas estas funciones.
	/**
	 * 
	 * @return ArrayList <Entrenador>
	 * 
	 * Generamos ArrayList de entrenadores y definimos un Entrenador.
	 * Abrimos un bucle con el numero de equipos que vamosa tener,
	 * y en cada iteración vamos definiendo el Entrenador
	 * y añadiendolo al ArrayList, los datos 
	 * del Entrenador nos los proporcionan otros metodos.
	 */
	private ArrayList<Entrenador> crearEntrenadores() {
		
		ArrayList<Entrenador> entrenadores = new ArrayList<>();
		Entrenador entrenador;
		
		for (int i = 0; i < numEquipos; i++) {
			entrenadores.add(entrenador = new Entrenador(ponerNombre(), ponerFnacimiento(true), altura(), peso(), ("Equipo"+i), sueldo(), 0, caracteristicas(), caracteristicas(), caracteristicas()));
		}
		return entrenadores;
	}

	/**
	 * 
	 * @return ArrayList <Futbolista>
	 * 
	 * Generamos ArrayList de jugadores y definimos un futbolista.
	 * abrimos un bucle con el numero de equipos que queremos generar
	 * y un segundo bloque con la cantidad de jugadores que queremos 
	 * tener en cada equipo, y en cada iteración vamos creando los 
	 * futbolistas y añadiendolos al ArrayList, según la posicion del 
	 * bucle de futbolistas creará un portero, delantero... y los datos 
	 * de los futbolistas nos los proporcionan otros metodos.
	 */
	private ArrayList<Futbolista> crearJugadores() {
		
		ArrayList<Futbolista> jugadores = new ArrayList<>();
		Futbolista futbolista = new Futbolista();
		
		for (int i = 0; i < numEquipos; i++) {
			for (int j = 0; j < 22; j++) {
				if (j<4) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "delantero", caracteristicas(), categoria()));
				} else if (j>3 && j<12) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "centrocampista", caracteristicas(), categoria()));
				} else if (j>11 && j<20) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "defensa", caracteristicas(), categoria()));
				} else if (j>19 && j<22) {
					jugadores.add(futbolista = new Futbolista(altura(), peso(), ponerFnacimiento(false), ponerNombre(), j, ("Equipo"+i), sueldo(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), caracteristicas(), "portero", caracteristicas(), categoria()));
				}
			}
		}
		System.out.println("Creados");
		return jugadores;
	}
	
	/**
	 * @param entrenadores
	 * Contiene todos los entrenadores que habrá en los equipos.
	 * @param jugadores
	 * Contiene todos los jugadores que habrá en los equipos.
	 * 
	 * Bucle que nos indica la cantidad de equipos que hay y en 
	 * cada iteracion crea un nuevo equipo con un nombre, fecha de fundacion 
	 * aleatoria y un nombre de presidente. Se le añade al equipo el entrenador
	 * que corresponde que coincide con el equipo. Y se genera un segundo bucle
	 * para ir añadiendo los jugadores que pertenecen a ese equipo. Por ultimo
	 * añadimos el equipo al ArrayList de equipos.
	 */
	public void crearEquipos(ArrayList<Entrenador> entrenadores, ArrayList<Futbolista> jugadores) {
		
		for (int i = 0; i < numEquipos; i++) {
			Equipo equipo = new Equipo(("Equipo"+i), ponerFnacimiento(false), ponerNombre());
			equipo.setEntrenador(entrenadores.get(i));
			ArrayList<Futbolista> futbolistas = new ArrayList<>();
			for (int j = 0; j < jugadores.size(); j++) {
				
				System.out.println("se añade un jugador al equipo" + jugadores.get(j).getNomEquipo());

				if (entrenadores.get(i).getNomEquipo().equals( jugadores.get(j).getNomEquipo())) {
					System.out.println("se añade un jugador al equipo" + jugadores.get(j).getNomEquipo());
					futbolistas.add(jugadores.get(j));
					equipo.setaEquipo(futbolistas);
				}
			}
			equipos.add(equipo);
		}
	}

	/**
	 * @param ArrayList nombres
	 * Alberga una serie de nombres disponibles.
	 * @return String
	 * 
	 * Se genera un número aleatorio que es el indice del ArrayList
	 * de nombres y devuelve es nombre.
	 */
	private String ponerNombre () {
		ArrayList<String> nombres = new ArrayList<String>(Arrays.asList( "Alejandro", "Alex", "Alfonso", "Carlo", "Carlos", "David", "Domingo", "Fermín", "Ismael", "Israel", "Jaimito", "James", "Javier", "Juan", "JuanCarlos", "Pedro", "Pep", "Pepe", "Santos", "Sergio", "Tiverius", "Tomás", "Vicent", "Vicente" ));
		int num = (int) Math.round(Math.random()*23);
		return nombres.get(num);
	}
	
	/**
	 * @param bool
	 * Nos indicara si la fecha de nacimiento es para un 
	 * entrenador (true) o para un jugador (false)
	 * @param nacimineto
	 * Calcula la fecha de nacimiento en funcion de los años
	 * que le pasemos. 
	 * 
	 * @return LocalDate
	 * 
	 * Recepcionamos un true/false, lo pasamos al if para que
	 * nos devuelva un valor y se lo entregamos a nacimiento
	 * para que nos calcula la fecha de nacimiento.
	 */
	private LocalDate ponerFnacimiento (boolean bool) {
		int num;
		LocalDate nacimiento = LocalDate.now();
		if (bool) {
			num = (int) Math.round(Math.random()*(55-30)+30);
		} else {
			num = (int) Math.round(Math.random()*(35-18)+18);
		}
		 nacimiento = nacimiento.minusYears(num);
		return nacimiento;
	}
	
	/**
	 * @return float
	 *  Nos devuelve una altura entre 1.60 metros y 2.00 metros.
	 */
	private float altura () {
		return (float) (Math.random()*(2.00-1.60)+1.60);
	}
	
	/**
	 * @return float
	 *  Nos devuelve un peso entre 60 Kilogramos y 100 kilogramos.
	 */
	private float peso () {
		return(float) (Math.random()*(100-60)+60);
	}
	
	/**
	 * @return float
	 * 
	 * Nos devuelve un sueldo mensual entre 1500€ y 5000€.
	 */
	private float sueldo () {
		return (float) (Math.random()*(5000-1500)+1500);
	}
	
	/**
	 * @return int
	 *  Nos devuelve una característica X entre 1 y 100.
	 */
	private int caracteristicas () {
		return (int) Math.round(Math.random()*100+1);
	}

	/**
	 * @return String
	 * 
	 * Escoge aleatoriamente entre una de las categorías 
	 * disponibles y la retorna.
	 */
	private String categoria () {
		String [] cat = {"baja", "media", "alta", "estrella"};
		int indice = (int) Math.round(Math.random()*3);
		return cat[indice];
	}
	
	/**
	 * Genera la clasificacion de la competicion
	 * a traves de la recogida de los equipos y añadiendolos
	 * al ArrayList clasificacion para posteriormente 
	 * ordenarlos a traves de Collections sort.
	 */
	public static void crearClasificacion() {
		
		for (Equipo equipo : equipos) {
			clasificacion.add(equipo);
		}
		Collections.sort(clasificacion);
		guardarClasificacion();
	}

	/**
	 * 
	 * @return String
	 * 
	 * Genera una seduoTabla con la clasificacion de la 
	 * competicion extraida del ArrayList clasificacion
	 * y la devuelve en formato String.
	 */
	public static String mostrarClasificacion() {
		String clasificacionOrdenada = "Equipo \t Ganados \t Perdidos \t Empatados \t Puntuación"+System.lineSeparator();
		for (Equipo equipo : clasificacion) {
			clasificacionOrdenada += equipo.getNombre()+" \t "+equipo.getPatidosGanados()+" \t "+equipo.getPartidosPerdidos()+" \t "+equipo.getPartidosEmpatados()+" \t "+equipo.getPuntos()+System.lineSeparator();
		}
		return clasificacionOrdenada;
	} //muestra por pantalla la tabla de clasificación

	/**
	 * Generamos un fichero de texto con el nombre de 
	 * la competicion y le añadimos la seudoTabla que
	 * nos devuelve el metodo mostrarClasificacion().
	 */
	public static void guardarClasificacion() {
		
		FileWriter fw = null;
		PrintWriter salida = null;
		
		try {
			fw = new FileWriter(nombreArchivo, true);
			salida = new PrintWriter(fw);
			salida.println(mostrarClasificacion());
			
			salida.flush();
	            
	        } catch (IOException ex) {
	            Logger.getLogger(Competicion.class.getName()).log(Level.SEVERE, null, ex);
	        } finally {
	        }
		
	} //guarda en archivo de texto la tabla de clasificación 
	
	/**
	 * Cogemos el ArrayList clasificacion que tiene la 
	 * clasificacion ordenada de maxima puntuacion a menor
	 * y 
	 * 
	 */
	public void asignarTitulo() {
		Competicion.clasificacion.get(0).anyadirTitulo(titulo);
	}
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada jugador 
	 * que participa en la competición. Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarJugadores() {} 
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada entrenador 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarEntrenadores() {}
	
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada Equipo 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarEquipos() {}

	public static ArrayList<Equipo> getEquipos() {
		return equipos;
	}

	public static ArrayList<Partido> getPartidos() {
		return partidos;
	}


	public ArrayList<Equipo> getClasificacion() {
		return clasificacion;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public int getNumEquipos() {
		return numEquipos;
	}
	
}

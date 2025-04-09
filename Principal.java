import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) {
		final int N_EQUIPOS_MAX = 15, CANT_ATRIBUTOS = 8;
		final int ID_MIN = 10;
		final int ID_MAX = 8000;
		String[][] equipos = new String[N_EQUIPOS_MAX][CANT_ATRIBUTOS];
		Scanner s = new Scanner(System.in);
		int cantEquipos = 0;
		
		int opc = 0;
		do {
			opc = mostrarMenuYElegirOpcion(s);
			cantEquipos = generarAccion(s,opc,equipos, cantEquipos, ID_MIN, ID_MAX);
		} while(opc != 11);
	}

	private static int ingresarEntero(final int MAX, final int MIN, Scanner s) {
        int nro =0;
        boolean error = false;
        do{
			error = false;
            try {
            	nro = s.nextInt();
            	if (nro>MAX || nro < MIN) {
            	    error =true;
            	    System.out.println("Error, el numero ingresado debe estar entre " + MIN + " y " + MAX);
            	    System.out.println("Ingrese otro numero");
            	}
            } catch (InputMismatchException e) {
            	System.out.println("Error. Tipo de dato mal ingresado");
            	System.out.println("Vuelva a ingresar el dato");
				error = true;
            } catch(Exception e){
                System.out.println("Error inesperado ");
            } finally{
                s.nextLine();
            }
        }while(error);
        return nro;
	}

	public static int mostrarMenuYElegirOpcion(Scanner s) {
		System.out.println("Bienvenido al software de e-sports");
		System.out.println("1)  Registrar equipo\n");
		System.out.println("2)  Consultar equipo\n");
		System.out.println("3)  Modificar equipo\n");
		System.out.println("4)  Eliminar equipo\n");
		System.out.println("5)  Listar todos los equipos\n");
		System.out.println("6)  Registrar resultados de partido\n");
		System.out.println("7)  Buscar equipos por videojuego\n");
		System.out.println("8)  Buscar equipos por país\n");
		System.out.println("9)  Ver tabla de posiciones\n");
		System.out.println("10) Calcular estadísticas del torneo\n");
		System.out.println("11) Salir\n");
		return ingresarEntero(11,1,s);
	}

	public static int generarAccion(Scanner s, final int OPC, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX) {
		switch(OPC){
			case 1:
				if(cantEquipos < EQUIPOS.length) {
					cantEquipos = ingresarEquipo(s,EQUIPOS, cantEquipos, ID_MIN, ID_MAX);
				}
		}
		return cantEquipos;
	}

	public static int ingresarEquipo(Scanner s, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX) {
		// ID
		int indiceIdBuscado = -1;
		final int idEquipo = 0;
		do {
			System.out.println("Ingrese el ID del equipo: ");
			idEquipo = ingresarEntero(ID_MAX, ID_MIN, s);
			indiceIdBuscado = buscarIdEnMatriz(EQUIPOS, idEquipo, cantEquipos);

			if(indiceIdBuscado >= 0) {
				System.out.println("Esta id del equipo ya fue asignado a otro. Por favor, ingrese nuevamente");
			}
		}while(indiceIdBuscado >= 0);

		// NOMBRE
		int indiceNombreBuscado = -1;
		String nombreEquipo;

		do {
			System.out.println("Ingrese el nombre del equipo");
			nombreEquipo = s.nextLine();
			indiceNombreBuscado = buscarCadenaEnMatriz(EQUIPOS,nombreEquipo,cantEquipos,1);

			if(indiceNombreBuscado >= 0) {
				System.out.println("Este nombre de equipo ya fue asignado a otro. Por favor, ingrese uno nuevo: ");
			}


		}while(indiceNombreBuscado >= 0);
		
		// VIDEOJUEGO
		final int MAX_JUEGOS =7, MIN_JUEGOS =1;
		System.out.println("Ingrese el videojuego en el que desea anotar a su equipo:");
		System.out.println("1 - League Of Legends");
		System.out.println("2 - Counter-Strike");
		System.out.println("3 - Dota 2");
		System.out.println("4 - Valorant");
		System.out.println("5 - Fortnite");
		System.out.println("6 - FIFA");
		System.out.println("7 - Otros");
		final int VIDEOJUEGO=ingresarEntero(MAX_JUEGOS, MIN_JUEGOS, s);
		

		// PAIS
		String pais;
		boolean validezPais;
		do {
			System.out.println("Ingrese el pais de origen de su equipo");
			pais = s.nextLine();
			validezPais = comprobarCadena(pais);
			if (!validezPais) {
				System.out.println("El pais ingresado es invalido. Intente ingresando solo texto");
			}
			
		}while (!validezPais);
		//NUMERO DE JUGADORES
		System.out.println("Ingrese el numero de jugadores");
		final int NUMERO_JUGADORES;
		final int MAX_JUGADORES =10, MIN_JUGADORES = 1;
		NUMERO_JUGADORES = ingresarEntero(MAX_JUGADORES, MIN_JUGADORES, s);
		//PARTIDOS GANADOS


		EQUIPOS[cantEquipos][0] = String.valueOf(idEquipo);
		EQUIPOS[cantEquipos][1] = nombreEquipo;
		EQUIPOS[cantEquipos][2] = String.valueOf(VIDEOJUEGO);
		EQUIPOS[cantEquipos][3] = pais;
		EQUIPOS[cantEquipos][4] = String.valueOf(NUMERO_JUGADORES);
		EQUIPOS[cantEquipos][5] = ;
		return ++cantEquipos;
	}
	public static int buscarIdEnMatriz(final String[][] EQUIPOS, final int BUSCADO, final int LONGITUD) {
		int i = 0;
		while(i<LONGITUD) {
			if(Integer.parseInt(EQUIPOS[i][0]) == BUSCADO) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static int buscarCadenaEnMatriz(final String[][] EQUIPOS, final String BUSCADO, final int LONGITUD, final int IND_COL) {
		int i = 0;
		while(i<LONGITUD) {
			if(EQUIPOS[i][IND_COL].toLowerCase().equals(BUSCADO.toLowerCase())) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static void consultarEquipo(Scanner s, final String[][] EQUIPOS, final int ID_EQUIPO_CONSULTADO, final int ID_MIN, final int ID_MAX){
		System.out.println("Ingrese la id del equipo del cual quiere consultar los datos");
		

	}
	public static void mostrarDatoEquipo(final String[][] EQUIPOS, final int INDICE) {
		System.out.println("ID. " + EQUIPOS[INDICE][0]);
		System.out.print(" Nombre. "+ EQUIPOS[INDICE][1]);
		System.out.print(" Videojuego. "+ EQUIPOS[INDICE][2]);
		System.out.println("Pais. " + EQUIPOS[INDICE][3]);
		System.out.println("Numero de Jugadores. " + EQUIPOS[INDICE][4]);
		System.out.println("Partidos Ganados. " + EQUIPOS[INDICE][5]);
		System.out.println("Partidos Perdidos. " + EQUIPOS[INDICE][6]);
		System.out.println("Puntos totales. " + EQUIPOS[INDICE][7]);

	}

 public static boolean comprobarCadena(final String CADENA){
		if(CADENA.isEmpty()){
			return false;
		}
		for(int i = 0; i<CADENA.length();i++){
			if(Character.isDigit(CADENA.charAt(i))){
                return false;
            }
		}

		
		return true;
	}
	
	// public static int validarId(Scanner s, final int ID_MAX, final int ID_MIN){
	// 	int validado = 0;
	// 	do
	// 		int id = s.nextInt();
	// 	if (id < ID_MIN || id > ID_MAX) {
	// 		System.out.println("La id no es valida ,ingrese una entre"+ ID_MIN + " y " + ID_MAX);
	// 	}else{
	// 		return id;
	// 	}
	// }

	

}








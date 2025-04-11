import java.util.InputMismatchException;
import java.util.Scanner;




public class Principal{

	public static void main(String[] args) {
		final int N_EQUIPOS_MAX = 15, CANT_ATRIBUTOS = 8;
		final int ID_MIN = 10;
		final int ID_MAX = 8000;
		final int MAX_JUEGOS = 7, MIN_JUEGOS = 1;
		final String[] VIDEOJUEGOS = new String[]{"League Of Legends", "Counter Strike", "Dota 2", "Valorant", "Fortnite", "FIFA", "Otros" };
		String[][] equipos = new String[N_EQUIPOS_MAX][CANT_ATRIBUTOS];
		
		Scanner s = new Scanner(System.in);
		int cantEquipos = 0;
		
		int opc = 0;
		do {
			opc = mostrarMenuYElegirOpcion(s);
			cantEquipos = generarAccion(s,opc,equipos, cantEquipos, ID_MIN, ID_MAX, VIDEOJUEGOS,MAX_JUEGOS, MIN_JUEGOS);
		} while(opc != 11);
	}

	public static int ingresarEquipo(Scanner s, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX, final String[] VIDEOJUEGOS, final int MAX_JUEGOS, final int MIN_JUEGOS) {
		// ID
		int indiceIdBuscado = -1;
		int idEquipo = 0;
		do {
			System.out.println("Ingrese el ID del equipo: ");
			idEquipo = ingresarEntero(ID_MAX, ID_MIN, s);
			indiceIdBuscado = buscarEquipo(EQUIPOS, idEquipo, cantEquipos);

			if(indiceIdBuscado >= 0) {
				System.out.println("Esta id del equipo ya fue asignado a otro. Por favor, ingrese nuevamente");
			}
		}while(indiceIdBuscado >= 0);

		// NOMBRE
		int indiceNombreBuscado = -1;
		String nombreEquipo;
		do {
			System.out.println("Ingrese el nombre del equipo");
			nombreEquipo = ingresarCadena(s);
			indiceNombreBuscado = buscarCadenaEnMatriz(EQUIPOS,nombreEquipo,cantEquipos,1);

			if(indiceNombreBuscado >= 0) {
				System.out.println("Este nombre de equipo ya fue asignado a otro. Por favor, ingrese uno nuevo: ");
			}


		}while(indiceNombreBuscado >= 0);
		
		// VIDEOJUEGO
		
		System.out.println("Ingrese el videojuego en el que desea anotar a su equipo:");
		for(int i = 0; i<VIDEOJUEGOS.length; i++){
			System.out.println(i+1+". "+VIDEOJUEGOS[i]);
		}

		final int VIDEOJUEGO=ingresarEntero(MAX_JUEGOS, MIN_JUEGOS, s);
	
		

		// PAIS
		String pais;
		boolean validezPais;
		
			System.out.println("Ingrese el pais de origen de su equipo");
			pais = ingresarCadena(s);
			
		
		//NUMERO DE JUGADORES
		System.out.println("Ingrese el numero de jugadores");
		final int NUMERO_JUGADORES;
		final int MAX_JUGADORES =10, MIN_JUGADORES = 1;
		NUMERO_JUGADORES = ingresarEntero(MAX_JUGADORES, MIN_JUGADORES, s);
		//PARTIDOS GANADOS
		System.out.println("ingrese los partidos ganados");
		int partidosGanados;
		final int PARTIDOS_GANADOS_MIN = 0, PARTIDOS_GANADOS_MAX = 99;
		partidosGanados=ingresarEntero(PARTIDOS_GANADOS_MAX,PARTIDOS_GANADOS_MIN , s);
		//PARTIDOS PERDIDOS
		System.out.println("ingrese los partidos perdidos");
		int partidosPerdidos;
		final int PARTIDOS_PERDIDOS_MIN = 0, PARTIDOS_PERDIDOS_MAX = 99;
		partidosPerdidos=ingresarEntero(PARTIDOS_PERDIDOS_MAX, PARTIDOS_PERDIDOS_MIN, s);
		//PUNTOS TOTALES
		int puntosEquipo=partidosGanados*3;


		EQUIPOS[cantEquipos][0] = String.valueOf(idEquipo);
		EQUIPOS[cantEquipos][1] = nombreEquipo;
		EQUIPOS[cantEquipos][2] = String.valueOf(VIDEOJUEGO);
		EQUIPOS[cantEquipos][3] = pais;
		EQUIPOS[cantEquipos][4] = String.valueOf(NUMERO_JUGADORES);
		EQUIPOS[cantEquipos][5] = String.valueOf(partidosGanados);
		EQUIPOS[cantEquipos][6] = String.valueOf(partidosPerdidos);
		EQUIPOS[cantEquipos][7] = String.valueOf(puntosEquipo);
		return ++cantEquipos;
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

	public static int generarAccion(Scanner s, final int OPC, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX, final String[] VIDEOJUEGOS, final int MAX_JUEGOS, final int MIN_JUEGOS) {
		switch(OPC){
			case 1:
				if(cantEquipos < EQUIPOS.length) {
					cantEquipos = ingresarEquipo(s,EQUIPOS, cantEquipos, ID_MIN, ID_MAX, VIDEOJUEGOS, MAX_JUEGOS, MIN_JUEGOS);
				}
			break;
			case 2:
				consultarEquipo(s,EQUIPOS, cantEquipos, ID_MAX, ID_MIN, VIDEOJUEGOS);
			break;
			case 5: // CONSULTAR TODOS LOS EQUIPOS
				System.out.println("Los equipos registrados son: ");
				for(int i = 0; i < cantEquipos; i++) {
					mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
					System.out.println("-------------------------------------------");
				}
			break;
			case 7:
			buscarPorVideojuego(s, EQUIPOS, VIDEOJUEGOS, MAX_JUEGOS, 1, cantEquipos);
			break;
			case 8:
			buscarPorPais(s, EQUIPOS, VIDEOJUEGOS, cantEquipos);
			break;
		}
		return cantEquipos;
	}

	
	public static int buscarEquipo(final String[][] EQUIPOS, final int BUSCADO, final int LONGITUD) {
		int i = 0;
		while(i<LONGITUD) {
			if(Integer.parseInt(EQUIPOS[i][0]) == BUSCADO) { //indice 0 = id. Busca por gigante de hierro
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

	public static void consultarEquipo(Scanner s, final String[][] EQUIPOS, final int N_EQUIPOS_MAX, int ID_MAX, int ID_MIN, final String[] VIDEOJUEGOS) {
		System.out.println("Ingrese la id del equipo del cual quiere consultar los datos");
		final int INDICE;
		final int CONSULTADO;
		CONSULTADO = ingresarEntero(ID_MAX, ID_MIN, s);
		INDICE=buscarEquipo(EQUIPOS, CONSULTADO, N_EQUIPOS_MAX);
		mostrarDatoEquipo(EQUIPOS, INDICE, VIDEOJUEGOS);
		

	}
	public static void mostrarDatoEquipo(final String[][] EQUIPOS, final int INDICE, final String[] VIDEOJUEGOS) {
		System.out.println("ID. " + EQUIPOS[INDICE][0]);
		System.out.println("NOMBRE. "+ EQUIPOS[INDICE][1]);
		System.out.println("VIDEOJUEGO. "+ VIDEOJUEGOS[Integer.parseInt(EQUIPOS[INDICE][2]) -1]);
		System.out.println("PAIS. " + EQUIPOS[INDICE][3]);
		System.out.println("NUMERO DE JUGADORES. " + EQUIPOS[INDICE][4]);
		System.out.println("PARTIDOS GANADOS. " + EQUIPOS[INDICE][5]);
		System.out.println("PARTIDOS PERDIDOS. " + EQUIPOS[INDICE][6]);
		System.out.println("PUNTOS TOTALES. " + EQUIPOS[INDICE][7]);
		System.out.println("------------------------------------------------------------------------");

	}

	public static String ingresarCadena(Scanner s) {
        String cadena;
        boolean error;

        do {
            error = false;
            try {
                cadena = s.nextLine();

                if (cadena.trim().isEmpty()) {
                    error = true;
                    System.out.println("Error: La cadena ingresada está vacía. Ingrese otra cadena.");
                }

               
                else if (cadena.matches("\\d+")) {
                    error = true;
                    System.out.println("Error: La cadena no puede ser solo números. Ingrese otra cadena.");
                }
            } catch (Exception e) {
                System.out.println("Error inesperado");
                error = true;
                cadena = "";
            }
        } while (error);

        return cadena;
    }

	public static void buscarPorVideojuego(Scanner s, final String[][] EQUIPOS,final String[] VIDEOJUEGOS,final int MAX, final int MIN, final int CANT_EQUIPOS) {
		System.out.println("Ingrese el videojuego por el cual buscar:");
		for(int i = 0; i < VIDEOJUEGOS.length; i++){
			System.out.println(i + 1 + ". "+ VIDEOJUEGOS[i]);
		}
		final int JUEGO_BUSCADO=ingresarEntero(MAX, MIN, s);

		for(int i = 0; i < CANT_EQUIPOS; i++){

			if(JUEGO_BUSCADO == Integer.parseInt(EQUIPOS[i][2])) {
				mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
			}
		}
	}
	public static void buscarPorPais(Scanner s, final String[][] EQUIPOS, final String[] VIDEOJUEGOS, final int CANT_EQUIPOS) {
		System.out.println("Ingrese el pais por el cual buscar:");
		
		final String PAIS_BUSCADO = ingresarCadena(s);
		for(int i = 0; i < CANT_EQUIPOS; i++){

			if(PAIS_BUSCADO.toLowerCase().equals((EQUIPOS[i][3]).toLowerCase()))  {
				mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
			}
		}
		
	}


	public static int ingresarEntero(final int MAX, final int MIN, Scanner s) {
        int nro =0;
        boolean error = false;
        do{
			error = false;
            try {
            	nro = s.nextInt();
            	if (nro>MAX || nro < MIN) {
            	    error =true;
            	    System.out.println("Error, el numero ingresado debe estar entre " + MIN + " y " + MAX);
            	    System.out.println("Ingrese otro número");
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
	public static float calcularRatio(int partidosGanados, int partidosPerdidos){
		float ratio;
		ratio = ((float) partidosGanados)/ partidosPerdidos;
		if (partidosPerdidos == 0) {
			return partidosGanados;			
		}
		return ratio;
	}
	
	private static void modificarEquipo(Scanner s, String[][] EQUIPOS,final int cantEquipos, int idEquipo, int ID_MAX, int ID_MIN) {
		System.out.println("Ingrese el ID del equipo que desea modificar: ");
		idEquipo = ingresarEntero(ID_MAX, ID_MIN, s);
		



	

	}
	
	
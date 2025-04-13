import java.util.InputMismatchException;
import java.util.Scanner;




public class Principal{

	public static void main(String[] args) {
		//CONSTANTES
		final int N_EQUIPOS_MAX = 15, CANT_ATRIBUTOS = 8;
		final int ID_MIN = 10, ID_MAX = 8000;
		final int MIN_JUEGOS = 1, MAX_JUEGOS = 7;
		final String[] VIDEOJUEGOS = new String[]{"League Of Legends", "Counter Strike", "Dota 2", "Valorant", "Fortnite", "FIFA", "Otros" };
		final String[][] EQUIPOS = new String[N_EQUIPOS_MAX][CANT_ATRIBUTOS];
		final int MIN_JUGADORES = 1, MAX_JUGADORES = 10;
		final boolean MODO_PRUEBA = true;

		Scanner s = new Scanner(System.in);
		int cantEquipos = 0;
		// CARGAR DATOS DE PRUEBA SI MODO_PRUEBA ESTA ACTIVADO
        if (MODO_PRUEBA) {
            cantEquipos = cargarDatosPrueba(EQUIPOS, VIDEOJUEGOS);
        }

		int opc;
		do {
			opc = mostrarMenuYElegirOpcion(s);
			cantEquipos = generarAccion(s,opc,EQUIPOS, cantEquipos, ID_MIN, ID_MAX, VIDEOJUEGOS,MAX_JUEGOS, MIN_JUEGOS, MAX_JUGADORES, MIN_JUGADORES);
		} while(opc != 11);

		s.close();
		System.out.println("Cerrando programa...");
	}


	//DATOS PARA HACER PRUEBAS Y NO INGRESAR MANUALMENTE LOS MISMOS
	public static int cargarDatosPrueba(String[][] EQUIPOS, final String[] VIDEOJUEGOS) {
        EQUIPOS[0] = new String[]{"101", "Team A", "1", "Argentina", "5", "10", "2", "30"};
    	EQUIPOS[1] = new String[]{"102", "Team B", "2", "Brasil", "6", "8", "4", "24"};
    	EQUIPOS[2] = new String[]{"103", "Team C", "3", "Chile", "7", "6", "6", "18"};
    	EQUIPOS[3] = new String[]{"104", "Team D", "4", "Peru", "5", "4", "8", "12"};
    	EQUIPOS[4] = new String[]{"105", "Team E", "5", "Colombia", "6", "2", "10", "6"};
    	EQUIPOS[5] = new String[]{"106", "Team F", "6", "Mexico", "8", "12", "3", "36"};
    	EQUIPOS[6] = new String[]{"107", "Team G", "7", "USA", "9", "15", "1", "45"};
    	EQUIPOS[7] = new String[]{"108", "Team H", "1", "Canada", "4", "7", "5", "21"};
    	EQUIPOS[8] = new String[]{"109", "Team I", "2", "Spain", "5", "9", "4", "27"};
    	EQUIPOS[9] = new String[]{"110", "Team J", "3", "France", "6", "11", "3", "33"};
    	EQUIPOS[10] = new String[]{"111", "Team K", "4", "Germany", "7", "13", "2", "39"};
    	EQUIPOS[11] = new String[]{"112", "Team L", "5", "Italy", "8", "14", "1", "42"};
    	EQUIPOS[12] = new String[]{"113", "Team M", "6", "Japan", "5", "10", "3", "30"};
    	EQUIPOS[13] = new String[]{"114", "Team N", "7", "South Korea", "6", "12", "2", "36"};
    	EQUIPOS[14] = new String[]{"115", "Team O", "1", "Australia", "4", "8", "4", "24"};

        System.out.println("Datos de prueba cargados exitosamente.");
        return 15;
    }

	public static int ingresarEquipo(Scanner s, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX, final String[] VIDEOJUEGOS, final int MAX_JUEGOS, final int MIN_JUEGOS,final int MAX_JUGADORES, final int MIN_JUGADORES) {
		// ID
		int indiceIdBuscado;
		int idEquipo = 0;
		do {
			System.out.println("Ingrese el ID del equipo: ");
			idEquipo = ingresarEntero(ID_MAX, ID_MIN, s);
			indiceIdBuscado = buscarEquipo(EQUIPOS, idEquipo, cantEquipos); // Si devuelve -1, se puede usar

			if(indiceIdBuscado >= 0) {
				System.out.println("Esta id del equipo ya fue asignado a otro. Por favor, ingrese nuevamente");
			}
		}while(indiceIdBuscado >= 0);

		// NOMBRE
		int indiceNombreBuscado;
		String nombreEquipo;
		do {
			System.out.println("Ingrese el nombre del equipo");
			nombreEquipo = ingresarCadena(s);
			indiceNombreBuscado = buscarCadenaEnMatriz(EQUIPOS,nombreEquipo,cantEquipos,1); // Busca que no se repita, si devuelve -1 se puede usar, si devuelve un numero, ya existe

			if(indiceNombreBuscado >= 0) {
				System.out.println("Este nombre de equipo ya fue asignado a otro. Por favor, ingrese uno nuevo: ");
			}


		}while(indiceNombreBuscado >= 0);
		
		// VIDEOJUEGO
		
		System.out.println("Ingrese el videojuego en el que desea anotar a su equipo:");
		for(int i = 0; i < VIDEOJUEGOS.length; i++){
			System.out.println(i + 1 + ". " + VIDEOJUEGOS[i]);
		}
		System.out.println("\n");

		final int VIDEOJUEGO = ingresarEntero(MAX_JUEGOS, MIN_JUEGOS, s);
	
		

		// PAIS
		String pais;
		
			System.out.println("Ingrese el pais de origen de su equipo");
			pais = ingresarCadena(s);
			
		
		//NUMERO DE JUGADORES
		System.out.println("Ingrese el numero de jugadores");
		final int NUMERO_JUGADORES;
		
		NUMERO_JUGADORES = ingresarEntero(MAX_JUGADORES, MIN_JUGADORES, s);
		//PARTIDOS GANADOS
		System.out.println("ingrese los partidos ganados");
		int partidosGanados;
		final int PARTIDOS_GANADOS_MIN = 0, PARTIDOS_GANADOS_MAX = 99;
		partidosGanados = ingresarEntero(PARTIDOS_GANADOS_MAX,PARTIDOS_GANADOS_MIN , s);
		//PARTIDOS PERDIDOS
		System.out.println("ingrese los partidos perdidos");
		int partidosPerdidos;
		final int PARTIDOS_PERDIDOS_MIN = 0, PARTIDOS_PERDIDOS_MAX = 99;
		partidosPerdidos = ingresarEntero(PARTIDOS_PERDIDOS_MAX, PARTIDOS_PERDIDOS_MIN, s);
		//PUNTOS TOTALES
		int puntosEquipo = partidosGanados*3;

		//PASAR LOS RESPECTIVOS VALORES A CADA EQUIPO
		EQUIPOS[cantEquipos][0] = String.valueOf(idEquipo);
		EQUIPOS[cantEquipos][1] = nombreEquipo;
		EQUIPOS[cantEquipos][2] = String.valueOf(VIDEOJUEGO);
		EQUIPOS[cantEquipos][3] = pais;
		EQUIPOS[cantEquipos][4] = String.valueOf(NUMERO_JUGADORES);
		EQUIPOS[cantEquipos][5] = String.valueOf(partidosGanados);
		EQUIPOS[cantEquipos][6] = String.valueOf(partidosPerdidos);
		EQUIPOS[cantEquipos][7] = String.valueOf(puntosEquipo);
		//DEVOLVER CANTEQUIPOS + 1
		return ++cantEquipos;
	}

	public static int mostrarMenuYElegirOpcion(Scanner s) {
		//SE MUESTRA EL MENU 
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
		imprimirGuiones();
		//SE PIDE UN ENTERO
		return ingresarEntero(11,1,s);
	}

	public static int generarAccion(Scanner s, final int OPC, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX, final String[] VIDEOJUEGOS, final int MAX_JUEGOS, final int MIN_JUEGOS, final int MAX_JUGADORES, final int MIN_JUGADORES) {
		switch(OPC){
		//OPCION PARA INGRESAR UN EQUIPO
			case 1:
				if(cantEquipos < EQUIPOS.length) {
					cantEquipos = ingresarEquipo(s,EQUIPOS, cantEquipos, ID_MIN, ID_MAX, VIDEOJUEGOS, MAX_JUEGOS, MIN_JUEGOS, MAX_JUGADORES, MIN_JUGADORES);
				}
				else {
					System.out.println("No se pueden agregar más equipos. Límite alcanzado.");
				}
			break;
		//OPCION PARA CONSULTAR UN EQUIPO
			case 2:
				consultarEquipo(s,EQUIPOS, cantEquipos, ID_MAX, ID_MIN, VIDEOJUEGOS);
			break;
		//OPCION PARA MODIFICAR UN EQUIPO
			case 3:
				modificarEquipo(s,EQUIPOS,cantEquipos,ID_MAX, ID_MIN, MAX_JUEGOS, MIN_JUEGOS, VIDEOJUEGOS, MAX_JUGADORES, MIN_JUGADORES);
			break;
		//OPCION PARA ELIMINAR UN EQUIPO
			case 4:
				cantEquipos = eliminarEquipo(s, EQUIPOS, cantEquipos, ID_MIN, ID_MAX);
			break;
		//OPCION PARA LISTAR TODOS LOS EQUIPOS
			case 5: 
				listarEquipos(EQUIPOS, cantEquipos, VIDEOJUEGOS);
			break;
		//OPCION PARA REGISTRAR UN PARTIDO
			case 6:
				registrarPartido(EQUIPOS, cantEquipos, s);
			break;
		//OPCION PARA BUSCAR EQUIPOS POR VIDEOJUEGOS
			case 7:
				buscarPorVideojuego(s, EQUIPOS, VIDEOJUEGOS, MAX_JUEGOS, 1, cantEquipos);
			break;
		//OPCION PARA BUSCAR EQUIPOS POR PAIS
			case 8:
				buscarPorPais(s, EQUIPOS, VIDEOJUEGOS, cantEquipos);
			break;
		//OPCION PARA MOSTRAR LA TABLA DE POSICIONES
			case 9:
				mostrarTablaPosiciones(EQUIPOS, cantEquipos);
			break;
		//OPCION PARA VER LAS ESTADISTICAS
			case 10:
				calcularEstadisticas(EQUIPOS, cantEquipos,VIDEOJUEGOS);
			break;
		}
		return cantEquipos; 
	}

	
	public static int buscarEquipo(final String[][] EQUIPOS, final int BUSCADO, final int LONGITUD) {
		int i = 0;
		while(i < LONGITUD) {
			if(EQUIPOS[i] == null) { // Si es null, se puede sobreescribir
				return -1;
			}
			if(Integer.parseInt(EQUIPOS[i][0]) == BUSCADO) { //INDICE 0 = ID, BUSCA POR ID
				return i;
			}
			i++;
		}
		return -1;
	}
	public static int buscarCadenaEnMatriz(final String[][] EQUIPOS, final String BUSCADO, final int LONGITUD, final int IND_COL) {
		int i = 0;
		while(i < LONGITUD) {
			if(EQUIPOS[i][IND_COL].equalsIgnoreCase(BUSCADO)) {
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
		INDICE = buscarEquipo(EQUIPOS, CONSULTADO, N_EQUIPOS_MAX);

		if(INDICE == -1 || EQUIPOS[INDICE][0] == null) {
			System.out.println("Ese equipo no existe");
			return;
		}

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
		imprimirGuiones();
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

               
                else if (cadena.matches("\\d+")) { // \\d es una expresion regular que se usa para verificar si la cadena contiene SOLO digitos (0 al 9)
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
			System.out.println(i + 1 + ". " + VIDEOJUEGOS[i]);
		}
		final int JUEGO_BUSCADO=ingresarEntero(MAX, MIN, s);
		
		boolean encontrado = false;
		for(int i = 0; i < CANT_EQUIPOS; i++){
			if(EQUIPOS[i] != null && JUEGO_BUSCADO == Integer.parseInt(EQUIPOS[i][2])) {
				mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
				encontrado = true;
			}
		}

		if(!encontrado) {
			System.out.println("No se encontraron equipos que jueguen a " + VIDEOJUEGOS[JUEGO_BUSCADO-1]);
		}
	}
	public static void buscarPorPais(Scanner s, final String[][] EQUIPOS, final String[] VIDEOJUEGOS, final int CANT_EQUIPOS) {
		System.out.println("Ingrese el pais por el cual buscar:");
		
		final String PAIS_BUSCADO = ingresarCadena(s);

		boolean encontrado = false;
		for(int i = 0; i < CANT_EQUIPOS; i++){

			if(EQUIPOS[i] != null && PAIS_BUSCADO.equalsIgnoreCase((EQUIPOS[i][3])))  {
				mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
				encontrado = true;
			}
		}
		if(!encontrado) {
			System.out.println("No se encontraron equipos de " + PAIS_BUSCADO);
		}
		
	}


	public static int ingresarEntero(final int MAX, final int MIN, Scanner s) {
        int nro = 0;
        boolean error;
        do{
			error = false;
            try {
            	nro = s.nextInt();
            	if (nro > MAX || nro < MIN) {
            	    error = true;
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
	private static float calcularRatio(int partidosGanados, int partidosPerdidos){
		float ratio;
		ratio = ((float) partidosGanados)/ partidosPerdidos; //FORMULA RATIO
		if (partidosPerdidos == 0) {
			return partidosGanados;			
		}
		return ratio;
	}
	
	private static void modificarEquipo(Scanner s, String[][] EQUIPOS, int cantEquipos, final int ID_MAX, final int ID_MIN, final int MAX_JUEGOS,  final int MIN_JUEGOS, final String[] VIDEOJUEGOS, final int MAX_JUGADORES, final int MIN_JUGADORES) {
		System.out.println("Ingrese el ID del equipo que desea modificar: ");
		int idEquipo = ingresarEntero(ID_MAX, ID_MIN, s);
		int indice = buscarEquipo(EQUIPOS, idEquipo, cantEquipos);

		if(indice == -1) { //VERIFICAR QUE EXISTA EL EQUIPO INGRESADO
			System.out.println("Ese equipo no existe");
			return;
		}

		System.out.println("Elija el atributo que desea modificar: ");
		System.out.println("1. Id");
		System.out.println("2. Nombre");
		System.out.println("3. Videojuego");
		System.out.println("4. Pais");
		System.out.println("5. Número de jugadores");
		System.out.println("6. Partidos ganados");
		System.out.println("7. Partidos perdidos");
		final int OPCIONES_MODIFICACION = 7;
		int opcion = ingresarEntero(OPCIONES_MODIFICACION,1,s);

		switch(opcion) {
		//MODIFICAR ID
			case 1:
				System.out.println("Ingrese el nuevo ID del equipo: ");
				int nuevoId;
				do {
				nuevoId = ingresarEntero(ID_MAX,ID_MIN, s);
				if(buscarEquipo(EQUIPOS, nuevoId, cantEquipos) != -1) {
					System.out.println("Este id ya esta en uso. Pruebe otro");
				}
				}while(buscarEquipo(EQUIPOS, nuevoId, cantEquipos) != -1 );
				EQUIPOS[indice][0] = String.valueOf(nuevoId);
				break;
		//MODIFICAR NOMBRE
			case 2:
				System.out.println("Ingrese el nuevo nombre del equipo: ");
				String nuevoNombre;
				do {
				nuevoNombre = ingresarCadena(s);
				if(buscarCadenaEnMatriz(EQUIPOS, nuevoNombre, cantEquipos, 1) != -1) {
					System.out.println("Este nombre ya esta en uso. Pruebe otro");
				}
				}while(buscarCadenaEnMatriz(EQUIPOS, nuevoNombre, cantEquipos, 1) != -1);
				EQUIPOS[indice][1] = nuevoNombre;
				break;
		//MODIFICAR VIDEOJUEGO
			case 3:
				System.out.println("Ingrese el nuevo videojuego del equipo: ");
				for(int i = 0; i<VIDEOJUEGOS.length; i++){
					System.out.println(i + 1 + ". "+ VIDEOJUEGOS[i]);
				}
				int nuevoJuego = ingresarEntero(MAX_JUEGOS, MIN_JUEGOS, s);
				EQUIPOS[indice][2] = String.valueOf(nuevoJuego);
				break;
		//MODIFICAR PAIS
			case 4: 
				System.out.println("Ingrese el nuevo pais del equipo");
				String nuevoPais = ingresarCadena(s);
				EQUIPOS[indice][3]= nuevoPais;
				break;
		//MODIFICAR LA CANTIDAD DE JUGADORES
			case 5:
				System.out.println("Ingrese la cantidad nueva de jugadores");
				int nuevaCantJugadores = ingresarEntero(MAX_JUGADORES, MIN_JUGADORES, s);
				EQUIPOS[indice][4] = String.valueOf(nuevaCantJugadores);
				break;
		//MODIFICAR PARTIDOS GANADOS
			case 6:
				System.out.println("Ingrese la cantidad partidos ganados");
				int nuevaCantPartidosGanados = ingresarEntero(99, 1, s);
				EQUIPOS[indice][5] = String.valueOf(nuevaCantPartidosGanados);
				break;
		//MODIFICAR PARTIDOS PERDIDOS
			case 7:
				System.out.println("Ingrese la cantidad partidos perdidos");
				int nuevaCantPartidosPerdidos = ingresarEntero(99, 1, s);
				EQUIPOS[indice][6] = String.valueOf(nuevaCantPartidosPerdidos);
				break;
		}
		System.out.println("Asi quedaria modificado");
		imprimirGuiones();
		mostrarDatoEquipo(EQUIPOS, indice, VIDEOJUEGOS);

	}
	

	private static int eliminarEquipo(Scanner s, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX) {
		System.out.println("Ingrese el id del equipo a eliminar: ");
		int idBuscado = ingresarEntero(ID_MAX,ID_MIN,s);
		final int idEquipo = buscarEquipo(EQUIPOS, idBuscado, cantEquipos);
		if(idEquipo == -1) { //VERIFICAR QUE EXISTA EL ID DEL EQUIPO
			System.out.println("No se encontró el ID del equipo");
		} else {
			for (int i = idEquipo; i < cantEquipos-1; i++) {
				EQUIPOS[i] = EQUIPOS[i+1];
			}
			EQUIPOS[cantEquipos-1] = new String[8]; //LIMPIAR LA ULTIMA POSICION
			System.out.println("Equipo eliminado exitosamente");
			cantEquipos--;
		}
		
		return cantEquipos;
	}

	private static void registrarPartido(final String[][] EQUIPOS, final int CANT_EQUIPOS, Scanner s) {
		System.out.println("Ingrese el id del equipo que ganó el partido: ");
		//VARIABLES PARA REGISTRAR PARTIDO
		int idGanador = ingresarEntero(8000, 10, s);
		int indiceGanador = buscarEquipo(EQUIPOS, idGanador, CANT_EQUIPOS);
		if(indiceGanador == -1) {  //VERIFICAR QUE EXISTA UN EQUIPO GANADOR
			System.out.println("No se encontró el ID del equipo ganador");
			return;
		}
		System.out.println("Ingrese el id del equipo que perdió el partido: ");
		int idPerdedor = ingresarEntero(8000, 10, s);
		int indicePerdedor = buscarEquipo(EQUIPOS, idPerdedor, CANT_EQUIPOS);
		if(indicePerdedor == -1) {
			System.out.println("No se encontró el ID del equipo perdedor");
			return;
		}
		
		if (idGanador == idPerdedor) {
			System.out.println("Un equipo no puede jugar contra si mismo.");
			return;
		}

		//COMPROBAR QUE JUEGUEN AL MISMO JUEGO
		if(!EQUIPOS[indiceGanador][2].equals(EQUIPOS[indicePerdedor][2])) {
			System.out.println("Los equipos no juegan al mismo videojuego. No se puede registrar el partido.");
			return;
		}

		int partidosGanados = Integer.parseInt(EQUIPOS[indiceGanador][5]);
		int partidosPerdidos = Integer.parseInt(EQUIPOS[indicePerdedor][6]);
		
		EQUIPOS[indiceGanador][5] = String.valueOf(partidosGanados + 1);
		EQUIPOS[indicePerdedor][6] = String.valueOf(partidosPerdidos + 1);
		int puntosGanador = Integer.parseInt(EQUIPOS[indiceGanador][7]);
		EQUIPOS[indiceGanador][7] = String.valueOf(puntosGanador + 3);
		System.out.println("Partido registrado exitosamente");
	}
	
	private static void mostrarTablaPosiciones(final String[][] EQUIPOS, final int cantEquipos) {
		String[][] copia = new String[cantEquipos][8];
		//IMPRIMIR LA TABLA
		for(int i = 0; i < cantEquipos; i++) {
			for(int j = 0; j < 8; j++){
				copia[i][j] = EQUIPOS[i][j];
			}
		}

		for(int i = 0; i < cantEquipos -1; i++) {
			for(int j = 0; j < cantEquipos -i -1; j++) {
				int puntos1 = Integer.parseInt(copia[j][7]);
				int puntos2 = Integer.parseInt(copia[j + 1][7]);

				if(puntos1 < puntos2) {
					String[] temp = copia[j];
					copia[j] = copia[j + 1];
					copia[j + 1] = temp;
				}
			}
		}
		System.out.println("Tabla de posiciones: ");
		for(int i = 0; i < cantEquipos; i++) {
			System.out.println((i+1) + ". Equipo: " + copia[i][1] + " - Puntos: " + copia[i][7]);
		}
		

	}

	private static void calcularEstadisticas(final String[][] EQUIPOS, int cantEquipos, final String[] VIDEOJUEGOS) {
		if(cantEquipos == 0) { //VERIFICAR QUE HAYA ALGUN EQUIPO REGISTRADO
			System.out.println("No hay equipos registrados para calcular estadísticas.");
			return;
		}
		// VARIABLES PARA ESTADISTICAS
		int[] equiposPorVideojuego = new int[VIDEOJUEGOS.length];
		String[] paises = new String[cantEquipos];
		int[] equiposPorPais = new int[cantEquipos];
		int totalVictorias = 0;
		int totalJugadores = 0;
		float mejorRatio = -1;
		float peorRatio = Float.MAX_VALUE;
		String equipoMejorRatio = "";
		String equipoPeorRatio = "";
		int paisCount = 0;

		for(int i = 0; i < cantEquipos; i++) {
			if(EQUIPOS[i] != null) {
				int videojuegoIndex = Integer.parseInt(EQUIPOS[i][2]) - 1;
				equiposPorVideojuego[videojuegoIndex]++;

				String pais = EQUIPOS[i][3];
				boolean paisEncontrado = false;
				for(int j = 0; j < paisCount; j++) {
					if(paises[j].equalsIgnoreCase(pais)) {
						equiposPorPais[j]++;
						paisEncontrado = true;
						break; // BUEN USO DEL BREAK EN UN IF
					}
				}
				if(!paisEncontrado) {
					paises[paisCount] = pais;
					equiposPorPais[paisCount] = 1;
					paisCount++;
				}
				// CALCULAR EL TOTAL DE VICTORIAS
				int victorias= Integer.parseInt(EQUIPOS[i][5]);
				totalVictorias += victorias;
				
				// PROMEDIO DE NUMERO DE JUGADORES
				totalJugadores += Integer.parseInt(EQUIPOS[i][4]);

				// CALCULAR MEJOR Y PEOR RATIO
				int derrotas = Integer.parseInt(EQUIPOS[i][6]);
				float ratio = calcularRatio(victorias, derrotas);
				if(ratio > mejorRatio) {
					mejorRatio = ratio;
					equipoMejorRatio = EQUIPOS[i][1];
				}
				if(ratio < peorRatio) {
					peorRatio = ratio;
					equipoPeorRatio = EQUIPOS[i][1];
				}
			}
		}
		// MOSTRAR ESTADISTICAS
		System.out.println("Estadísticas del torneo:");
		System.out.println("Porcentaje de equipos por videojuego:");
		for (int i = 0; i < VIDEOJUEGOS.length; i++) {
			float porcentaje = (equiposPorVideojuego[i] / (float) cantEquipos) * 100;
			System.out.printf("%s: %.2f%%\n", VIDEOJUEGOS[i], porcentaje);
		}
	
		System.out.println("\nPorcentaje de equipos por país:");
		for (int i = 0; i < paisCount; i++) {
			float porcentaje = (equiposPorPais[i] / (float) cantEquipos) * 100;
			System.out.printf("%s: %.2f%%\n", paises[i], porcentaje);
		}
	
		System.out.printf("\nPromedio de victorias: %.2f\n", totalVictorias / (float) cantEquipos);
		System.out.printf("Promedio de jugadores por equipo: %.2f\n", totalJugadores / (float) cantEquipos);
		System.out.printf("Equipo con mejor ratio de victorias/derrotas: %s (%.2f)\n", equipoMejorRatio, mejorRatio);
		System.out.printf("Equipo con peor ratio de victorias/derrotas: %s (%.2f)\n", equipoPeorRatio, peorRatio);
		}

		private static void listarEquipos(final String[][] EQUIPOS, int cantEquipos, final String[] VIDEOJUEGOS) {
			if(cantEquipos == 0) { //VERIFICAR SI HAY EQUIPOS REGISTRADOS
				System.out.println("No hay equipos registrados.");
				imprimirGuiones(); 
				
			}
			//MOSTRAR LOS EQUIPOS REGISTRADOS
				for(int i = 0; i < cantEquipos; i++) {
					System.out.println("Los equipos registrados son: ");
					if(EQUIPOS[i] != null) {
						mostrarDatoEquipo(EQUIPOS, i, VIDEOJUEGOS);
						imprimirGuiones();
					}
					}
		}

	
		private static void imprimirGuiones(){
		System.out.println("------------------------------------------------------------------------");
		}

}

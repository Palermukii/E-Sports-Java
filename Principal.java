import java.util.InputMismatchException;
import java.util.Scanner;


public class Principal {

	public static void main(String[] args) {
		final int N_EQUIPOS_MAX = 15, CANT_ATRIBUTOS = 8;
		final int ID_MIN = 0;
		final int ID_MAX = 1000;
		String[][] equipos = new String[N_EQUIPOS_MAX][CANT_ATRIBUTOS];
		Scanner s = new Scanner(System.in);
		int cantEquipos = 0;
		mostrarMenuYElegirOpcion(s);
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
					cantEquipos = ingresarEquipo(s,EQUIPOSM, cantEquipos, )
				}
		}
	}

	public static int ingresarEquipo(Scanner s, final String[][] EQUIPOS, int cantEquipos, final int ID_MIN, final int ID_MAX) {

	}

	public static int validarId(equipos[][]){
		
	}

	


	
}






//1 Registrar equipo
//2 Consultar equipo
//3 Modificar equipo
//4 Eliminar equipo
//5 Listar todos los equipos
//6 Registrar resultado de partido
//7 Buscar equipos por videojuego
//8 Buscar equipos por país
//9 Ver tabla de posiciones
//10 Calcular estadísticas del torneo
//11 Salir

package com.sergio;
import java.util.Scanner;
import java.util.Random;

public class Juego {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Jugador jugador1 = new Jugador("Jugador 1", "");
        Jugador jugador2 = new Jugador("Jugador 2", "");
        Computadora computadora = new Computadora("O");

        System.out.println("¡Bienvenido al juego del 3 en raya!");
        tablero.mostrar();
        
        // Pedir al usuario que seleccione un modo de juego
        int modoJuego;
        do {
            System.out.println("Seleccione un modo de juego:");
            System.out.println("1. Jugador vs Jugador");
            System.out.println("2. Jugador vs Computadora");
            modoJuego = scanner.nextInt();
        } while (modoJuego != 1 && modoJuego != 2);
        
        // Pedir al jugador 1 que seleccione un símbolo
        System.out.println("Jugador 1, seleccione su símbolo:");
        String simboloJugador1 = scanner.next();
        jugador1.setSimbolo(simboloJugador1);

        // Pedir al jugador 2 que seleccione un símbolo diferente al de jugador 1
        System.out.println("Jugador 2, seleccione su símbolo:");
        String simboloJugador2 = scanner.next();
        while (simboloJugador2.equals(simboloJugador1)) {
            System.out.println("Este símbolo ya fue seleccionado. Seleccione otro:");
            simboloJugador2 = scanner.next();
        }
        jugador2.setSimbolo(simboloJugador2);

        Jugador jugadorActivo, jugadorInactivo;

     // Determinar quién será el primer jugador en jugar
        if (modoJuego == 1) {
            Random random = new Random();
            int jugadorInicial = random.nextInt(2) + 1;
            jugadorActivo = jugadorInicial == 1 ? jugador1 : jugador2;
            jugadorInactivo = jugadorInicial == 1 ? jugador2 : jugador1;
        } else {
            jugadorActivo = jugador1;
            Jugador pc = new Jugador("Computadora", computadora.getSimbolo());
            jugadorInactivo = pc;
        }

        // Iniciar el juego
        while (true) {
            // Pedir al jugador activo que seleccione una celda
            System.out.println(jugadorActivo.getNombre() + ", es tu turno.");
            boolean movimientoValido = false;
            while (!movimientoValido) {
                System.out.println("Selecciona la fila:");
                int fila = scanner.nextInt();
                System.out.println("Selecciona la columna:");
                int columna = scanner.nextInt();
                if (tablero.estaVacia(fila, columna)) {
                    tablero.marcar(fila, columna, jugadorActivo.getSimbolo());
                    movimientoValido = true;
                } else {
                    System.out.println("La celda seleccionada está ocupada. Por favor, seleccione otra.");
                }
            }
            tablero.mostrar();

            // Comprobar si el jugador activo ha ganado o si hay empate
            if (hayGanador(tablero, jugadorActivo)) {
                System.out.println(jugadorActivo.getNombre() + " ha ganado. ¡Felicidades!");
                break;
            } else if (hayEmpate(tablero)) {
                System.out.println("El juego ha terminado en empate.");
                break;
            }

            // Cambiar el jugador activo al jugador inactivo
            Jugador temp = jugadorActivo;
            jugadorActivo = jugadorInactivo;
            jugadorInactivo = temp;
        }
        
    }



	private static boolean hayGanador(Tablero tablero, Jugador jugador) {
        String simbolo = jugador.getSimbolo();
        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (tablero.obtenerValor(i, 0).equals(simbolo) && tablero.obtenerValor(i, 1).equals(simbolo) && tablero.obtenerValor(i, 2).equals(simbolo)) {
                return true;
            }
        }
        // Comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero.obtenerValor(0, i).equals(simbolo) && tablero.obtenerValor(1, i).equals(simbolo) && tablero.obtenerValor(2, i).equals(simbolo)) {
                return true;
            }
        }
        // Comprobar diagonal principal
        if (tablero.obtenerValor(0, 0).equals(simbolo) && tablero.obtenerValor(1, 1).equals(simbolo) && tablero.obtenerValor(2, 2).equals(simbolo)) {
            return true;
        }
        // Comprobar diagonal secundaria
        if (tablero.obtenerValor(0, 2).equals(simbolo) && tablero.obtenerValor(1, 1).equals(simbolo) && tablero.obtenerValor(2, 0).equals(simbolo)) {
            return true;
        }
        return false;
    }

    private static boolean hayEmpate(Tablero tablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero.estaVacia(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean seguir(Scanner scanner) {
        System.out.println("¿Quieres jugar de nuevo? (s/n)");
        String respuesta = scanner.next().toLowerCase();
        while (!respuesta.equals("s") && !respuesta.equals("n")) {
            System.out.println("Por favor, introduce s o n.");
            respuesta = scanner.next().toLowerCase();
        }
        return respuesta.equals("s");
    }

    private static void terminar() {
        System.out.println("Gracias por jugar. ¡Hasta la próxima!");
        System.exit(0);
    }
}




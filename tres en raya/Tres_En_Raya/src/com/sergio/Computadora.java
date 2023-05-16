package com.sergio;
import java.util.Random;

public class Computadora {
    private String simbolo;
    private Random generador;

    public Computadora(String simbolo) {
        this.simbolo = simbolo;
        generador = new Random();
    }
    
    public Computadora(Jugador jugador) {
        this.simbolo = jugador.getSimbolo();
        generador = new Random();
    }

    public void jugar(Tablero tablero) {
        System.out.println("Turno de la computadora:");
        int fila,columna;
        do {
            fila = generador.nextInt(3);
            columna = generador.nextInt(3);
        } while (!tablero.estaVacia(fila,columna));
        tablero.marcar(fila,columna, simbolo);
        tablero.mostrar();
    }
    public String getSimbolo() {
        return simbolo;
    }

}




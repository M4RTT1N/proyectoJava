package com.example;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;

public class App {
    public static void main(String[] args) {
        jugarAdivinanza();
    }

    public static void jugarAdivinanza() {
        System.out.println("Bienvenido al Juego de Adivinanza de Números Mejorado!");
        
        Random random = new Random();
        int numeroSecreto = random.nextInt(100) + 1; // Número entre 1 y 100
        int intentos = 0;
        boolean adivinado = false;
        List<Integer> intentosPrevios = new ArrayList<>();
        
        Scanner scanner = new Scanner(System.in);
        
        while (!adivinado && intentos < 100) {  // Añadimos un límite de intentos
            if (intentos > 0 && intentos % 5 == 0) {
                System.out.println("Llevas " + intentos + " intentos. ¿Quieres una pista? (s/n)");
                String respuesta = scanner.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    darPista(numeroSecreto, intentosPrevios);
                }
            }

            System.out.print("Adivina el número (entre 1 y 100): ");
            try {
                if (!scanner.hasNextInt()) {
                    break;  // Salimos del bucle si no hay más entradas
                }
                int intento = scanner.nextInt();
                intentos++;
                
                if (intentosPrevios.contains(intento)) {
                    System.out.println("Ya has intentado este número antes. ¡Intenta otro!");
                    continue;
                }
                
                intentosPrevios.add(intento);
                
                if (intento == numeroSecreto) {
                    adivinado = true;
                    System.out.println("¡Felicidades! Has adivinado el número en " + intentos + " intentos.");
                    mostrarEstadisticas(intentosPrevios);
                } else if (intento < numeroSecreto) {
                    System.out.println("El número es mayor. Intenta de nuevo.");
                } else {
                    System.out.println("El número es menor. Intenta de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // limpia el buffer del scanner
            }
        }
        
        if (!adivinado) {
            System.out.println("El juego ha terminado. El número secreto era: " + numeroSecreto);
        }
        
        scanner.close();
    }
    
    private static void darPista(int numeroSecreto, List<Integer> intentosPrevios) {
        int cercano = intentosPrevios.stream()
                .min((a, b) -> Math.abs(a - numeroSecreto) - Math.abs(b - numeroSecreto))
                .orElse(0);
        System.out.println("Pista: El número más cercano que has intentado es " + cercano);
    }
    
    private static void mostrarEstadisticas(List<Integer> intentos) {
        System.out.println("Estadísticas de tus intentos:");
        System.out.println("Número de intentos: " + intentos.size());
        System.out.println("Primer intento: " + intentos.get(0));
        System.out.println("Último intento: " + intentos.get(intentos.size() - 1));
        double promedio = intentos.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("Promedio de intentos: " + String.format("%.2f", promedio));
    }
}
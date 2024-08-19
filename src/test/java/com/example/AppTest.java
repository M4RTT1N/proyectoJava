package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testJuegoAdivinanza() {
        String input = "50\n75\n62\n68\n66\nn\n67\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        App.jugarAdivinanza();
        String output = outputStream.toString();
        
        assertTrue(output.contains("Bienvenido al Juego de Adivinanza de Números Mejorado!"), "Mensaje de bienvenida no encontrado");
        assertTrue(output.contains("El número es mayor") || output.contains("El número es menor"), "No se proporcionaron pistas de mayor/menor");
        assertTrue(output.contains("¿Quieres una pista?"), "Oferta de pista no encontrada");
    }

    @Test
    public void testIntentoDuplicado() {
        String input = "50\n50\n75\n1\n2\n3\n4\n5\nn\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.jugarAdivinanza();
        String output = outputStream.toString();
        
        assertTrue(output.contains("Ya has intentado este número antes"), "Advertencia de intento duplicado no encontrada");
    }

    @Test
    public void testPista() {
        String input = "1\n2\n3\n4\n5\ns\n50\n60\n70\n80\n90\n100\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        App.jugarAdivinanza();
        String output = outputStream.toString();
        
        assertTrue(output.contains("¿Quieres una pista?"), "Oferta de pista no encontrada");
        assertTrue(output.contains("Pista: El número más cercano que has intentado es"), "Pista no proporcionada");
    }
}
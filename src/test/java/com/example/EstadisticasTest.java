package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class EstadisticasTest {

    @Test
    public void testCalcularPromedio() {
        List<Integer> intentos = Arrays.asList(10, 20, 30, 40, 50);
        double promedio = intentos.stream().mapToInt(Integer::intValue).average().orElse(0);
        assertEquals(30.0, promedio, 0.01, "El promedio calculado no es correcto");
    }

    @Test
    public void testPrimerUltimoIntento() {
        List<Integer> intentos = Arrays.asList(5, 15, 25, 35, 45);
        assertEquals(5, intentos.get(0), "El primer intento no es correcto");
        assertEquals(45, intentos.get(intentos.size() - 1), "El último intento no es correcto");
    }
}
package com.example;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ AppTest.class, EstadisticasTest.class })
public class AllTests {
    // Esta clase vacía solo sirve como punto de entrada para la suite de pruebas
}
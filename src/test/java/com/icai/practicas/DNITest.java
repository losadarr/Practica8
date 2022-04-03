package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icai.practicas.model.DNI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DNITest {

    DNI dni;
    DNI dni2;
    DNI dni3;
 
    @BeforeEach                          //para cada vez antes de cada test
    void setUp() {
        dni = new DNI("05965845J");
        dni2 = new DNI("01234a67Z");
        dni3 = new DNI("00000000T");
    }

    @Test
    @DisplayName("Simple validación")
    void testValidacion(){

        assertEquals(true, dni.validar(),
        "Validación del dni que deberia funcionar"
        );
    }
    @Test
    @DisplayName("Asegurandonos de que un dni sin expresion regular sea falso")
    void testInvalidacion(){

        assertEquals(false, dni2.validar(),
        "Validación del dni que deberia dar falso"
        );

    }
    @Test
    @DisplayName("Asegurandonos de que un dni dentro de los invalidos por el ministerio de interior sea falso")
    void testInvalidacion2(){

        assertEquals(false, dni3.validar(),
        "Validación2 del dni que deberia dar falso"
        );

    }

    
    
}

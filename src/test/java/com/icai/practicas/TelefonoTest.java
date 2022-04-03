package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icai.practicas.model.Telefono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TelefonoTest {

    Telefono telefono;
    Telefono telefono2;
    Telefono telefono3;
    Telefono telefono4;
    Telefono telefono5;


    @BeforeEach                          //para cada vez antes de cada test
    void setUp() {
        telefono = new Telefono("630002114");//valido
        telefono2 = new Telefono("+34 735987634");//valido

        telefono3 = new Telefono("6300021897687"); //invalido
        telefono4 = new Telefono("+3434 735987634");//invalido
        telefono5 = new Telefono("hola");//invalido

    }

    @Test
    @DisplayName("Simple validación")
    void testValidacion(){

        assertEquals(true, telefono.validar(),
        "Validación del teléfono que deberia funcionar"
        );
        
        assertEquals(true, telefono2.validar(),
        "Validación del teléfono que deberia funcionar"
        );

        //todos en el mismo test para probar otra manera de hacerlo diferente a la del DNI

        assertEquals(false, telefono3.validar(),
         "Validación del teléfono que deberia fallar"
        );
        assertEquals(false, telefono4.validar(),
        "Validación del teléfono que deberia fallar"
        );
        assertEquals(false, telefono5.validar(),
        "Validación del teléfono que deberia fallar"
        );



    }
    
}

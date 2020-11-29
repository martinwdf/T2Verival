package com.bcopstein;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class FactoryValidacaoTest {

    @Test
    public void testeValidacaoFora(){
        LocalTime agora = LocalTime.now();
        FactoryValidacao f = new FactoryValidacao(agora);
        assertEquals(new ValidacaoForaHorarioComercial().getClass().getName(), f.getRegraValidacao().getClass().getName());
    }

    @Test
    public void testeValidacaoDentro(){
        LocalTime agora = LocalTime.of(7, 20, 2);
        FactoryValidacao f = new FactoryValidacao(agora);
        assertEquals(new ValidacaoHorarioComercial().getClass().getName(), f.getRegraValidacao().getClass().getName());
    }
}
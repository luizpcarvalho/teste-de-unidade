package br.com.caelum.leilao.servico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnoBissextoTest {

    @Test
    public void deveRetornarTrue() {
        AnoBissexto anoBissexto = new AnoBissexto();
        boolean bissexto = anoBissexto.ehBissexto(2020);

        assertEquals(true, bissexto);
    }

    @Test
    public void deveRetornarFalse(){
        AnoBissexto anoBissexto = new AnoBissexto();
        boolean bissexto = anoBissexto.ehBissexto(2019);

        assertEquals(false, bissexto);
    }

}

package br.com.caelum.leilao.servico;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatematicaMalucaTest {

    @Test
    public void testaComNumeroMaiorQueTrinta() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(140, matematicaMaluca.contaMaluca(35));
    }

    @Test
    public void testaComNumeroMaiorQueDezEMenorQueTrinta() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(60, matematicaMaluca.contaMaluca(20));
    }

    @Test
    public void testaComNumeroMenorQueDez() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(10, matematicaMaluca.contaMaluca(5));
    }

    @Test
    public void testaComNumeroIgualATrintaEIgualADez() {
        MatematicaMaluca matematicaMaluca = new MatematicaMaluca();

        assertEquals(90, matematicaMaluca.contaMaluca(30));
        assertEquals(20, matematicaMaluca.contaMaluca(10));
    }

}
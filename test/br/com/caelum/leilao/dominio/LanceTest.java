package br.com.caelum.leilao.dominio;

import br.com.caelum.leilao.servico.Avaliador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanceTest {

    private Avaliador leiloeiro;
    private Usuario joao;

    @BeforeEach
    public void setUp(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
    }

    @Test
    public void deveRetornarIllegalArgumentExceptionComValorDoLanceIgualAZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Lance(joao, 0)
        );

        assertTrue(exception.getMessage().contains("O valor do lance deve ser maior que zero!"));
    }

    @Test
    public void deveRetornarIllegalArgumentExceptionComValorDoLanceMenorQueZero() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Lance(joao, -5)
        );

        assertTrue(exception.getMessage().contains("O valor do lance deve ser maior que zero!"));
    }

}
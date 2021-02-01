package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvaliadorTest {

    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;

    @BeforeEach
    public void setUp(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
    }

    @Test
    public void deveEntenderLancesEmOrdemCrescente() {
        // parte 1: cenário

        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 250.0)
                .lance(jose, 300.0)
                .lance(maria, 400.0)
                .constroi();

        // parte 2: ação
        leiloeiro.avalia(leilao);

        // parte 3: validação
        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 1000.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveCalcularAMedia() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 300.0)
                .lance(jose, 400.0)
                .lance(maria, 500.0)
                .constroi();

        leiloeiro.avalia(leilao);

        double mediaEsperada = 400;

        assertEquals(mediaEsperada, leiloeiro.getValorMedio(), 0.00001);
    }

    @Test
    public void testeMediaDeZeroLance() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(0, leiloeiro.getValorMedio(), 0.00001);
    }

    @Test
    public void deveEncontrarOsTresMaioresLances(){
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(400.0, maiores.get(0).getValor(), 0.00001);
        assertEquals(300.0, maiores.get(1).getValor(), 0.00001);
        assertEquals(200.0, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveEntenderLancesEmOrdemAleatoria() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 200.0)
                .lance(jose, 450.0)
                .lance(maria, 120.0)
                .lance(joao, 700.0)
                .lance(jose, 630.0)
                .lance(maria, 230.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(120, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveEntenderLancesEmOrdemDecrescente() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo")
                .lance(joao, 400.0)
                .lance(jose, 300.0)
                .lance(joao, 200.0)
                .lance(jose, 100.0)
                .constroi();

        leiloeiro.avalia(leilao);

        assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
        assertEquals(100, leiloeiro.getMenorLance(), 0.00001);

    }

    @Test
    public void deveDevolverOsTresMaioresDeCincoLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 5")
                .lance(joao, 500)
                .lance(jose, 700)
                .lance(joao, 350)
                .lance(jose, 100)
                .lance(joao, 430)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();
        assertEquals(3, maiores.size());
        assertEquals(700, maiores.get(0).getValor(), 0.00001);
        assertEquals(500, maiores.get(1).getValor(), 0.00001);
        assertEquals(430, maiores.get(2).getValor(), 0.00001);
    }

    @Test
    public void deveDevolverDoisLances() {
        Leilao leilao = new CriadorDeLeilao().para("Geladeira")
                .lance(maria, 500)
                .lance(joao, 450)
                .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(2, maiores.size());
        assertEquals(500, maiores.get(0).getValor());
        assertEquals(450, maiores.get(1).getValor());
    }

    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 4 Novo").constroi();
        Exception exception = assertThrows(RuntimeException.class, () -> leiloeiro.avalia(leilao));
        assertTrue(exception.getMessage().contains("Não é possível avaliar um leilão sem lances!"));
    }
}

package br.com.caelum.leilao.servico;

public class AnoBissexto {

    public boolean ehBissexto(int ano) {
        if(ano % 400 == 0){
            return true;
        } else if(ano % 100 == 0){
            return false;
        } else if(ano % 4 == 0){
            return true;
        }
        return false;
    }

}

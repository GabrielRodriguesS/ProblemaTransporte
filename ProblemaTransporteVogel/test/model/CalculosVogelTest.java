/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author Gabriel
 */
public class CalculosVogelTest {

    Map<String, Reino> mapaReinos;
    CalculosVogel calculo;
    Reino reino1;
    Reino reino2;
    Reino reino3;
    Reino reino4;
    Reino reino5;

    @Before
    public void setUp() {
        mapaReinos = new HashMap();
        calculo = new CalculosVogel(mapaReinos);
        instanciaReinos3();
        mapaReinos.put("Reino 1", this.reino1);
        mapaReinos.put("Reino 2", this.reino2);
        mapaReinos.put("Reino 3", this.reino3);
        mapaReinos.put("Reino 4", this.reino4);
        mapaReinos.put("Reino 5", this.reino5);
        calculo.transformaMapaEmArraySemDummy();
    }

    @Test
    public void testVogel() {
        calculo.calculaVogel(false);
    }

    @Ignore
    @Test
    public void testBuscaReino() {
        Assert.assertEquals((Object) reino2, calculo.buscarNoMapaOReino(2));
    }

    @Ignore
    @Test
    public void testCalculoMaiorColuna() {
        Reino retorno = calculo.calculaMaiorPenalidadeColuna();
        Assert.assertEquals((Object) reino2, (Object) retorno);
    }

    @Ignore
    @Test
    public void testLinhaMaiorPenalidade() {
        Integer retorno = calculo.calculaPenalidadePorLinha(calculo.getCustosRotalinhaDois());
        Assert.assertEquals((Object) 2, (Object) retorno);
    }

    @Ignore
    @Test
    public void testMenorCustoLinha() {
        Integer retorno = calculo.getMenorCustoLinha(calculo.getCustosRotalinhaUm());
        Assert.assertEquals((Object) 3, (Object) retorno);
    }

// public Reino(Integer rotaFabricaUm, Integer rotaFabricaDois, Integer demanda, Integer penalidade, Integer quantidadeTransportada) 
//LEMBRAR QUE A ORDEM NO HASHMAP É DEFINIDA POR UM HASH E NAO NA ORDEM DE INSERÇÃO!
    private void instanciaReinos1() {
        reino1 = new Reino(2, 5, 500, 0, 0, "reino1"); //3 
        reino2 = new Reino(8, 9, 500, 0, 0, "reino2"); //1 
        reino3 = new Reino(6, 10, 500, 0, 0, "reino3"); //4 
        reino4 = new Reino(1, 6, 500, 0, 0, "reino4"); //5 
        reino5 = new Reino(8, 3, 500, 0, 0, "reino5"); //5 

        reino1.setPenalidadeDummy(); //pena 2
        reino2.setPenalidadeDummy(); //pena 8
        reino3.setPenalidadeDummy(); //pena 6
        reino4.setPenalidadeDummy(); //pena 1
        reino5.setPenalidadeDummy(); //pena 3
    }

    private void instanciaReinos2() {
        reino1 = new Reino(1, 10, 500, 0, 0, "reino1"); //9 
        reino2 = new Reino(2, 5, 500, 0, 0, "reino2"); //3
        reino3 = new Reino(6, 10, 500, 0, 0, "reino3"); //4
        reino4 = new Reino(1, 9, 500, 0, 0, "reino4"); // 8
        reino5 = new Reino(8, 3, 500, 0, 0, "reino5"); //5

        reino1.setPenalidade(); //pena 2
        reino2.setPenalidade(); //pena 8
        reino3.setPenalidade(); //pena 6
        reino4.setPenalidade(); //pena 1
        reino5.setPenalidade(); //pena 3
    }

    private void instanciaReinos3() {
        reino1 = new Reino(6, 16, 700, 0, 0, "reino1"); //10
        reino2 = new Reino(2, 5, 500, 0, 0, "reino2"); //3
        reino3 = new Reino(1, 10, 700, 0, 0, "reino3"); //9
        reino4 = new Reino(1, 11, 700, 0, 0, "reino4"); // 9
        reino5 = new Reino(8, 3, 700, 0, 0, "reino5"); //5

        reino1.setPenalidade(); //pena 2
        reino2.setPenalidade(); //pena 8
        reino3.setPenalidade(); //pena 6
        reino4.setPenalidade(); //pena 1
        reino5.setPenalidade(); //pena 3
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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
        calculo = new CalculosVogel();
        instanciaReinos1();
        mapaReinos.put("Reino 1", this.reino1);
        mapaReinos.put("Reino 2", this.reino2);
        mapaReinos.put("Reino 3", this.reino3);
        mapaReinos.put("Reino 4", this.reino4);
        mapaReinos.put("Reino 5", this.reino5);
        calculo.transformaMapaEmArrayComDummyOferta(mapaReinos);
    }

    @Test
    public void testCalculoMaiorColuna() {
        Reino retorno = calculo.calculaMaiorPenalidadeColuna(mapaReinos);
        Assert.assertEquals((Object) reino3, (Object) retorno);
    }

    @Ignore
    @Test
    public void testLinhaMaiorPenalidade() {
        Integer retorno = calculo.calculaPenalidadePorLinha(calculo.getCustosRotalinhaDois());
        Assert.assertEquals((Object) 2, (Object) retorno);
    }

// public Reino(Integer rotaFabricaUm, Integer rotaFabricaDois, Integer demanda, Integer penalidade, Integer quantidadeTransportada) 
//LEMBRAR QUE A ORDEM NO HASHMAP É DEFINIDA POR UM HASH E NAO NA ORDEM DE INSERÇÃO!
    private void instanciaReinos1() {
        reino1 = new Reino(2, 5, 500, 0, 0); //3 d 5
        reino2 = new Reino(8, 9, 500, 0, 0); //1 d 9
        reino3 = new Reino(6, 10, 500, 0, 0); //4 d 10
        reino4 = new Reino(1, 6, 500, 0, 0); //5 d 6
        reino5 = new Reino(8, 3, 500, 0, 0); //5 d 3
    }

    private void instanciaReinos2() {
        reino1 = new Reino(1, 10, 500, 0, 0); //9 
        reino2 = new Reino(2, 5, 500, 0, 0); //3
        reino3 = new Reino(6, 10, 500, 0, 0); //4
        reino4 = new Reino(1, 9, 500, 0, 0); // 8
        reino5 = new Reino(8, 3, 500, 0, 0); //5
    }

    private void instanciaReinos3() {
        reino1 = new Reino(6, 16, 500, 0, 0); //10
        reino2 = new Reino(2, 5, 500, 0, 0); //3
        reino3 = new Reino(1, 10, 500, 0, 0); //9
        reino4 = new Reino(1, 10, 500, 0, 0); // 9
        reino5 = new Reino(8, 3, 500, 0, 0); //5
    }

}

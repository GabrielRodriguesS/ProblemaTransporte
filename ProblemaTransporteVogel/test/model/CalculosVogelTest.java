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

/**
 *
 * @author Gabriel
 */
public class CalculosVogelTest {
// public Reino(Integer rotaFabricaUm, Integer rotaFabricaDois, Integer demanda, Integer penalidade, Integer quantidadeTransportada) {

    Map<String, Reino> mapaReinos = new HashMap();
    Reino reino1 = new Reino(2, 5, 500, 0, 0);
    Reino reino2 = new Reino(8, 9, 500, 0, 0);
    Reino reino3 = new Reino(6, 10, 500, 0, 0);
    Reino reino4 = new Reino(1, 6, 500, 0, 0);
    Reino reino5 = new Reino(8, 3, 500, 0, 0);

    @Before
    public void setUp() {
        instanciaReinos2();
        mapaReinos.put("Reino 1", this.reino1);
        mapaReinos.put("Reino 2", this.reino2);
        mapaReinos.put("Reino 3", this.reino3);
        mapaReinos.put("Reino 4", this.reino4);
        mapaReinos.put("Reino 5", this.reino5);
    }

    @Test
    public void testCalculoMaiorColuna() {
        CalculosVogel calculo = new CalculosVogel();
        Integer retorno = calculo.calculaMaiorPenalidadeColuna(mapaReinos);
        Assert.assertEquals((Object) 0, (Object) retorno);
    }

    //LEMBRAR QUE A ORDEM NO HASHMAP É DEFINIDA POR UM HASH E NAO NA ORDEM DE INSERÇÃO!
    private void instanciaReinos() {
        reino1 = new Reino(2, 5, 500, 0, 0); //3 
        reino2 = new Reino(8, 9, 500, 0, 0); //1
        reino3 = new Reino(6, 10, 500, 0, 0); //4
        reino4 = new Reino(1, 6, 500, 0, 0); //5
        reino5 = new Reino(8, 3, 500, 0, 0); //5
    }

    private void instanciaReinos2() {
        reino1 = new Reino(1, 10, 500, 0, 0); //9
        reino2 = new Reino(2, 5, 500, 0, 0); //3
        reino3 = new Reino(6, 10, 500, 0, 0); //4
        reino4 = new Reino(1, 9, 500, 0, 0); // 8
        reino5 = new Reino(8, 3, 500, 0, 0); //5
    }

}

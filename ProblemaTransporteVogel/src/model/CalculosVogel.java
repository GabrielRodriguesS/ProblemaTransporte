/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class CalculosVogel {

    private final ArrayList<Integer> custoRotalinhaUm;
    private final ArrayList<Integer> custoRotalinhaDois;
    private final ArrayList<Integer> custoRotalinhaDummy;

    public CalculosVogel() {
        this.custoRotalinhaDois = new ArrayList<>();
        this.custoRotalinhaUm = new ArrayList<>();
        this.custoRotalinhaDummy = new ArrayList<>();
    }

    public void transformaMapaEmArraySemDummy(Map<String, Reino> mapaReinos) {
        System.out.println("###Mapa###");
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            System.out.println("rota1:" + value.getRotaFabricaUm());
            System.out.println("rota2:" + value.getRotaFabricaDois());
            custoRotalinhaUm.add(value.getRotaFabricaUm());
            custoRotalinhaDois.add(value.getRotaFabricaDois());
        }
        System.out.println("###Array linha um ###");
        for (Integer custoRotalinhaUm1 : custoRotalinhaUm) {
            System.out.println("custo:" + custoRotalinhaUm1);
        }
        System.out.println("###Array linha dois ###");
        for (Integer custoRotalinhaUm1 : custoRotalinhaDois) {
            System.out.println("custo:" + custoRotalinhaUm1);
        }
        
        
    }

    public void transformaMapaEmArrayComDummyOferta(Map<String, Reino> mapaReinos) {

    }

    public void transformaMapaEmArrayComDummyDemanda(Map<String, Reino> mapaReinos) {

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class CalculosVogel {

    private final ArrayList<Integer> custosRotalinhaUm;
    private final ArrayList<Integer> custosRotalinhaDois;
    private final ArrayList<Integer> custosRotalinhaDummy;

    public CalculosVogel() {
        this.custosRotalinhaDois = new ArrayList<>();
        this.custosRotalinhaUm = new ArrayList<>();
        this.custosRotalinhaDummy = new ArrayList<>();
    }

    public void transformaMapaEmArraySemDummy(Map<String, Reino> mapaReinos) {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        calculaVogel(mapaReinos);
    }

    public void transformaMapaEmArrayComDummyOferta(Map<String, Reino> mapaReinos) {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
            custosRotalinhaDummy.add(value.getDummy());
        }
        calculaVogel(mapaReinos);
    }

    public void transformaMapaEmArrayComDummyDemanda(Map<String, Reino> mapaReinos) {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        custosRotalinhaDois.add(0);
        custosRotalinhaUm.add(0);

        calculaVogel(mapaReinos);
    }

    private void calculaVogel(Map<String, Reino> mapaReinos) {
        getMaiorPenalidade();
    }

    //retona o array com maior penalidade entre as linhas
    private ArrayList<Integer> getMaiorPenalidade() {
        Integer penalidadeLinhaUm = getPenalidadePorLinha(custosRotalinhaUm);
        Integer penalidadeLinhaDois = getPenalidadePorLinha(custosRotalinhaDois);

        System.out.println("Penalidade linha um: " + penalidadeLinhaUm);
        System.out.println("Penalidade linha dois: " + penalidadeLinhaDois);
        if (penalidadeLinhaUm > penalidadeLinhaDois) {
            return custosRotalinhaUm;
        } else {
            return custosRotalinhaDois;
        }
    }

    //faz o mesmo que o de cima soq com coluna
    private ArrayList<Integer> calculaPenalidadeColuna(Map<String, Reino> mapaReinos) {
        return null;

    }

    private Integer getPenalidadePorLinha(ArrayList<Integer> custosLinha) {
        ArrayList<Integer> copiaDoArray = new ArrayList();
        Integer primeiroMenorCusto, segundoMenorCusto;

        copiaDoArray.addAll(custosLinha);
        primeiroMenorCusto = Collections.min(copiaDoArray);
        copiaDoArray.remove(primeiroMenorCusto);
        segundoMenorCusto = Collections.min(copiaDoArray);
        return segundoMenorCusto - primeiroMenorCusto;
    }
}

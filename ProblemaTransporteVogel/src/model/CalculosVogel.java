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

    public void calculaVogel(Map<String, Reino> mapaReinos) {
        ArrayList<Integer> linhaComMaiorPenalidade = getMaiorPenalidadeEntreLinhaEColuna();
        Integer colunaComMaiorPenalidade = calculaMaiorPenalidadeColuna(mapaReinos);

        Integer penalidadeLinha = calculaPenalidadePorLinha(linhaComMaiorPenalidade);

        System.out.println("Penalidade linha:" + penalidadeLinha);
        System.out.println("Penalidade coluna:" + colunaComMaiorPenalidade);
    }

    //retona o array com maior penalidade entre as linhas
    public ArrayList<Integer> getMaiorPenalidadeEntreLinhaEColuna() {
        Integer penalidadeLinhaUm = calculaPenalidadePorLinha(custosRotalinhaUm);
        Integer penalidadeLinhaDois = calculaPenalidadePorLinha(custosRotalinhaDois);
        if (penalidadeLinhaUm > penalidadeLinhaDois) {
            return custosRotalinhaUm;
        } else {
            return custosRotalinhaDois;
        }
    }

    //faz o mesmo que o de cima soq com coluna
    public Integer calculaMaiorPenalidadeColuna(Map<String, Reino> mapaReinos) {
        ArrayList<Integer> penalidadesDasColunas = new ArrayList();
        Integer maiorPenalidade;
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            if (value.getRotaFabricaUm() > value.getRotaFabricaDois()) {
                penalidadesDasColunas.add(value.getRotaFabricaUm() - value.getRotaFabricaDois());
            } else {
                penalidadesDasColunas.add(value.getRotaFabricaDois() - value.getRotaFabricaUm());
            }
        }
        maiorPenalidade = Collections.max(penalidadesDasColunas);
        System.out.println("index: " + penalidadesDasColunas.indexOf(maiorPenalidade));
        System.out.println("Valor da penalidae: " + maiorPenalidade);
        return penalidadesDasColunas.indexOf(maiorPenalidade);
    }

    public ArrayList<Integer> calculaPenalidadeColunaDummyOferta(Map<String, Reino> mapaReinos) {
        return null;

    }

    private Integer calculaPenalidadePorLinha(ArrayList<Integer> custosLinha) {
        ArrayList<Integer> copiaDoArray = new ArrayList();
        Integer primeiroMenorCusto, segundoMenorCusto;

        copiaDoArray.addAll(custosLinha);
        primeiroMenorCusto = Collections.min(copiaDoArray);
        copiaDoArray.remove(primeiroMenorCusto);
        segundoMenorCusto = Collections.min(copiaDoArray);
        return segundoMenorCusto - primeiroMenorCusto;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import util.ReinoComparator;

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
        calculaVogel(mapaReinos, false);
    }

    public void transformaMapaEmArrayComDummyOferta(Map<String, Reino> mapaReinos) {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
            custosRotalinhaDummy.add(value.getDummy());
        }
        calculaVogel(mapaReinos, true);
    }

    public void transformaMapaEmArrayComDummyDemanda(Map<String, Reino> mapaReinos) {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        custosRotalinhaDois.add(0);
        custosRotalinhaUm.add(0);

        calculaVogel(mapaReinos, false);
    }

    public void calculaVogel(Map<String, Reino> mapaReinos, boolean dummyOferta) {
        ArrayList<Integer> linhaComMaiorPenalidade = getMaiorPenalidadeLinha();
        Reino colunaComMaiorPenalidade;
        if (dummyOferta) {
            colunaComMaiorPenalidade = calculaMaiorPenalidadeColunaDummyOferta(mapaReinos);
        } else {
            colunaComMaiorPenalidade = calculaMaiorPenalidadeColuna(mapaReinos);
        }
        Integer penalidadeLinha = calculaPenalidadePorLinha(linhaComMaiorPenalidade);

//        System.out.println("Penalidade linha:" + penalidadeLinha);
//        System.out.println("Penalidade coluna:" + colunaComMaiorPenalidade);
    }

    //retona o array com maior penalidade entre as linhas
    public ArrayList<Integer> getMaiorPenalidadeLinha() {
        Integer penalidadeLinhaUm = calculaPenalidadePorLinha(custosRotalinhaUm);
        Integer penalidadeLinhaDois = calculaPenalidadePorLinha(custosRotalinhaDois);
        System.out.println("Penalidade linha1: " + penalidadeLinhaUm);
        System.out.println("Penalidade linha2: " + penalidadeLinhaDois);
        if (penalidadeLinhaUm > penalidadeLinhaDois) {
            return custosRotalinhaUm;
        } else {
            return custosRotalinhaDois;
        }
    }

    //faz o mesmo que o de cima soq com coluna
    public Reino calculaMaiorPenalidadeColuna(Map<String, Reino> mapaReinos) {
        //return Collections.max(mapaReinos.values(), new ReinoComparator());
        Reino r = Collections.max(mapaReinos.values(), new ReinoComparator());
        System.out.println("Caracteristicas Reino");
        System.out.println("F1: " + r.getRotaFabricaUm());
        System.out.println("F2: " + r.getRotaFabricaDois());
        System.out.println("Demanda: " + r.getDemanda());
        return r;
    }

    //retorna a chave para a coluna com maior penalidade
    public Reino calculaMaiorPenalidadeColunaDummyOferta(Map<String, Reino> mapaReinos) {
        ArrayList<Integer> penalidadesDasColunas = new ArrayList();
        Integer maiorPenalidade;
        System.out.println("Ordem dos elementos no mapa:");
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            if (value.getRotaFabricaUm() > value.getRotaFabricaDois()) {
                penalidadesDasColunas.add(value.getRotaFabricaUm() - value.getRotaFabricaDois());
            } else {
                penalidadesDasColunas.add(value.getRotaFabricaDois() - value.getRotaFabricaUm());
            }
            System.out.println("Penalidade:" + penalidadesDasColunas.get(penalidadesDasColunas.size() - 1));
        }
        maiorPenalidade = Collections.max(penalidadesDasColunas);
        System.out.println("index: " + penalidadesDasColunas.indexOf(maiorPenalidade));
        System.out.println("Valor da penalidae: " + maiorPenalidade);
        //return penalidadesDasColunas.indexOf(maiorPenalidade);
        return null;
    }

    public Integer calculaPenalidadePorLinha(ArrayList<Integer> custosLinha) {
        ArrayList<Integer> copiaDoArray = new ArrayList();
        Integer primeiroMenorCusto, segundoMenorCusto;

        copiaDoArray.addAll(custosLinha);
        primeiroMenorCusto = Collections.min(copiaDoArray);
        copiaDoArray.remove(primeiroMenorCusto);
        segundoMenorCusto = Collections.min(copiaDoArray);
        return segundoMenorCusto - primeiroMenorCusto;
    }

    public ArrayList<Integer> getCustosRotalinhaUm() {
        return custosRotalinhaUm;
    }

    public ArrayList<Integer> getCustosRotalinhaDois() {
        return custosRotalinhaDois;
    }

}

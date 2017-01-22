/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import static javafx.scene.input.KeyCode.T;
import util.ReinoComparator;
import util.ReinoComparatorDummy;

/**
 *
 * @author Gabriel
 */
public class CalculosVogel {

    private final ArrayList<Integer> custosRotalinhaUm;
    private final ArrayList<Integer> custosRotalinhaDois;

    public CalculosVogel() {
        this.custosRotalinhaDois = new ArrayList<>();
        this.custosRotalinhaUm = new ArrayList<>();
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
        ArrayList<Integer> linhaComMaiorPenalidade = getMaiorPenalidadeDasLinhas();
        Reino reinoDaColunaComMaiorPenalidade;
        if (dummyOferta) {
            reinoDaColunaComMaiorPenalidade = calculaMaiorPenalidadeColunaDummyOferta(mapaReinos);
        } else {
            reinoDaColunaComMaiorPenalidade = calculaMaiorPenalidadeColuna(mapaReinos);
        }
        Integer penalidadeLinha = calculaPenalidadePorLinha(linhaComMaiorPenalidade);
        reinoDaColunaComMaiorPenalidade.setPenalidade();

        System.out.println("Reino:");
        System.out.println("f1: " + reinoDaColunaComMaiorPenalidade.getRotaFabricaUm());
        System.out.println("f2: " + reinoDaColunaComMaiorPenalidade.getRotaFabricaDois());
        System.out.println("Penal: " + reinoDaColunaComMaiorPenalidade.getPenalidade());

        if (penalidadeLinha > reinoDaColunaComMaiorPenalidade.getPenalidade()) {

        } else {

        }
    }

    //retona o array com maior penalidade entre as linhas
    public ArrayList<Integer> getMaiorPenalidadeDasLinhas() {
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
        return Collections.max(mapaReinos.values(), new ReinoComparator());
    }

    //retorna a chave para a coluna com maior penalidade
    public Reino calculaMaiorPenalidadeColunaDummyOferta(Map<String, Reino> mapaReinos) {
        return Collections.max(mapaReinos.values(), new ReinoComparatorDummy());
//        List<Reino> l = new ArrayList<>();
//        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
//            Reino value = entrySet.getValue();
//            l.add(value);
//        }
//        Collections.sort(l, new ReinoComparatorDummy());
//        System.out.println("Lista ordnada");
//        for (Reino l1 : l) {
//            System.out.println("###");
//            System.out.println("f1: " + l1.getRotaFabricaUm());
//            System.out.println("f2: " + l1.getRotaFabricaDois());
//            System.out.println("###");
//        }
//        return null;
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

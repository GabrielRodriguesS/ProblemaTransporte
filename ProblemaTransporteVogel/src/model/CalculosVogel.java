/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Gabriel
 */
public class CalculosVogel {

    private final ArrayList<Integer> custosRotalinhaUm;
    private final ArrayList<Integer> custosRotalinhaDois;
    private final ArrayList<Transporte> transportes;
    private static Integer ofertaFabricaUm = 1800;
    private static Integer ofertaFabricaDois = 700;

    public CalculosVogel() {
        this.custosRotalinhaDois = new ArrayList<>();
        this.custosRotalinhaUm = new ArrayList<>();
        this.transportes = new ArrayList<>();
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
        Reino reinoDaColunaComMaiorPenalidade = calculaMaiorPenalidadeColuna(mapaReinos);
        Integer penalidadeLinha = calculaPenalidadePorLinha(linhaComMaiorPenalidade);
        if (penalidadeLinha > reinoDaColunaComMaiorPenalidade.getPenalidade()) {
            penalidadeLinha = this.getMenorCustoLinha(linhaComMaiorPenalidade);
            calculaTransporteSobreLinha(linhaComMaiorPenalidade, penalidadeLinha, mapaReinos);
        } else {
            calculaTransporteSobreColuna(reinoDaColunaComMaiorPenalidade, mapaReinos);
        }
    }

    //retona o array com maior penalidade entre as linhas
    public ArrayList<Integer> getMaiorPenalidadeDasLinhas() {
        Integer penalidadeLinhaUm;
        Integer penalidadeLinhaDois;
        if (custosRotalinhaUm == null) {
            penalidadeLinhaUm = Integer.MIN_VALUE;
        } else {
            penalidadeLinhaUm = calculaPenalidadePorLinha(custosRotalinhaUm);
        }
        if (custosRotalinhaDois == null) {
            penalidadeLinhaDois = Integer.MIN_VALUE;
        } else {
            penalidadeLinhaDois = calculaPenalidadePorLinha(custosRotalinhaDois);
        }

        if (penalidadeLinhaUm > penalidadeLinhaDois && penalidadeLinhaUm != Integer.MIN_VALUE) {
            return custosRotalinhaUm;
        } else if (penalidadeLinhaDois != Integer.MIN_VALUE) {
            return custosRotalinhaDois;
        }
        return null;
    }

    //faz o mesmo que o de cima soq com coluna
    public Reino calculaMaiorPenalidadeColuna(Map<String, Reino> mapaReinos) {
        List<Reino> reinosOrdenadosPenalidade = new ArrayList(mapaReinos.values());
        Collections.sort(reinosOrdenadosPenalidade);
        return reinosOrdenadosPenalidade.get(reinosOrdenadosPenalidade.size() - 1);
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

    public void calculaTransporteSobreLinha(ArrayList<Integer> custoLinha, Integer menorPenalidade, Map<String, Reino> mapaReinos) {
        if (this.custosRotalinhaUm.equals(custoLinha)) {
            calculosTransporteLinhaUm(menorPenalidade, mapaReinos);
        } else {
            calculosTransporteLinhaDois(menorPenalidade, mapaReinos);
        }
    }

    public Integer getMenorCustoLinha(ArrayList<Integer> custoLinha) {
        return Collections.min(custoLinha);

    }

    public void calculosTransporteLinhaDois(Integer menorPenalidade, Map<String, Reino> mapaReinos) {
        Integer index = this.custosRotalinhaDois.indexOf(menorPenalidade);
        Reino reino = buscarNoMapaOReino(index, mapaReinos);
        Integer quantidadeTransportada = calculaQuantidadeTransportada(reino.getDemanda(), CalculosVogel.ofertaFabricaDois);

        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);
        informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
        this.transportes.add(informacoesTransporte);

        eliminaLinhaOferta(quantidadeTransportada, custosRotalinhaDois, reino);
    }

    public void calculosTransporteLinhaUm(Integer menorPenalidade, Map<String, Reino> mapaReinos) {
        Integer index = this.custosRotalinhaUm.indexOf(menorPenalidade);
        Reino reino = buscarNoMapaOReino(index, mapaReinos);
        Integer quantidadeTransportada = calculaQuantidadeTransportada(reino.getDemanda(), CalculosVogel.ofertaFabricaUm);

        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);
        informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
        this.transportes.add(informacoesTransporte);

        eliminaLinhaOferta(quantidadeTransportada, custosRotalinhaUm, reino);
    }

    //REFATORAR TIRANDO CODIGO DUPLICADO COM O METODO PARA LINHA! (SE DER TEMPO)
    private void calculaTransporteSobreColuna(Reino reino, Map<String, Reino> mapaReinos) {
        Integer menorPenalidade = Integer.min(reino.getRotaFabricaUm(), reino.getRotaFabricaDois());
        Integer quantidadeTransportada;
        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);

        if (Objects.equals(menorPenalidade, reino.getRotaFabricaUm())) { //null safe mode
            quantidadeTransportada = calculaQuantidadeTransportada(reino.getDemanda(), ofertaFabricaUm);
            informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
            eliminaLinhaOferta(quantidadeTransportada, custosRotalinhaUm, reino);
        } else {
            quantidadeTransportada = calculaQuantidadeTransportada(reino.getDemanda(), ofertaFabricaDois);
            informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
            eliminaLinhaOferta(quantidadeTransportada, custosRotalinhaDois, reino);
        }
        this.transportes.add(informacoesTransporte);
    }

    public Reino buscarNoMapaOReino(Integer index, Map<String, Reino> mapaReinos) {
        int i = 0;
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            if (i == index) {
                return entrySet.getValue();
            }
            i++;
        }
        return null;
    }

    private Integer calculaQuantidadeTransportada(Integer demanda, Integer oferta) {
        oferta -= demanda;
        if (oferta > 0) {
            return demanda;
        } else {
            return oferta;
        }
    }

    private void eliminaLinhaOferta(Integer quantidadeTransportada, ArrayList<Integer> linha, Reino reino) {
        if (quantidadeTransportada < 0 || quantidadeTransportada == 0) {
            linha = null;
            reino.setDemanda(Math.abs(quantidadeTransportada));
        }
    }

    public ArrayList<Integer> getCustosRotalinhaUm() {
        return custosRotalinhaUm;
    }

    public ArrayList<Integer> getCustosRotalinhaDois() {
        return custosRotalinhaDois;
    }

}

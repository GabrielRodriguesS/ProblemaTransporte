/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import view.TelaResultado;

/**
 *
 * @author Gabriel
 */
public class CalculosVogel {

    Map<String, Reino> mapaReinos;
    private final ArrayList<Integer> custosRotalinhaUm;
    private final ArrayList<Integer> custosRotalinhaDois;
    private final ArrayList<Transporte> transportes;
    private static Integer ofertaFabricaUm = 1800;
    private static Integer ofertaFabricaDois = 700;

    public CalculosVogel(Map<String, Reino> mapaReinos) {
        this.custosRotalinhaDois = new ArrayList<>();
        this.custosRotalinhaUm = new ArrayList<>();
        this.transportes = new ArrayList<>();
        this.mapaReinos = mapaReinos;
    }

    public void transformaMapaEmArraySemDummy() {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        calculaVogel(false);
    }

    public void transformaMapaEmArrayComDummyOferta() {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        calculaVogel(true);
    }

    public void transformaMapaEmArrayComDummyDemanda() {
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            Reino value = entrySet.getValue();
            custosRotalinhaUm.add(value.getRotaFabricaUm());
            custosRotalinhaDois.add(value.getRotaFabricaDois());
        }
        custosRotalinhaDois.add(0);
        custosRotalinhaUm.add(0);

        calculaVogel(false);
    }

    public void calculaVogel(boolean dummyOferta) {
        if (!(ofertaFabricaUm == 0 && ofertaFabricaDois == 0) || !(custosRotalinhaUm.isEmpty() || custosRotalinhaDois.isEmpty())) {
            ArrayList<Integer> linhaComMaiorPenalidade = getMaiorPenalidadeDasLinhas();
            Reino reinoDaColunaComMaiorPenalidade = calculaMaiorPenalidadeColuna();
            Integer penalidadeLinha = calculaPenalidadePorLinha(linhaComMaiorPenalidade);

            if (penalidadeLinha > reinoDaColunaComMaiorPenalidade.getPenalidade()) {
                penalidadeLinha = this.getMenorCustoLinha(linhaComMaiorPenalidade);
                calculaTransporteSobreLinha(linhaComMaiorPenalidade, penalidadeLinha);
            } else {
                calculaTransporteSobreColuna(reinoDaColunaComMaiorPenalidade);
            }

            this.calculaVogel(dummyOferta);
        }
        TelaResultado tela = new TelaResultado(transportes);
    }

    //retona o array com maior penalidade entre as linhas
    public ArrayList<Integer> getMaiorPenalidadeDasLinhas() {
        Integer penalidadeLinhaUm;
        Integer penalidadeLinhaDois;
        if (custosRotalinhaUm.isEmpty()) {
            penalidadeLinhaUm = Integer.MIN_VALUE;
        } else {
            penalidadeLinhaUm = calculaPenalidadePorLinha(custosRotalinhaUm);
        }
        if (custosRotalinhaDois.isEmpty()) {
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
    public Reino calculaMaiorPenalidadeColuna() {
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

    public void calculaTransporteSobreLinha(ArrayList<Integer> custoLinha, Integer menorPenalidade) {
        if (this.custosRotalinhaUm.equals(custoLinha)) {
            calculosTransporteLinhaUm(menorPenalidade);
        } else {
            calculosTransporteLinhaDois(menorPenalidade);
        }
    }

    public Integer getMenorCustoLinha(ArrayList<Integer> custoLinha) {
        return Collections.min(custoLinha);

    }

    public void calculosTransporteLinhaDois(Integer menorPenalidade) {
        Integer index = this.custosRotalinhaDois.indexOf(menorPenalidade);
        Reino reino = buscarNoMapaOReino(index);
        Integer quantidadeTransportada;
        if (CalculosVogel.ofertaFabricaDois - reino.getDemanda() > 0) {
            quantidadeTransportada = reino.getDemanda();
            CalculosVogel.ofertaFabricaDois -= reino.getDemanda();
            mapaReinos.remove(reino.getNomeReino());
            this.custosRotalinhaDois.remove(reino.getRotaFabricaUm());
            removeColunaDosCustos(reino);
        } else if (CalculosVogel.ofertaFabricaDois - reino.getDemanda() < 0) {
            quantidadeTransportada = CalculosVogel.ofertaFabricaDois;
            CalculosVogel.ofertaFabricaDois = 0;
            this.custosRotalinhaDois.clear();
            reino.setDemanda(reino.getDemanda() - quantidadeTransportada);
            mapaReinos.replace(reino.getNomeReino(), reino);
        } else {
            quantidadeTransportada = CalculosVogel.ofertaFabricaDois;
            CalculosVogel.ofertaFabricaDois = 0;
            mapaReinos.remove(reino.getNomeReino());
            this.custosRotalinhaDois.clear();
        }

        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);
        informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
        this.transportes.add(informacoesTransporte);

    }

    public void calculosTransporteLinhaUm(Integer menorPenalidade) {
        Integer index = this.custosRotalinhaUm.indexOf(menorPenalidade);
        Reino reino = buscarNoMapaOReino(index);
        Integer quantidadeTransportada;

        if (CalculosVogel.ofertaFabricaUm - reino.getDemanda() > 0) {
            quantidadeTransportada = reino.getDemanda();
            CalculosVogel.ofertaFabricaUm -= reino.getDemanda();
            mapaReinos.remove(reino.getNomeReino());
            this.custosRotalinhaUm.remove(reino.getRotaFabricaUm());
            removeColunaDosCustos(reino);
        } else if (CalculosVogel.ofertaFabricaUm - reino.getDemanda() < 0) {
            quantidadeTransportada = CalculosVogel.ofertaFabricaUm;
            CalculosVogel.ofertaFabricaUm = 0;
            this.custosRotalinhaUm.clear();
            reino.setDemanda(reino.getDemanda() - quantidadeTransportada);
            mapaReinos.replace(reino.getNomeReino(), reino);
        } else {
            quantidadeTransportada = CalculosVogel.ofertaFabricaUm;
            CalculosVogel.ofertaFabricaUm = 0;
            mapaReinos.remove(reino.getNomeReino());
            this.custosRotalinhaUm.clear();
        }

        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);
        informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
        this.transportes.add(informacoesTransporte);

    }

    //REFATORAR TIRANDO CODIGO DUPLICADO COM O METODO PARA LINHA! (SE DER TEMPO)
    private void calculaTransporteSobreColuna(Reino reino) {
        Integer menorPenalidade = Integer.min(reino.getRotaFabricaUm(), reino.getRotaFabricaDois());
        Integer quantidadeTransportada;
        Transporte informacoesTransporte = new Transporte();
        informacoesTransporte.setNomeReino(reino.getNomeReino());
        informacoesTransporte.setPenalidade(menorPenalidade);

        if (Objects.equals(menorPenalidade, reino.getRotaFabricaUm())) { //null safe mode

            if (CalculosVogel.ofertaFabricaUm - reino.getDemanda() > 0) {
                quantidadeTransportada = reino.getDemanda();
                CalculosVogel.ofertaFabricaUm -= reino.getDemanda();
                mapaReinos.remove(reino.getNomeReino());
                this.custosRotalinhaUm.remove(reino.getRotaFabricaUm());
                removeColunaDosCustos(reino);
            } else if (CalculosVogel.ofertaFabricaUm - reino.getDemanda() < 0) {
                quantidadeTransportada = CalculosVogel.ofertaFabricaUm;
                CalculosVogel.ofertaFabricaUm = 0;
                this.custosRotalinhaUm.clear();
                reino.setDemanda(reino.getDemanda() - quantidadeTransportada);
                mapaReinos.replace(reino.getNomeReino(), reino);
            } else {
                quantidadeTransportada = CalculosVogel.ofertaFabricaUm;
                CalculosVogel.ofertaFabricaUm = 0;
                mapaReinos.remove(reino.getNomeReino());
                this.custosRotalinhaUm.clear();
            }
        } else {
            if (CalculosVogel.ofertaFabricaDois - reino.getDemanda() > 0) {
                quantidadeTransportada = reino.getDemanda();
                CalculosVogel.ofertaFabricaDois -= reino.getDemanda();
                mapaReinos.remove(reino.getNomeReino());
                this.custosRotalinhaDois.remove(reino.getRotaFabricaUm());
                removeColunaDosCustos(reino);
            } else if (CalculosVogel.ofertaFabricaDois - reino.getDemanda() < 0) {
                quantidadeTransportada = CalculosVogel.ofertaFabricaDois;
                CalculosVogel.ofertaFabricaDois = 0;
                this.custosRotalinhaDois.clear();
                reino.setDemanda(reino.getDemanda() - quantidadeTransportada);
                mapaReinos.replace(reino.getNomeReino(), reino);
            } else {
                quantidadeTransportada = CalculosVogel.ofertaFabricaDois;
                CalculosVogel.ofertaFabricaDois = 0;
                mapaReinos.remove(reino.getNomeReino());
                this.custosRotalinhaDois.clear();
            }
        }

        informacoesTransporte.setQuantidadeTransportada(quantidadeTransportada);
        this.transportes.add(informacoesTransporte);
    }

    public Reino buscarNoMapaOReino(Integer index) {
        Reino reino = null;
        int i = 0;
        for (Map.Entry<String, Reino> entrySet : mapaReinos.entrySet()) {
            reino = entrySet.getValue();
            if (i == index) {
                return reino;
            }
            i++;
        }
        return reino;
    }

    private Integer calculaQuantidadeTransportada(Integer demanda, Integer oferta) {
        oferta -= demanda;
        if (oferta > 0) {
            return demanda;
        } else {
            return oferta;
        }
    }

    public ArrayList<Integer> getCustosRotalinhaUm() {
        return custosRotalinhaUm;
    }

    public ArrayList<Integer> getCustosRotalinhaDois() {
        return custosRotalinhaDois;
    }

    private void removeColunaDosCustos(Reino reino) {
        int j = 0;
        for (Iterator<Integer> i = custosRotalinhaUm.iterator(); i.hasNext();) {
            Integer next = i.next();
            if (Objects.equals(i, reino.getRotaFabricaUm()) && Objects.equals(custosRotalinhaDois.get(j), reino.getRotaFabricaDois())) {
                i.remove();
                custosRotalinhaDois.remove(j);
            }
            j++;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Gabriel
 */
public class Transporte {

    private Integer quantidadeTransportada;
    private Integer penalidade;
    private String nomeReino;

    public Integer getQuantidadeTransportada() {
        return quantidadeTransportada;
    }

    public void setQuantidadeTransportada(Integer quantidadeTransportada) {
        this.quantidadeTransportada = quantidadeTransportada;
    }

    public Integer getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(Integer penalidade) {
        this.penalidade = penalidade;
    }

    public String getNomeReino() {
        return nomeReino;
    }

    public void setNomeReino(String nomeReino) {
        this.nomeReino = nomeReino;
    }

}

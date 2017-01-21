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
public class Reino implements Comparable<Reino> {

    private Integer rotaFabricaUm;
    private Integer rotaFabricaDois;
    private Integer demanda;
    private Integer penalidade;
    private static final Integer dummy = 0;

    public Integer getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(Integer penalidade) {
        this.penalidade = penalidade;
    }

    public Integer getRotaFabricaUm() {
        return rotaFabricaUm;
    }

    public static Integer getDummy() {
        return dummy;
    }

    public void setRotaFabricaUm(Integer rotaFabricaUm) {
        this.rotaFabricaUm = rotaFabricaUm;
    }

    public Integer getRotaFabricaDois() {
        return rotaFabricaDois;
    }

    public void setRotaFabricaDois(Integer rotaFabricaDois) {
        this.rotaFabricaDois = rotaFabricaDois;
    }

    public Integer getDemanda() {
        return demanda;
    }

    public void setDemanda(Integer demanda) {
        this.demanda = demanda;
    }

    @Override
    public int compareTo(Reino o) {
        if (this.getPenalidade() < o.getPenalidade()) {
            return -1;
        }
        if (this.getPenalidade() > o.getPenalidade()) {
            return 1;
        }
        return 0;
    }

}
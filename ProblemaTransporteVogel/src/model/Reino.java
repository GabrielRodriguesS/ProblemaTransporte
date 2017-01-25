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
    private Integer quantidadeTransportada;
    private String nomeReino;

    public Reino(Integer rotaFabricaUm, Integer rotaFabricaDois, Integer demanda, Integer penalidade, Integer quantidadeTransportada, String nomeReino) {
        this.rotaFabricaUm = rotaFabricaUm;
        this.rotaFabricaDois = rotaFabricaDois;
        this.demanda = demanda;
        this.penalidade = penalidade;
        this.quantidadeTransportada = quantidadeTransportada;
        this.nomeReino = nomeReino;
        this.setPenalidade();
    }

    public Reino() {
    }

    public String getNomeReino() {
        return nomeReino;
    }

    public void setNomeReino(String nomeReino) {
        this.nomeReino = nomeReino;
    }

    public Integer getPenalidade() {
        return penalidade;
    }

    public Integer getQuantidadeTransportada() {
        return quantidadeTransportada;
    }

    public void setQuantidadeTransportada(Integer quantidadeTransportada) {
        this.quantidadeTransportada = quantidadeTransportada;
    }

    public final void setPenalidade() {
        if (this.getRotaFabricaUm() > this.getRotaFabricaDois()) {
            this.penalidade = this.getRotaFabricaUm() - this.getRotaFabricaDois();
        } else {
            this.penalidade = this.getRotaFabricaDois() - this.getRotaFabricaUm();
        }
    }

    public Integer getRotaFabricaUm() {
        return rotaFabricaUm;
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
    public int compareTo(Reino outroReino) {
        if (this.getPenalidade() < outroReino.getPenalidade()) {
            return -1;
        } else if (this.getPenalidade() > outroReino.getPenalidade()) {
            return 1;
        }
        return 0;
    }

}

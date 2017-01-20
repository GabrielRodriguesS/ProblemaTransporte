package model;

public class ObjMatriz {

    private int linha, coluna = 5, i, j;
    private float matriz[][] = new float[linha][coluna];
    private float menor1, menor2, penalidade;
    private int posicaoLinha, posicaoColuna;
    private float[] vetorPenalidades = new float[7];

    public ObjMatriz() {
        this.linha = 2;
    }

    public float[][] getMatriz() {
        return matriz;
    }

    public float getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(float penalidade) {
        this.penalidade = penalidade;
    }

    public int getPosicaoLinha() {
        return posicaoLinha;
    }

    public void setPosicaoLinha(int posicaoLinha) {
        this.posicaoLinha = posicaoLinha;
    }

    public int getPosicaoColuna() {
        return posicaoColuna;
    }

    public void setPosicaoColuna(int posicaoColuna) {
        this.posicaoColuna = posicaoColuna;
    }

    public void setMatriz(float[][] matriz) {
        this.matriz = matriz;
    }

    public float getMenor1() {
        return menor1;
    }

    public void setMenor1(float menor1) {
        this.menor1 = menor1;
    }

    public float getMenor2() {
        return menor2;
    }

    public void setMenor2(float menor2) {
        this.menor2 = menor2;
    }

    public void calculaPenalidadeLinha(float matriz[][]) {
        /* Nas linhas do FOR sao calculados os 2 menores numeros; 
         depois é chamada a função "calculaPenalidade" que faz a subtração 
         dos 2 menores numeros encontrados. */

        for (i = 0; i < linha; i++) { //linha;
            for (j = 0; j < coluna; j++) { //coluna;s
                if (i == 1 & j == 0) {
                    menor1 = matriz[i][j];
                    menor2 = matriz[i][j + 1];
                }
                if (i == 0 & j == 0) {
                    menor1 = matriz[i][j];
                    menor2 = matriz[i][j + 1];
                }
                if (matriz[i][j] < menor1) {
                    menor2 = menor1;
                    menor1 = matriz[i][j];
                    setPosicaoColuna(j);
                } else if (matriz[i][j] < menor2 && !(matriz[i][j] == menor1)) {
                    menor2 = matriz[i][j];
                }
            }
            setPosicaoLinha(i);
            vetorPenalidades[i] = calculaPenalidade(menor1, menor2);
        }
    }

    public float calculaPenalidade(float menor1, float menor2) {
        return menor2 - menor1;
    }

}

package novo;

public class ObjMatriz {

    int linha = 2, coluna = 5, i, j;
    Float matriz[][] = new Float[linha][coluna];
    Float menor1, menor2, penalidade;
    int posicaoLinha, posicaoColuna;
    Float[] vetorPenalidades = new Float[7];

    public Float[][] getMatriz() {
        return matriz;
    }

    public Float getPenalidade() {
        return penalidade;
    }

    public void setPenalidade(Float penalidade) {
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

    public void setMatriz(Float[][] matriz) {
        this.matriz = matriz;
    }

    public Float getMenor1() {
        return menor1;
    }

    public void setMenor1(Float menor1) {
        this.menor1 = menor1;
    }

    public Float getMenor2() {
        return menor2;
    }

    public void setMenor2(Float menor2) {
        this.menor2 = menor2;
    }
  
    public void calculaPenalidadeLinha(Float matriz[][]) {
        /* Nas linhas do FOR sao calculados os 2 menores numeros; 
        depois é chamada a função "calculaPenalidade" que faz a subtração 
        dos 2 menores numeros encontrados. */
                
        for (i = 0; i < linha; i++) { //linha;

            for (j = 0; j < coluna; j++) { //coluna;

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
                } else if (matriz[i][j] < menor2 && !(matriz[i][j].equals(menor1))) {
                    menor2 = matriz[i][j];
                }
            }
            setPosicaoLinha(i);
            vetorPenalidades[i] = calculaPenalidade(menor1, menor2);
        }
    }

    public Float calculaPenalidade(Float menor1, Float menor2) {
        return menor2 - menor1;
    }
    
    

}

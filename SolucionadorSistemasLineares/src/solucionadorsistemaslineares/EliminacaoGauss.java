package solucionadorsistemaslineares;

/**
 * Classe responsável por resolver o sistema linear utilizando o 
 * método eliminação de Gauss
 * 
 * Está classe possui os métodos para:
 *  transformam a matriz fornecida pelo usuário em uma matriz triangular superior
 *  realiza a substituição retroativa das linhas
 */

public class EliminacaoGauss {
    private static final double EPS = 1e-9;
    /**
     * Método principal da classe
     *  é chamada no btnExecutar da interface 
     *  é quem chama os métodos que vão resolver o sistema
     * @param A matriz dos coeficientes fornecidos pelo usuario
     * @param B vetor dos termos indepedentes
     * @param t variavel que fornece o tamanho da matriz
     * @return vetor solução do sistema linear
     */
    public static double[] resolver(double[][] A, double[] B, int t) {
        // Clonando para não estragar os dados originais da interface
        double[][] matrizCopia = new double[t][t];
        double[] bCopia = new double[t];
        for (int i = 0; i < t; i++) {
            System.arraycopy(A[i], 0, matrizCopia[i], 0, t);
            bCopia[i] = B[i];
        }

        transformarTriangular(matrizCopia, bCopia,t);
        return resolverSubstituicao(matrizCopia, bCopia,t);
    }

    
    /**
     * 
     *Método que transforma a matriz fornecida pelo usuario em uma matriz triangular superior
     * @param matriz = matriz quadrada fornecida pelo usuario
     * @param termosIndependentes = vetor dos termos independentes do sistema
     * @param t tamanho da matriz
     */
    public static void transformarTriangular(double[][] matriz, double[] termosIndependentes, int t) {
        /* Percorre as colunas e define os pivôs --> pivo = matriz[k][k] */
        for (int k = 0; k < t - 1; k++) {
            /* verifica se o pivo é zero se for troca as linha da matriz*/
            if (Math.abs(matriz[k][k]) < EPS) {
                for (int i = k + 1; i < t; i++) {
                    if (Math.abs(matriz[i][k]) > EPS) {
                        // troca linhas da matriz
                        double[] temp = matriz[k];
                        matriz[k] = matriz[i];
                        matriz[i] = temp;
                        // troca termos independentes
                        double tempB = termosIndependentes[k];
                        termosIndependentes[k] = termosIndependentes[i];
                        termosIndependentes[i] = tempB;
                        break;
                    }
                }
            }           
            /* Percorre as linhas abaixo do pivô --> elementos abaixo do pivô precisam ser zerados*/
            for (int i = k + 1; i < t; i++) { 
                /* Calcula o fator multiplicador (elemento a ser zerado / pivô)*/
                double fator = matriz[i][k] / matriz[k][k];
                /** Aplica a operação em todos os elementos da linha 
                 * Linha n' = linha n - (fator multiplicado * linhaPivo)
                */
                for (int j = k; j < t; j++) {
                    matriz[i][j] -= fator * matriz[k][j];
                }
                //aplicar o mesmo fator ao vetor de termos independentes
                termosIndependentes[i] -= fator * termosIndependentes[k];
                
            }
        }
        /*verificação se o sistema é SI ou SPI*/
        for (int i = 0; i < t; i++) {
            boolean linhaZero = true;
            for (int j = 0; j < t; j++) {
                if (Math.abs(matriz[i][j]) > EPS) {
                    linhaZero = false;
                    break;
                }
            }
            if (linhaZero) {
                if (Math.abs(termosIndependentes[i]) > EPS){
                    // 0 = número → impossível
                    throw new ArithmeticException("sistema_impossivel");
                } else {
                    // 0 = 0 → infinitas soluções
                    throw new ArithmeticException("sistema_indeterminado");
                }
            }
        }
    }                     
        
    /**
     * Método que resolve o sistema triangular superior por substituição retroativa
     * @param matriz matriz tringular superior
     * @param b vetor dos termos independentes
     * @param t tamanho da matriz
     * @return vetor solucao (valores das incognitas)
     */
    public static double[] resolverSubstituicao(double[][] matriz, double[] b, int t) {
         /*vetor para armazenar o valor das incognitas*/
        double[] x = new double[t];
        /** 
         * Inicia a resolução pela última variável do sistema--> a mais de baixo 
         * primeiro pois ela tem apenas uma incógnita 
         * Para achar o valor da incógnita --> faz-se a divisão do último termo 
         * independente do vetor pelo último número da diagonal da matriz 
         * (único elemento da última linha da matriz) 
        */
        x[t - 1] = b[t - 1] / matriz[t - 1][t - 1];

        /* Segue a resolução do restante do sistema de BAIXO para CIMA*/
        for (int i = t - 2; i >= 0; i--) {
            // variavel que armazena os valores já conhecidos (incognita*coeficiente)
            double soma = 0;
            // Multiplica os coeficientes pelas incgonicas que já descobertas
            for (int j = i + 1; j < t; j++) {
                soma += matriz[i][j] * x[j];
            }
            
            if (Math.abs(matriz[i][i]) < EPS) {
                throw new ArithmeticException("sistema_indeterminado");
            }
            /**
             * x[i] = valor da incognita
             * b[i]-soma = termo independete - valores já descobertos 
             * matriz[i][i] = coeficiente da incognita a ser descoberta
             */
            x[i] = (b[i] - soma) / matriz[i][i];
        }
        return x; // Retorna o vetor com os valores das incognitas (x, y, z...)
    }
}


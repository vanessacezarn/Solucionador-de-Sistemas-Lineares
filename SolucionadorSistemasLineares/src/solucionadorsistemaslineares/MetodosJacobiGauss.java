/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package solucionadorsistemaslineares;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Classe responsável por resolver o sistema linear utilizando os 
 * métodos de Jacobi e Gauss-Seidel
 * 
 * Está classe possui os métodos para:
 *  resolver por Jacobi
 *  resolver por Gauss-Seidel
 */

public class MetodosJacobiGauss {
    /**
     * Método para resolver por Jacobi
     *  é chamada no btnExecutar da interface 
     * @param A matriz dos coeficientes fornecidos pelo usuario
     * @param b vetor dos termos indepedentes
     * @param t variavel que fornece o tamanho da matriz
     * @param erro variavel que guarda a precisão do erro
     * @param maxIter variavel que fornece o número máximo de iterações
     * @return vetor solução do sistema linear
     */
    public static double[] resolverJacobi(double[][] A, double[] b, int t, double erro, int maxIter) {
        double[] x = new double[t];      
        double[] xNovo = new double[t];

        for (int iter = 0; iter < maxIter; iter++) {
            for (int i = 0; i < t; i++) {
                double soma = b[i];
                for (int j = 0; j < t; j++) {
                    if (j != i) soma -= A[i][j] * x[j];
                }
                xNovo[i] = soma / A[i][i];
            }

            // Critério de parada: erro relativo máximo
            double erroAtual = 0;
            for (int i = 0; i < t; i++) {
                double e = Math.abs((xNovo[i] - x[i]) / (xNovo[i] == 0 ? 1 : xNovo[i]));
                erroAtual = Math.max(erroAtual, e);
            }

            x = xNovo.clone();

            if (erroAtual < erro){
                JOptionPane.showMessageDialog(null,
                        "Número de iterações:" + (iter+1),
                        "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
                return x;
            }
        }

        throw new ArithmeticException("nao_convergiu");
    }
    
    /**
     * Método para resolver por Gauss-Seidel
     *  é chamada no btnExecutar da interface 
     * @param A matriz dos coeficientes fornecidos pelo usuario
     * @param b vetor dos termos indepedentes
     * @param t variavel que fornece o tamanho da matriz
     * @param erro variavel que guarda a precisão do erro
     * @param maxIter variavel que fornece o número máximo de iterações
     * @return vetor solução do sistema linear
     */
    public static double[] resolverGaussSeidel(double[][] A, double[] b, int t, double erro, int maxIter) {
        double[] x = new double[t];

        for (int iter = 0; iter < maxIter; iter++) {
            double[] xAnterior = x.clone();

            for (int i = 0; i < t; i++) {
                double soma = b[i];
                for (int j = 0; j < t; j++) {
                    if (j != i) soma -= A[i][j] * x[j]; // usa x já atualizado
                }
                x[i] = soma / A[i][i];
            }

            double erroAtual = 0;
            for (int i = 0; i < t; i++) {
                double e = Math.abs((x[i] - xAnterior[i]) / (x[i] == 0 ? 1 : x[i]));
                erroAtual = Math.max(erroAtual, e);
            }

            if (erroAtual < erro){
                JOptionPane.showMessageDialog(null,
                        "Número de iterações:" + (iter+1),
                        "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
                return x;
            }
        }

        throw new ArithmeticException("nao_convergiu");
    }
}

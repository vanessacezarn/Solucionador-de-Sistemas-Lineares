# Ánalise Comparativa

## Sistema utilizado
```
2x1 - x2 = 1
x1 + 2x2 = 3
```
Precisão de erro = ``0.02``
Máximo de iterações = ``10``


## Comparação entre os métodos
**Eliminação de Gaus**
- O sistema chegou ao resultado
- Vetor resultante = ``[1.0, 1,0]``

**Método de Jacobi**
- O sistema convergiu em 8 iteraçoes
- Vetor resultante = ``[0.996, 0.996]``

**Método de Gauss-Seidel**
- O sistema convergiu em 5 iteraçoes
- Vetor resultante = ``[0.998, 1.000]``


## Conclusão
A Eliminação de Gauss é um método direto e deterministica pois sempre encontra a solução (se existir). Já os métodos iterativos dependem de dois fatores definidos pelo usuário: a precisão de erro e o número máximo de iterações. Em nosso exemplo, caso o máximo de iterações do exemplo fosse 7, o método de Jacobi não teria convergido. O método de Gauss-Seidel converge em um número menor de iterações pois aproveita os valores já atualizados. No entanto, ambos falham se a matriz não for diagonal dominante.

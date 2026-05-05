# Solucionador de Sistemas Lineares
### 📌 Objetivo do Sistema
Desenvolver uma aplicação com interface gráfica capaz de resolver sistemas de equações lineares do tipo **Ax = b**, utilizando:

- um método direto: **Eliminação de Gauss**
- dois métodos iterativos: **Jacobi** e **Gauss-Seidel**

O sistema foi projetado para ser utilizado de forma intuitiva, sem necessidade de execução via terminal.

---

### ⚙️ Funcionalidades

- Seleção do método de resolução:
  - Eliminação de Gauss
  - Método de Jacobi
  - Método de Gauss-Seidel

- Definição do tamanho do sistema (matriz NxN)

- Grade interativa para entrada dos coeficientes da matriz A e vetor B dos termos independentes

- Para métodos iterativos:
  - Campo para precisão (erro tolerado)
  - Número máximo de iterações

- Exibição do resultado:
  - Vetor solução do sistema
  - Mensagens de erro amigáveis quando não for possível resolver
  - Para métodos iterativos:
    - Número de iterações necessárias para convergência

---

### Métodos Implementados

#### 🔹 Eliminação de Gauss
Transforma a matriz de coeficientes em uma **matriz triangular superior** e, em seguida, aplica **substituição retroativa** para encontrar a solução.

#### 🔹 Método de Jacobi
É um método iterativo que isola cada variável na diagonal principal. A cada passo, utiliza apenas os valores da iteração anterior para calcular os novos, convergindo para a solução se a matriz for diagonalmente dominante.

#### 🔹 Método de Gauss-Seidel
Uma evolução do Jacobi que utiliza os valores recém-calculados da iteração atual assim que ficam disponíveis. Essa atualização imediata geralmente acelera a convergência, exigindo menos iterações para atingir a precisão desejada.  


---

### ⚠️ Tratamento de Erros

O sistema trata  situações como:
- Entrada inválida do usuário
- Sistema impossível (sem solução)
- Sistema indeterminado (infinitas soluções)
- Não convergência dos métodos iterativos

---

### 🛠️ Tecnologias Utilizadas

- Java
- Swing (interface gráfica)
- NetBeans

---

### Interface do Sistema
<div align="center">
  <img width="577" height="491" alt="image" src="https://github.com/user-attachments/assets/5a09cb45-1d7e-4ba2-a3b8-0b8ba2f0955b" />

</div>

---

### 🚀 Como Executar

1. Clone o repositório
2. Abra o projeto no NetBeans
3. Execute a aplicação
4. Preencha os dados e escolha o método desejado
---


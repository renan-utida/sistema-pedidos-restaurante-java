# Sistema de Pedidos para Restaurante

## Descrição

Este projeto consiste em um sistema de gerenciamento de pedidos para um restaurante, desenvolvido em Java. O sistema permite que o usuário gerencie o cardápio de pratos, crie e edite pedidos, calcule o total de cada pedido e visualize detalhes dos pedidos de forma organizada e eficiente.

O sistema foi projetado para ser intuitivo e fácil de usar, com menus interativos que guiam o usuário através das funcionalidades disponíveis. Além disso, ele conta com validações de dados para garantir a integridade das informações inseridas e persistência de dados para salvar e carregar automaticamente o cardápio e os pedidos em um arquivo de texto.

### Objetivos
- Gerenciar o cardápio de pratos, permitindo adicionar, remover e listar pratos.
- Criar e gerenciar pedidos, incluindo a adição e remoção de pratos, cálculo do total e visualização de detalhes.
- Garantir a persistência dos dados, salvando e carregando automaticamente as informações do sistema.

### Tecnologias Utilizadas
- **Linguagem**: Java
- **Persistência de Dados**: Arquivo de texto (`restaurante.txt`)
- **Ferramentas**: Tratamento de exceções, validação de entradas, manipulação de arquivos.

<img src="https://github.com/user-attachments/assets/fc2e0fc7-e5d9-40a9-8681-9f0c09c23705" alt="java-logo" width="200" height="auto"/>

---

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

### Gerenciamento do Cardápio
- **Adicionar Prato**: Adiciona um novo prato ao cardápio com nome, preço e descrição.
- **Remover Prato**: Remove um prato do cardápio.
- **Listar Cardápio**: Exibe todos os pratos disponíveis no cardápio.

### Gerenciamento de Pedidos
- **Criar Pedido**: Cria um novo pedido associado a um cliente.
  - **Adicionar Prato ao Pedido**: Adiciona um prato do cardápio a um pedido específico.
  - **Remover Prato do Pedido**: Remove um prato de um pedido específico.
  - **Listar Pedido**: Exibe todos os pratos registrados no pedido.
  - **Finalizar Pedido**: Finaliza o pedido do cliente, mostrando o valor total gasto.
  - **Cancelar Pedido**: Cancela o pedido do cliente.
- **Listar Todos os Pedidos**: Exibe todos os pedidos registrados.
- **Visualizar Pedido Específico**: Exibe os detalhes de um pedido específico.
- **Editar Pedido Específico**: Permite adicionar ou remover pratos de um pedido existente.
- **Remover Pedido Específico**: Remove um pedido específico.
- **Limpar Todos os Pedidos**: Remove todos os pedidos registrados.

### Persistência de Dados
- Os dados do cardápio e dos pedidos são salvos em um arquivo de texto (`restaurante.txt`) e carregados automaticamente ao iniciar o sistema.

---

## Estrutura do Projeto

O projeto é composto por 12 classes, organizadas da seguinte forma:

### Classes Principais
- **Principal**: Classe principal que inicia o sistema e gerencia o menu de opções.
- **Restaurante**: Classe central que gerencia o cardápio e os pedidos.
- **Prato**: Representa um prato do cardápio, com atributos como nome, preço e descrição.
- **Pedido**: Representa um pedido, com atributos como número do pedido, cliente, lista de pratos e total.
- **ItemPedido**: Representa um item de um pedido, contendo um prato e a quantidade solicitada.

### Classes de Gerenciamento
- **GerenciarCardapio**: Gerencia as operações relacionadas ao cardápio (adicionar, remover e listar pratos).
- **GerenciarPedidos**: Gerencia as operações relacionadas aos pedidos (criar, editar, remover e listar pedidos).
- **MenuPedido**: Gerencia o menu de opções para adicionar/remover pratos de um pedido específico.

### Classes Utilitárias
- **Utilidades**: Contém métodos utilitários para formatação de valores e exibição de listas.
- **LeituraDeDados**: Contém métodos para leitura e validação de dados de entrada.
- **CarregarDados**: Responsável por carregar os dados do arquivo de texto ao iniciar o sistema.
- **SalvarDados**: Responsável por salvar os dados do cardápio e pedidos em um arquivo de texto.

---

## Como Executar o Projeto

### Pré-requisitos
- Java Development Kit (JDK) instalado (versão 11 ou superior recomendada).
- Um terminal ou IDE para compilar e executar o código.

### Passos para Execução
1. Clone o repositório ou baixe os arquivos do projeto.
2. Navegue até o diretório do projeto.
3. Compile o código:
   ```bash
   javac -d . src/org/example/*.java
   ```
4. Execute o programa:
   ```bash
   java org.example.Principal
   ```
5. Siga as instruções exibidas no menu para interagir com o sistema.

---

## Exemplo de Uso

### Adicionar um Prato ao Cardápio
1. No menu principal, selecione a opção 1 - Gerenciar Cardápio.
2. Escolha a opção 1 - Adicionar Prato.
3. Insira o nome, preço e descrição do prato.

### Criar um pedido
1. No menu principal, selecione a opção 2 - Gerenciar Pedidos.
2. Escolha a opção 1 - Criar Pedido.
3. Insira o número do pedido e o nome do cliente.
4. Adicione pratos ao pedido e finalize quando estiver pronto.

### Visualizar um pedido
1. No menu principal, selecione a opção 2 - Gerenciar Pedidos.
2. Escolha a opção 3 - Visualizar Pedido Específico.
3. Insira o número do pedido que deseja visualizar.

---

## Melhorias Implementadas
- **Validação de Entradas**: O sistema valida entradas como nomes, preços e quantidades para evitar erros.
- **Persistência de Dados**: Os dados são salvos em um arquivo de texto e carregados automaticamente ao iniciar o sistema.
- **Interface Amigável**: O sistema possui menus intuitivos e mensagens claras para facilitar o uso.

---

## Desenvolvedor

[<img loading="lazy" src="https://github.com/user-attachments/assets/b4f96f4b-542e-4988-9bc1-b1acf22a41a1" width=115><br><sub>Renan Dias Utida</sub>](https://github.com/renan-utida)

### Linkedin: https://www.linkedin.com/in/renan-dias-utida-1b1228225/

---

## Considerações Finais

Este projeto foi desenvolvido com o objetivo de criar um sistema de gerenciamento de pedidos para um restaurante, utilizando Java e boas práticas de programação. Através da implementação de classes bem definidas e da separação de responsabilidades, o sistema se torna modular, de fácil manutenção e expansão.

### Pontos Fortes
- **Organização do Código**: O código foi estruturado em classes coesas, cada uma com uma responsabilidade clara, o que facilita a leitura e a manutenção.
- **Validação de Dados**: O sistema realiza validações robustas para garantir a integridade dos dados inseridos pelo usuário.
- **Persistência de Dados**: A capacidade de salvar e carregar dados em um arquivo de texto garante que as informações não sejam perdidas entre execuções do programa.
- **Interface de Usuário Amigável**: O sistema oferece menus intuitivos e mensagens claras, tornando a interação com o usuário simples e eficiente.

### Desafios Encontrados
- **Gerenciamento de Exceções**: Foi necessário implementar um tratamento de exceções cuidadoso para lidar com entradas inválidas e evitar falhas no sistema.
- **Persistência de Dados**: A implementação da leitura e escrita de dados em arquivos exigiu atenção para garantir que os dados fossem armazenados e recuperados corretamente.

### Aprendizados
- **Boas Práticas de Programação**: O projeto reforçou a importância de seguir boas práticas, como a separação de responsabilidades, o uso de métodos bem definidos e a documentação do código.
- **Tratamento de Exceções**: A implementação de um tratamento de exceções eficaz foi essencial para garantir a robustez do sistema.
- **Persistência de Dados**: A experiência com leitura e escrita de arquivos trouxe um entendimento prático de como gerenciar dados persistentes em aplicações.

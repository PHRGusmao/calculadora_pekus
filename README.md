# Calculadora PEKUS by PHRGUSMAO 
![Logo Pekus](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRclDBimL3-Q-Pzkb7zbb0Ye5qYwTbSYjgLWg&s)

Este é um projeto de uma calculadora desenvolvida por mim, PHRGUSMAO, como parte do processo seletivo para a posição de trainee. O aplicativo foi construído utilizando Java e SQLite no ambiente Android Studio, sendo meu primeiro projeto voltado para o desenvolvimento de aplicativos Android.

## Índice

- Finalidade do Projeto
- Estrutura do Aplicativo
  - 1. Tela Inicial (Input de Dados e Operações)
  - 2. Segunda Tela (Exibição dos Registros)
  - 3. Banco de Dados (SQLite)
  - 4. Classes Principais
  - 5. Passos de Implementação
- Observação Importante

## Finalidade do Projeto 🎯

O objetivo principal deste projeto é desenvolver uma ferramenta intuitiva e eficiente para a realização de operações matemáticas básicas, como adição, subtração, multiplicação e divisão. Além de fornecer uma interface amigável para o usuário, o aplicativo também é capaz de registrar cada operação realizada, permitindo a visualização de um histórico detalhado das atividades anteriores.

Adicionalmente, este projeto visa atender às exigências do processo seletivo da empresa PEKUS, demonstrando minhas habilidades técnicas e comprometimento com a excelência no desenvolvimento de aplicativos. Através desta iniciativa, espero contribuir de maneira significativa para a equipe e para o desenvolvimento de soluções inovadoras na área.

## Design 🎨

O design da aplicação foi desenvolvido no Figma, proporcionando uma interface visual intuitiva e agradável ao usuário.
- [Link para acessar o projeto](https://www.figma.com/design/EIujYGlsmgdVfL3zxFczhk/Calculadora-Pekus?node-id=0-1&t=3ytER5YIrWO9j2r3-1).

## Estrutura do Aplicativo 📱

### 1. Tela Inicial (Input de Dados e Operações)
- Dois campos de entrada para ValorA e ValorB utilizando EditText.
- Botões para operações matemáticas: adição, subtração, multiplicação e divisão.
- Validação dos campos de entrada para garantir que ambos sejam números.
- Exibição de uma mensagem de sucesso ao clicar em um botão de operação e limpeza dos campos.
- Um botão para navegar para a tela de histórico.

### 2. Segunda Tela (Exibição dos Registros)
- Exibição de uma RecyclerView para mostrar as operações armazenadas.
- Cada item da lista contém: ID, ValorA, ValorB, operação, resultado e data/hora.
- Um botão para excluir registros.
- Um botão para ordenação crescente e descrescente.
- Um botão para retornar à primeira tela.

### 3. Banco de Dados (SQLite) 💾
- Criação de um banco de dados SQLite na primeira execução do aplicativo.
- Uma tabela com as colunas: ID, ValorA, ValorB, operação, resultado e data/hora.
- Cada operação gravada gera um novo registro no banco.

### 4. Classes Principais 🛠️
- **MainActivity.java**: Tela inicial com os campos de entrada e botões de operação.
- **DatabaseHelper.java**: Classe para gerenciar o SQLite (criação da tabela, inserção de dados e consulta).
- **ActivityResults.java**: Tela para exibição dos registros gravados.
- **Result.java**: Classe modelo que representa uma operação matemática, contendo atributos como ID, ValorA, ValorB, operação, resultado e data/hora.
- **ResultRepository.java**: Classe responsável por gerenciar operações de acesso a dados, incluindo inserção, consulta, atualização e exclusão de registros no banco de dados.

### 5. Passos de Implementação 🚀
- **SQLiteOpenHelper**: Utilização dessa classe para criar e gerenciar o banco de dados na primeira execução.
- **RecyclerView**: Utilização para listar os registros das operações na segunda tela.
- **Intents**: Navegação entre as telas.

## Observação Importante ⚠️

Por questões de avaliação, eu permiti o commit e o push de arquivos não convencionais ao repositório GitHub. Esta decisão foi tomada para demonstrar o progresso e a evolução do projeto, mesmo que isso inclua arquivos que não seguem a convenção padrão de estrutura de projeto.

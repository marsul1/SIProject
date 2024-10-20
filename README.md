# Departamento de Engenharia de Eletrónica e Telecomunicações e de Computadores

### Licenciatura em Engenharia Informática e de Computadores

## Trabalho prático (Fases 1 e 2)

### Sistemas de Informação

#### Semestre de verão de 2022/2023

**Docentes:** Afonso Remédios, Nuno Leite e Walter Vieira

---

## Planeamento

As datas importantes a recordar são:

- **Lançamento do enunciado:** 17 de março de 2023
- **Entrega intermédia (Fase 1):** 1 de maio de 2023
- **Entrega intermédia (Fase 2):** 29 de maio de 2023

Cada entrega intermédia deve apresentar o relatório e código (se houver) referentes exclusivamente a essa fase. O relatório deve seguir um dos modelos fornecidos, sob pena de penalização na nota. Deve ser conciso e apresentar a justificação de todas as decisões tomadas. A capa do relatório deve indicar a composição do grupo, a unidade curricular e a fase do trabalho a que respeita. Caso tenha adendas e/ou correções a fazer, deve indicá-las no relatório seguinte.

O ficheiro pdf (ou zip) gerado deve seguir o padrão: `TPSISINF-2223SV-GrupoNNTFaseN.ext`. N representa um dígito, T a turma (D1, D2, D3, N) e `ext` a extensão do ficheiro, exemplo: `TPSISINF-2223SV-Grupo14D2Fase1.zip` ou `TPSISINF-2223SV-Grupo02NFase1.zip`.

---

## Primeira fase

### Objetivos de aprendizagem

No final da primeira fase do trabalho, os alunos deverão ser capazes de:

- Desenvolver um modelo de dados adequado aos requisitos, normalizado até à 3NF;
- Conceber e implementar uma solução baseada em bases de dados dinâmicas, adequada aos requisitos;
- Utilizar corretamente controlo transacional;
- Utilizar corretamente níveis de isolamento;
- Utilizar corretamente vistas;
- Utilizar corretamente procedimentos armazenados;
- Utilizar corretamente gatilhos;
- Utilizar corretamente funções;
- Justificar o uso de vistas, procedimentos armazenados, gatilhos e funções;
- Desenvolver código de teste em PL/pgSQL para as funcionalidades requeridas;
- Desenvolver código PL/pgSQL para criar todos os objetos necessários a partir de uma base de dados vazia;
- Escrever um relatório técnico sobre as decisões e o trabalho desenvolvido.

### Enunciado do trabalho (documento de requisitos do sistema)

A empresa "GameOn" pretende desenvolver um sistema para gerir jogos, jogadores e partidas. O sistema deve registar os dados dos jogadores, identificados por um id gerado pelo sistema, com email e username como valores únicos e obrigatórios. O jogador pode estar em um dos estados: 'Ativo', 'Inativo' ou 'Banido' e pertence a uma região.

Os jogos são identificados por uma referência alfanumérica de 10 caracteres, com nome obrigatório e único e um URL com os detalhes do jogo. Registra-se os jogadores que compraram um jogo, com a data e o preço da compra. Cada vez que o jogo é jogado, é criada uma partida com um número sequencial único para cada jogo. Devem ser registradas as datas e horas de início e fim da partida, sendo a partida normal (um jogador) ou multi-jogador.

As partidas normais incluem informações sobre grau de dificuldade e a pontuação do jogador. As partidas multi-jogador incluem informações sobre os jogadores e suas pontuações. A partida está associada a uma região, e apenas jogadores dessa região podem jogar. Cada jogo pode ter crachás que são atribuídos aos jogadores quando atingem um limite de pontos.

Existem estatísticas associadas a jogadores e jogos. Para cada jogador, registra-se o número de partidas, jogos diferentes jogados e o total de pontos. Para cada jogo, registra-se o número de partidas, jogadores e o total de pontos.

Os jogadores podem adicionar outros como amigos, registrar conversas com mensagens associadas a uma ordem, data, hora e texto.

### Resultados pretendidos

Os resultados esperados incluem:

## 1. Modelo de dados (conceitual e relacional) com todas as restrições de integridade.
## 2. O código PL/pgSQL que permite:

- (a) Criar o modelo físico (1 script autónomo)
Criação de todas as tabelas e relações necessárias para o sistema.

-  (b) Remover o modelo físico (1 script autónomo)
Script que remove todas as tabelas e relações criadas.

-  (c) Preenchimento inicial da base de dados (1 script autónomo)
Inserção de dados iniciais para começar a utilização do sistema.

-  (d) Criar os mecanismos que permitam criar o jogador, dados os seus email, região e username, desativar e banir o jogador
Procedimentos armazenados para:

- Criar um jogador com `email`, `região` e `username`.
- Desativar um jogador.
- Banir um jogador.

-  (e) Criar, sem usar as tabelas de estatísticas, a função `totalPontosJogador`
Recebe como parâmetro o identificador de um jogador e devolve o número total de pontos obtidos por esse jogador.

-  (f) Criar, sem usar as tabelas de estatísticas, a função `totalJogosJogador`
Recebe como parâmetro o identificador de um jogador e devolve o número total de jogos diferentes nos quais o jogador participou.

-  (g) Criar a função `PontosJogoPorJogador`
Recebe como parâmetro a referência de um jogo e devolve uma tabela com duas colunas (`identificador de jogador`, `total de pontos`). Apenas são devolvidos os jogadores que tenham jogado o jogo.

-  (h) Criar o procedimento armazenado `associarCrachá`
Recebe como parâmetros o identificador de um jogador, a referência de um jogo e o nome de um crachá. Atribui o crachá ao jogador se ele reunir as condições para obtê-lo.

-  (i) Criar o procedimento armazenado `iniciarConversa`
Inicia uma conversa (chat) dados o identificador de um jogador e o nome da conversa. O jogador é automaticamente associado à conversa. O procedimento devolve, num parâmetro de saída, o identificador da conversa criada.

-  (j) Criar o procedimento armazenado `juntarConversa`
Recebe como parâmetros os identificadores de um jogador e de uma conversa e junta esse jogador à conversa.

-  (k) Criar o procedimento armazenado `enviarMensagem`
Recebe como parâmetros os identificadores de um jogador, de uma conversa e o texto de uma mensagem. Envia a mensagem para a conversa indicada, associando-a ao jogador.

-  (l) Criar a vista `jogadorTotalInfo`
Permite acessar as seguintes informações de todos os jogadores cujo estado seja diferente de "Banido":

 Identificador,
 Estado,
 Email,
 Username,
 Número total de jogos em que participou,
 Número total de partidas em que participou,
 Número total de pontos obtidos,

Os cálculos são implementados sem acessar as tabelas de estatísticas.

-  (m) Criar os mecanismos necessários para atribuir crachás automaticamente
Quando uma partida termina, os crachás do jogo a que pertence a partida são atribuídos automaticamente.

-  (n) Criar os mecanismos necessários para a instrução `DELETE` sobre a vista `jogadorTotalInfo`
A execução da instrução `DELETE` sobre a vista deve permitir colocar os jogadores envolvidos no estado "Banido".

-  (o) Criar um script autónomo com testes das funcionalidades de 2d a 2n
Criação de um script de testes que cobre cenários normais e de erro para as funcionalidades descritas em 2d a 2n. Para cada teste, o script deve listar o nome e o resultado:

- Exemplo de sucesso: `teste 1: Inserir jogador com dados bem passados: Resultado OK`
- Exemplo de falha: `teste 1: Inserir Cliente com dados bem passados: Resultado FAIL`


---

## Segunda fase

## Objetivos de aprendizagem

No final da segunda fase do trabalho, os alunos devem ser capazes de:

- Desenvolver uma camada de acesso a dados, que use uma implementação de JPA e um subconjunto dos padrões de desenho **DataMapper**, **Repository** e **UnitOfWork**;
- Desenvolver uma aplicação em Java, que use adequadamente a camada de acesso a dados;
- Utilizar corretamente processamento transacional, através de mecanismos disponíveis no JPA;
- Garantir a correta libertação de ligações e recursos, quando estes não estejam a ser utilizados;
- Garantir a correta implementação das restrições de integridade e/ou lógica de negócio.

---

## Resultados pretendidos

Tendo em conta os objetivos de aprendizagem, deverão ser produzidos os seguintes resultados:

### 1. Criação de uma aplicação Java que permita:

- (a) Aceder às funcionalidades **2d a 2l**, descritas na fase 1 deste trabalho;
- (b) Realizar a funcionalidade **2h**, descrita na fase 1 deste trabalho, sem usar qualquer procedimento armazenado nem qualquer função PL/pgSQL;
- (c) Realizar a funcionalidade **2h**, descrita na fase 1 deste trabalho, reutilizando os procedimentos armazenados e funções que a funcionalidade original usa.

### 2. Funcionalidades adicionais:

- (a) Usando **optimistic locking**, aumentar em **20%** o número de pontos associados a um crachá, dados o nome do crachá e o identificador do jogo a que ele pertence.
- (b) Testar a alínea anterior, apresentando uma mensagem de erro adequada em caso de alteração concorrente conflituante que inviabilize a operação. No relatório deve estar descrita a forma como as situações de erro foram criadas para teste desta alínea.
- (c) Realizar o mesmo que em (a), mas usando **controlo de concorrência pessimista**.

---

### Notas:

- Todas as alíneas devem ter tratamento de erros e, nos casos relevantes, gestão transacional utilizando o nível de isolamento adequado de forma explícita.
- O código a desenvolver deverá estar organizado em vários projetos que reflitam a estrutura modular da aplicação, a qual deverá reduzir o acoplamento entre módulos e facilitar a identificação das dependências entre eles.

---

**Data limite para entrega:** 12 de junho de 2023 até às 23:50.

A entrega deve incluir:

- Um **relatório** (em formato PDF);
- Os projetos **MAVEN**;
- O código **Java**;
- O código **PL/pgSQL**;

Tudo deve ser entregue via Moodle. O relatório deverá obedecer ao padrão fornecido pelos docentes.



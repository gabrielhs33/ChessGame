<h1 align="center">♟ Chess Game - Java Project</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-17-%23ED8B00?style=flat&logo=openjdk&logoColor=white" alt="Java 17">
  <img src="https://img.shields.io/badge/OOP-Design-%237F52FF" alt="OOP Design">
  <img src="https://img.shields.io/github/last-commit/gabrielhs33/ChessGame" alt="Last Commit">
</p>

<p align="center">Projeto de xadrez desenvolvido em Java como conclusão da disciplina de Programação 2, implementando todas as regras clássicas do jogo com interface no terminal.</p>

<h2>🏗️ Estrutura do Projeto</h2>

<pre>
ChessGame/
├── src/
│   ├── application/
│   │   ├── Program.java          # Ponto de entrada do jogo
│   │   └── UI.java              # Interface do usuário (terminal)
│   │
│   ├── boardgame/
│   │   ├── Board.java           # Lógica do tabuleiro
│   │   ├── Piece.java          # Classe base para peças
│   │   └── Position.java       # Sistema de posicionamento
│   │
│   └── chess/
│       ├── ChessMatch.java      # Controle da partida
│       ├── ChessPiece.java     # Peças específicas do xadrez
│       ├── ChessPosition.java  # Conversão de posições
│       ├── Color.java          # Enumeração de cores
│       └── pieces/
│           ├── King.java       # Implementação do Rei
│           ├── Queen.java      # Implementação da Rainha
│           ├── Rook.java       # Implementação da Torre
│           ├── Bishop.java     # Implementação do Bispo
│           ├── Knight.java     # Implementação do Cavalo
│           └── Pawn.java       # Implementação do Peão
│
├── bin/                        # Classes compiladas
└── .gitignore
</pre>

<h2>▶️ Como Executar</h2>

<ol>
  <li><strong>Pré-requisitos</strong>: Java 17+ instalado</li>
  <li>Clone o repositório:
    <pre><code>git clone https://github.com/gabrielhs33/ChessGame.git</code></pre>
  </li>
  <li>Compile o projeto:
    <pre><code>javac -d bin src/application/Program.java</code></pre>
  </li>
  <li>Execute o jogo:
    <pre><code>java -cp bin application.Program</code></pre>
  </li>
</ol>

<h2>🎮 Funcionalidades Implementadas</h2>
<ul>
  <li>Movimentação de todas as peças seguindo as regras oficiais</li>
  <li>Sistema de captura de peças</li>
  <li>Cheque e cheque-mate</li>
  <li>Roque (castling) grande e pequeno</li>
  <li>Promoção de peão para rainha</li>
  <li>Validação de jogadas inválidas</li>
  <li>Interface colorida no terminal</li>
</ul>

<h2>🕹️ Controles</h2>
<ul>
  <li>Digite a posição de origem (ex: <code>a2</code>)</li>
  <li>Digite a posição de destino (ex: <code>a4</code>)</li>
  <li>O sistema valida automaticamente jogadas inválidas</li>
</ul>

<h2>📌 Exemplo de Jogo</h2>
<pre>
8 ♜  ♞  ♝  ♛  ♚  ♝  ♞  ♜ 
7 ♟  ♟  ♟  ♟  ♟  ♟  ♟  ♟ 
6 ·  ·  ·  ·  ·  ·  ·  · 
5 ·  ·  ·  ·  ·  ·  ·  · 
4 ·  ·  ·  ·  ·  ·  ·  · 
3 ·  ·  ·  ·  ·  ·  ·  · 
2 ♙  ♙  ♙  ♙  ♙  ♙  ♙  ♙ 
1 ♖  ♘  ♗  ♕  ♔  ♗  ♘  ♖ 
   a  b  c  d  e  f  g  h

Source: e2
Target: e4
</pre>

<h2>📜 Licença</h2>
<p>Este projeto está sob a licença MIT - veja o arquivo <a href="LICENSE">LICENSE</a> para detalhes.</p>

<hr>

<p align="center">
  Desenvolvido por <a href="https://github.com/gabrielhs33">Gabriel Henrique</a> como projeto acadêmico
</p>

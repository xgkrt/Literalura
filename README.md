<h1>📚 Challenge LiterAlura</h1>
<p>Desafio de desenvolvimento back-end para busca e persistência de livros utilizando a 
<a href="https://gutendex.com" target="_blank">API Gutendex</a>.</p> 
<P>Proposto pela Oracle Next Education, como parte da formação de Back-end Java em parceria com a Alura.</p>

<div style="text-align: center">
  <img src="https://github.com/user-attachments/assets/a33c6082-218f-48cd-8b36-f69d684dab37" alt="badge literalura">
</div>  

<h1>🔧 Sobre o projeto</h1>
<p>Este projeto é uma aplicação back-end focada na busca de livros. A aplicação permite a persistência desses dados em um banco de dados relacional e oferece funcionalidades de pesquisa para consultar os livros e autores já salvos.</p>
<p>A interação é feita por meio de um menu de opções em uma interface de linha de comando, proporcionando ao usuário uma forma simples de realizar diversas consultas.</p>

<h1>📋 Como funciona?</h1>
<p>Ao iniciar o sistema, o usuário verá um menu com várias opções de interação, que permitem realizar buscas personalizadas. O menu apresenta as opções de 1 a 6, sendo 0 para encerrar o programa. O usuário deve fornecer números inteiros para selecionar a opção desejada:</p>

<h4>Menu Inicial</h4>
<pre>
Escolha o número de sua opção:

1 - Buscar livro pelo título.
2 - Listar livros registrados.
3 - Listar autores registrados.
4 - Listar autores vivos em um determinado ano.
5 - Listar livros em um determinado idioma.
6 - Listar mais baixados (livros com mais downloads).

0 - sair.
</pre>

<p>Ao escolher a opção "1 - Buscar livro pelo título", o usuário deve informar o nome de um livro ou autor para pesquisa. O livro encontrado será exibido e salvo no banco de dados:</p>

<!-- Aqui você pode adicionar a imagem do livro salvo ou exemplo de consulta -->

<p>Após salvar os livros e autores, o usuário pode selecionar entre as opções 2 a 6, que permitem realizar consultas específicas:</p>

<ul>
  <li><strong>2 - Listar livros registrados</strong>: Exibe os livros que já foram salvos no banco de dados.</li>
  <li><strong>3 - Listar autores registrados</strong>: Exibe os autores que já foram salvos no banco de dados.</li>
  <li><strong>4 - Listar autores vivos em um determinado ano</strong>: Permite consultar quais autores estavam vivos no ano fornecido.</li>
  <li><strong>5 - Listar livros em um determinado idioma</strong>: Permite filtrar os livros registrados pelo idioma informado.</li>
  <li><strong>6 - Listar mais baixados</strong>: Exibe os livros mais baixados, com base no número de downloads registrados.</li>
</ul>

<h1>💻 Tecnologias e ferramentas utilizadas</h1>
<ul>
  <li>Java (JDK 17)</li>
  <li>Spring Boot e JPA Hibernate</li>
  <li>IntelliJ e Git</li>
  <li><a href="https://gutendex.com" target="_blank">API Gutendex</a></li>
  <li>PostgreSQL</li>
</ul>

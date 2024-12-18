package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.Autor;
import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.model.Results;
import br.com.alura.literalura.repository.AutorRepository;
import br.com.alura.literalura.repository.LivroRepository;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    List<Livro> livros;
    List<Autor> autores;

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    Escolha o número de sua opção:
                    
                    1 - Buscar livro por título.
                    2 - Listar livros registrados.
                    3 - Listar autores registrados.
                    4 - Listar autores vivos em um determinado ano.
                    5 - Listar livros em um determinado idioma.
                    6 - Livros mais baixados.
                    
                    0 - sair.
                    """;
            try {
                System.out.println(menu);
                opcao = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("INSIRA SOMENTE NÚMEROS!\n");
                sc.nextLine();
                continue;
            }

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarTodosLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> top10LivrosBaixados();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        }
    }


    private void buscarLivroPorTitulo(){
        System.out.println("Digite o nome do livro.");
        var nomeLivro = sc.nextLine();

        var livroPresenteBanco = livroRepository.findByTituloContainingIgnoreCase(nomeLivro);
        if (livroPresenteBanco.isPresent()){
            System.out.println("Livro está no banco de dados:");
            System.out.println(livroPresenteBanco.get());
        } else {
            DadosLivro dadosLivro = getDadosLivro(nomeLivro);
            if (dadosLivro != null){
                salvarlivro(dadosLivro);
                System.out.println("Livro salvo com sucesso.");
                System.out.println(dadosLivro);
            }
        }
    }

    private void salvarlivro(DadosLivro dadosLivro) {
        if (dadosLivro == null) return;

        Autor autor;
        String nomeAutor = dadosLivro.autor().get(0).nome();

        // Primeiro, tenta encontrar o autor no banco
        Optional<Autor> autorOptional = autorRepository.findByNome(nomeAutor);

        // Se o autor não existe, cria um novo
        if (autorOptional.isEmpty()) {
            autor = new Autor();
            autor.setNome(nomeAutor);
            autor.setAnoNascimento(dadosLivro.autor().get(0).anoNascimento());
            autor.setAnoFalecimento(dadosLivro.autor().get(0).anoFalecimento());

            // Use save() e retorne a entidade gerenciada
            autor = autorRepository.save(autor);
        } else {
            // Se o autor já existe, use o autor do banco de dados
            autor = autorOptional.get();
        }

        // Cria o livro
        Livro livro = new Livro();
        livro.setTitulo(dadosLivro.titulo());
        livro.setIdioma(dadosLivro.idioma().get(0));
        livro.setNumeroDownloads(dadosLivro.numeroDownloads());

        // Associa o autor ao livro
        livro.setAutor(autor);
        livroRepository.save(livro);
    }

    private DadosLivro getDadosLivro(String nomeLivro){
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+").trim());
        var dados = converte.obterDados(json, Results.class);
        if (dados.results().isEmpty()){
            System.out.println("Livro não encontrado.");
            return null;
        } else {
            return dados.results().get(0);
        }
    }

    private void listarTodosLivros() {
        livros = livroRepository.findAll();
        livros.stream()
                .sorted(Comparator.comparing(Livro::getTitulo))
                .forEach(System.out::println);
    }

    private void listarAutores() {
        autores = autorRepository.findAll();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNome))
                .forEach(System.out::println);
    }

    private void listarAutoresPorAno() {
        System.out.println("Digite o ano desejado para listar autores vivos: ");
        var ano = sc.nextInt();

        List<Autor> autoresVivos = autorRepository.obterAutorPorAno(ano);

        if (autoresVivos.isEmpty()){
            System.out.println("Nenhum autor encontrado.");
        } else {
            System.out.println("Autores vivos em " + ano + ":");
            autoresVivos.forEach(a -> System.out.println("\nNome: " + a.getNome() +
                    "\nAno de Nascimento: " + a.getAnoNascimento() +
                    "\nAno de Falecimento: " + (a.getAnoFalecimento() != null ? a.getAnoFalecimento() : "Ainda vivo")));
        }
    }

    private void listarLivrosPorIdioma() {
        System.out.println("""
                Digite a sigla de um dos idioma:
                en - Inglês
                pt - Português
                fr - Francês:
                """);
        var idiomaEscolhido = sc.nextLine().toLowerCase();

        List<Livro> livrosFiltrados = livroRepository.findByIdioma(idiomaEscolhido);

        if (livrosFiltrados.isEmpty()){
            System.out.println("Nenhum livro encontrado.");
            listarLivrosPorIdioma();
        } else {
            System.out.println("Livros no idioma " + idiomaEscolhido + ":");
            livrosFiltrados.forEach(System.out::println);
        }
    }
    private void top10LivrosBaixados() {
        List<Livro> topLivros = livroRepository.top10LivrosBaixados();
        topLivros.forEach(System.out::println);
    }

}

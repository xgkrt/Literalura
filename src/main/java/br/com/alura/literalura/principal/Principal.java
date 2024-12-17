package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Livro;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;

import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados converte = new ConverteDados();

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
                    
                    0 - sair.
                    """;
            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private void buscarLivroPorTitulo() {
        DadosLivro dados = getDadosLivro();
        Livro livro = new Livro(dados);
        System.out.println(livro);

    }

    private DadosLivro getDadosLivro(){
        System.out.println("Digite o nome do livro");
        var nomeLivro = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ", "+"));
        DadosLivro dados = converte.obterDados(json, DadosLivro.class);
        return dados;
    }
}

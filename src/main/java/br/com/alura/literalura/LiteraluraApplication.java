package br.com.alura.literalura;

import br.com.alura.literalura.model.DadosLivro;
import br.com.alura.literalura.model.Results;
import br.com.alura.literalura.principal.Principal;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Principal principal = new Principal();
//		principal.exibeMenu();
		ConsumoApi consumoApi = new ConsumoApi();
		ConverteDados converteDados = new ConverteDados();
		var json = consumoApi.obterDados("https://gutendex.com/books/?search=dickens+great");

		var dados = converteDados.obterDados(json, Results.class);
		DadosLivro dadosLivro = dados.results().get(0);
		System.out.println(dadosLivro);
	}
}

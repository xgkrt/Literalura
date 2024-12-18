package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors") List<DadosAutor> autor,
                         @JsonAlias("languages") List<String> idioma,
                         @JsonAlias("download_count") Integer numeroDownloads) {

    public List<String> getIdiomasComNome() {
        Map<String, String> idiomaMap = Map.of(
                "pt", "Portuguese",
                "en", "English",
                "es", "Spanish"
                // Adicione outros idiomas conforme necessário
        );

        return idioma.stream()
                .map(idiomaMap::get)
                .toList(); // Retorna a lista de nomes dos idiomas
    }
}

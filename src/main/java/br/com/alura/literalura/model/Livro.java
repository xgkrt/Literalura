package br.com.alura.literalura.model;

import java.util.List;

public class Livro {

    private String id;
    private String titulo;
    private String autor;
    private String idioma;
    private Integer numeroDownloads;


    public Livro(){}
    public Livro(DadosLivro dadosLivro) {
        this.titulo = dadosLivro.titulo();
        this.autor = pegaAutor(dadosLivro).getNome();
        this.idioma = idiomaMod(dadosLivro.idioma());
        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    private String idiomaMod(List<String> idiomas) {
        if (idiomas == null || idiomas.isEmpty()) {
            return "desconhecido";
        } else {
            return idiomas.get(0);
        }
    }
    public Autor pegaAutor(DadosLivro dadosLivro) {
        DadosAutor dadosAutor = dadosLivro.autor().get(0);
        return new Autor(dadosAutor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autor +
                ", idiomas=" + idioma +
                ", numeroDownloads=" + numeroDownloads +
                '}';
    }
}

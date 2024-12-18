package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.idioma = :idioma")
    List<Livro> findByIdioma(String idioma);

    Optional<Livro> findByTituloContainingIgnoreCase(String nomeLivro);

    @Query("SELECT l FROM Livro l ORDER BY l.numeroDownloads Desc")
    List<Livro> top10LivrosBaixados();
}

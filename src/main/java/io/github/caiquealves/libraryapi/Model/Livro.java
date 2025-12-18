package io.github.caiquealves.libraryapi.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
public class Livro {
    @Id
    @Column(name= "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name= "isbn", length = 20, nullable = false)
    private String isbn;

    @Column(name= "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name= "data_publicacao")
    private String dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name= "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name= "preco", precision = 18, scale = 2)
    private double preco;

    //Relacionamento com autor
    @ManyToOne //primeiro refere-se a classe atual(Livro) a segunda refere-se a outra classe(Autor)
    @JoinColumn(name= "id_autor")
    private Autor autor;
}

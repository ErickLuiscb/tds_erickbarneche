package br.edu.ifsul.cstsi.tds_erickbarneche.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    // Associação

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private Collection<Sugestao> sugestoes;
}
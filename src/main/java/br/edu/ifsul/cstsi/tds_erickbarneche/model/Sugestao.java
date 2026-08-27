package br.edu.ifsul.cstsi.tds_erickbarneche.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "sugestoes")
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String tipo;
    private String capa;

    // Associações

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Categoria> categorias;
}
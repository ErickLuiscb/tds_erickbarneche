package br.edu.ifsul.cstsi.tds_erickbarneche.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "anotacoes")
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String texto;

    // Associação

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
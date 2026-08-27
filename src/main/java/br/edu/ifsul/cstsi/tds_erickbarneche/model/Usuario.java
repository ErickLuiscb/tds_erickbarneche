package br.edu.ifsul.cstsi.tds_erickbarneche.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String imagemPerfil;
    private String crp;
    private String tipo;
    private boolean isAdmin;

    // Associações

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Anotacao> anotacoes;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Sugestao> sugestoes;
}
package br.edu.ifsul.cstsi.tds_erickbarneche.model.Categoria;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao.Sugestao;
import jakarta.persistence.*;

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

@ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private Collection<Sugestao> sugestoes;


    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
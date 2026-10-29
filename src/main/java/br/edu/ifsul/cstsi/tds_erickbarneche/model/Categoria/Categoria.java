package br.edu.ifsul.cstsi.tds_erickbarneche.model.Categoria;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao.Sugestao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "categorias")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.EAGER)
    private Collection<Sugestao> sugestoes;
}
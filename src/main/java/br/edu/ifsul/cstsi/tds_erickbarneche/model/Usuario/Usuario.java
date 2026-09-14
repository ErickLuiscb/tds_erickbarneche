package br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao.Anotacao;
import br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao.Sugestao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
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

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Anotacao> anotacoes;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Sugestao> sugestoes;
}
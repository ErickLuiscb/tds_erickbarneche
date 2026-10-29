package br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Categoria.Categoria;
import br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity
@Table(name = "sugestoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Sugestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres")
    private String titulo;

    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição deve ter no máximo 255 caracteres")
    private String descricao;

    @NotBlank(message = "O tipo é obrigatório")
    private String tipo;

    private String capa;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Categoria> categorias;
}
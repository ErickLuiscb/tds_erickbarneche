package br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "anotacoes")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Anotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
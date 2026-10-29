package br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 255, message = "O título deve ter no máximo 255 caracteres")
    private String titulo;

    @NotBlank(message = "O texto é obrigatório")
    @Size(max = 255, message = "O texto deve ter no máximo 255 caracteres")
    private String texto;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
}
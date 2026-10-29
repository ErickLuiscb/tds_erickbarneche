package br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao.Anotacao;
import br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao.Sugestao;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true) // não permite dois usuários com o mesmo email
    private String email;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    private String imagemPerfil;
    private String crp;
    private String tipo;
    private boolean isAdmin;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Anotacao> anotacoes;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Collection<Sugestao> sugestoes;

    // ---- Métodos exigidos pela interface UserDetails ----

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.isAdmin) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
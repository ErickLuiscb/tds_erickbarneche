package br.edu.ifsul.cstsi.tds_erickbarneche.model.autenticacao;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface AutenticacaoRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
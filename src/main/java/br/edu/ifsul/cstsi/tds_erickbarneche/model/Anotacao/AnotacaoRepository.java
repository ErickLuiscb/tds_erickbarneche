package br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long> {
}
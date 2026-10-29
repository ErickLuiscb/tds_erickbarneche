package br.edu.ifsul.cstsi.tds_erickbarneche.model.infra.jwt;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.autenticacao.AutenticacaoRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final AutenticacaoRepository repository;

    public CustomJwtAuthenticationConverter(AutenticacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var usuario = repository.findByEmail(jwt.getSubject())
                .orElseThrow(() -> new BadCredentialsException("Usuário do token JWT não encontrado"));
        return new UsernamePasswordAuthenticationToken(usuario, jwt, usuario.getAuthorities());
    }
}
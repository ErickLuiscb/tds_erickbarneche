package br.edu.ifsul.cstsi.tds_erickbarneche.model.autenticacao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    private final AutenticacaoRepository autenticacaoRepository;

    public AutenticacaoService(AutenticacaoRepository autenticacaoRepository) {
        this.autenticacaoRepository = autenticacaoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return autenticacaoRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
    }
}
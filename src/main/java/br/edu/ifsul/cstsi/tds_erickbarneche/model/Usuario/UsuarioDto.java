package br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario;

import java.io.Serializable;

public record UsuarioDto(
        Long id,
        String nome,
        String email,
        String imagemPerfil,
        String crp,
        String tipo,
        boolean isAdmin) implements Serializable {
    public UsuarioDto(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getImagemPerfil(),
                usuario.getCrp(), usuario.getTipo(), usuario.isAdmin());
    }
}
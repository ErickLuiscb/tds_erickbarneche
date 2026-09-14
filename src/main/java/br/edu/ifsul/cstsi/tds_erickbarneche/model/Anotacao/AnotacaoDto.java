package br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao;

import java.io.Serializable;

public record AnotacaoDto(
        Long id,
        String titulo,
        String texto,
        Long usuarioId) implements Serializable {
    public AnotacaoDto(Anotacao anotacao) {
        this(anotacao.getId(), anotacao.getTitulo(), anotacao.getTexto(), anotacao.getUsuario().getId());
    }
}
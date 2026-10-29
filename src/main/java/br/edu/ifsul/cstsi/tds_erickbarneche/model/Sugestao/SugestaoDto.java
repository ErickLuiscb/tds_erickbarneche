package br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao;

import br.edu.ifsul.cstsi.tds_erickbarneche.model.Categoria.Categoria;

import java.io.Serializable;
import java.util.List;

public record SugestaoDto(
        Long id,
        String titulo,
        String descricao,
        String tipo,
        String capa,
        Long usuarioId,
        List<Long> categoriaIds) implements Serializable {
    public SugestaoDto(Sugestao sugestao) {
        this(sugestao.getId(), sugestao.getTitulo(), sugestao.getDescricao(), sugestao.getTipo(), sugestao.getCapa(),
                sugestao.getUsuario() != null ? sugestao.getUsuario().getId() : null,
                sugestao.getCategorias() != null
                        ? sugestao.getCategorias().stream().map(Categoria::getId).toList()
                        : List.of());
    }
}
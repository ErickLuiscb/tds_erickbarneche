package br.edu.ifsul.cstsi.tds_erickbarneche.model.Sugestao;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.util.List;

@RestController
@RequestMapping("api/v1/sugestoes")
public class SugestaoController {

    private final SugestaoRepository sugestaoRepository;

    public SugestaoController(SugestaoRepository sugestaoRepository) {
        this.sugestaoRepository = sugestaoRepository;
    }

    @GetMapping
    public ResponseEntity<List<SugestaoDto>> findAll() {
        return ResponseEntity.ok(
                sugestaoRepository.findAll().stream()
                        .map(SugestaoDto::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SugestaoDto> findById(@PathVariable(value = "id") Long id) {
        var sugestao = sugestaoRepository.findById(id);
        if (sugestao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sugestao.map(SugestaoDto::new).get());
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> insert(@Valid @RequestBody Sugestao sugestao, UriComponentsBuilder uriBuilder) {
        var s = sugestaoRepository.save(sugestao);
        var location = uriBuilder.path("api/v1/sugestoes/{id}").buildAndExpand(s.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<SugestaoDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Sugestao sugestao) {
        var sugestaoOptional = sugestaoRepository.findById(id);

        if (sugestaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var sugestaoBanco = sugestaoOptional.get();

        sugestaoBanco.setTitulo(sugestao.getTitulo());
        sugestaoBanco.setDescricao(sugestao.getDescricao());
        sugestaoBanco.setTipo(sugestao.getTipo());
        sugestaoBanco.setCapa(sugestao.getCapa());
        sugestaoBanco.setUsuario(sugestao.getUsuario());
        sugestaoBanco.setCategorias(sugestao.getCategorias());

        var sugestaoAtualizada = sugestaoRepository.save(sugestaoBanco);

        return ResponseEntity.ok(new SugestaoDto(sugestaoAtualizada));
    }

    @DeleteMapping("/{id}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        var sugestaoOptional = sugestaoRepository.findById(id);

        if (sugestaoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        sugestaoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
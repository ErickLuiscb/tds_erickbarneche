package br.edu.ifsul.cstsi.tds_erickbarneche.model.Anotacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/anotacoes")
public class AnotacaoController {

    private final AnotacaoRepository anotacaoRepository;

    public AnotacaoController(AnotacaoRepository anotacaoRepository) {
        this.anotacaoRepository = anotacaoRepository;
    }

    @GetMapping
    public ResponseEntity<List<AnotacaoDto>> findAll() {
        return ResponseEntity.ok(
                anotacaoRepository.findAll().stream()
                        .map(AnotacaoDto::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnotacaoDto> findById(@PathVariable(value = "id") Long id) {
        var anotacao = anotacaoRepository.findById(id);
        if (anotacao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(anotacao.map(AnotacaoDto::new).get());
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Anotacao anotacao, UriComponentsBuilder uriBuilder) {
        var a = anotacaoRepository.save(anotacao);
        var location = uriBuilder.path("api/v1/anotacoes/{id}").buildAndExpand(a.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public String update(@PathVariable(value = "id") Long id, @RequestBody Anotacao anotacao) {
        return "update " + anotacao;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return "delete " + id;
    }
}
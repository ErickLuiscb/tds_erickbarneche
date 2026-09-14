package br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> findAll() {
        return ResponseEntity.ok(
                usuarioRepository.findAll().stream()
                        .map(UsuarioDto::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable(value = "id") Long id) {
        var usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario.map(UsuarioDto::new).get());
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
        var u = usuarioRepository.save(usuario);
        var location = uriBuilder.path("api/v1/usuarios/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public String update(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
        return "update " + usuario;
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        return "delete " + id;
    }
}
package br.edu.ifsul.cstsi.tds_erickbarneche.model.Usuario;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured("ROLE_ADMIN")
    public ResponseEntity<String> insert(@Valid @RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
        var u = usuarioRepository.save(usuario);
        var location = uriBuilder.path("api/v1/usuarios/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> update(@PathVariable(value = "id") Long id, @Valid @RequestBody Usuario usuario) {
        var usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var usuarioBanco = usuarioOptional.get();

        usuarioBanco.setNome(usuario.getNome());
        usuarioBanco.setEmail(usuario.getEmail());
        usuarioBanco.setSenha(usuario.getSenha());
        usuarioBanco.setImagemPerfil(usuario.getImagemPerfil());
        usuarioBanco.setCrp(usuario.getCrp());
        usuarioBanco.setTipo(usuario.getTipo());
        usuarioBanco.setAdmin(usuario.isAdmin());

        var usuarioAtualizado = usuarioRepository.save(usuarioBanco);

        return ResponseEntity.ok(new UsuarioDto(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
        var usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
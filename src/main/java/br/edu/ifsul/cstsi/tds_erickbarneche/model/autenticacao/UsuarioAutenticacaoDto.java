package br.edu.ifsul.cstsi.tds_erickbarneche.model.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioAutenticacaoDto(
        @NotBlank(message = "O email não pode ser nulo ou vazio")
        @Email(message = "O email deve ter @ e . , no mínimo.")
        String email,

        @NotBlank(message = "A senha não pode ser nula ou vazia")
        @Pattern(regexp = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[$*&@#])[0-9a-zA-Z$*&@#]{8,}$",
                message = "A senha deve conter ao menos uma letra maiúscula, uma letra minúscula, um número, um caractere especial e um total de 8 caracteres.")
        String senha) {
}
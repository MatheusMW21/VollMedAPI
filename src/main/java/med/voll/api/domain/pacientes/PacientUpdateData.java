package med.voll.api.domain.pacientes;

import jakarta.validation.Valid;
import med.voll.api.domain.endereco.AdressData;

public record PacientUpdateData(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        @Valid AdressData endereco
) {
}
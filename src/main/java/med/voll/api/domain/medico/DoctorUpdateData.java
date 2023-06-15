package med.voll.api.domain.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.AdressData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AdressData endereco
) {

}

package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.AdressData;

public record DoctorUpdateData(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AdressData endereco
) {

}

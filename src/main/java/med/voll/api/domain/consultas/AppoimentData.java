package med.voll.api.domain.consultas;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record AppoimentData(
        Long doctorId,
        @NotNull
        Long pacientId,

        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade) {

}

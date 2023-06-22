package med.voll.api.domain.consultas;

import jakarta.validation.constraints.NotNull;

public record CancelAppoimentData(
        @NotNull
        Long appoimentId,
        @NotNull
        CancellationReason reason) {
}

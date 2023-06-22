package med.voll.api.domain.consultas;

import java.time.LocalDateTime;

public record DetailAppoimentData(Long id, Long doctorId, Long pacientId, LocalDateTime data) {
}

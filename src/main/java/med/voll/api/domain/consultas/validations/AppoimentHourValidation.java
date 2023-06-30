package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppoimentHourValidation implements ValidationInterface {

    public void validation(AppoimentData data) {
        var appoimentData = data.data();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, appoimentData).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidacaoException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }
    }
}

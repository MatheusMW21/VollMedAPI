package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkHourValidation implements ValidationInterface {

    public void validation(AppoimentData data) {
        var appoimentData = data.data();

        var sunday = appoimentData.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeSeven = appoimentData.getHour() < 7;
        var afterSeven = appoimentData.getHour() > 18;

        if (sunday || beforeSeven || afterSeven) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}

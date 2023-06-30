package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.consultas.AppoimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientWithoutAppoimentValidation implements ValidationInterface {
    @Autowired
    private AppoimentRepository repository;

    public void validation (AppoimentData data) {
        var firstAppoiment = data.data().withHour(7);
        var lastAppoiment = data.data().withHour(18);
        var pacientWithoutAppoiment = repository.existsByPacientIdAndDataBetween(data.pacientId(), firstAppoiment, lastAppoiment);
        if(pacientWithoutAppoiment){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}

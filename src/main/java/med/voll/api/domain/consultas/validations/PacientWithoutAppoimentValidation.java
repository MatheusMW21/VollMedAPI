package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.consultas.AppoimentRepository;

public class PacientWithoutAppoimentValidation {
    private AppoimentRepository repository;

    public void validar (AppoimentData data) {
        var firstAppoiment = data.data().withHour(7);
        var lastAppoiment = data.data().withHour(18);
        var pacientWithoutAppoiment = repository.existsByPacientIdAndDataBetween(data.pacientId(), firstAppoiment, lastAppoiment);
        if(pacientWithoutAppoiment){
            throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
        }
    }
}

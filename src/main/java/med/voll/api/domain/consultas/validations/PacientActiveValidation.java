package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.pacientes.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacientActiveValidation implements ValidationInterface {
    @Autowired
    private PacientRepository repository;
    public void validation(AppoimentData data) {
        var pacientIsActive = repository.findActiveById(data.pacientId());

        if (!pacientIsActive){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");

        }
    }
}

package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.pacientes.PacientRepository;

public class PacientActiveValidation {
    private PacientRepository repository;
    public void validation(AppoimentData data) {
        var pacientIsActive = repository.findActiveById(data.pacientId());

        if (!pacientIsActive){
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");

        }
    }
}

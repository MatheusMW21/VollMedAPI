package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.medico.DoctorRepository;

public class DoctorActiveValidation {
    private DoctorRepository repository;
    public void validation(AppoimentData data) {
        if(data.doctorId() == null) {
            return;
        }

        var doctorIsActive = repository.findAtivoById(data.doctorId());
        if(!doctorIsActive) {
        throw new ValidacaoException("Consulta não pode ser acessada com um médico");
        }
    }
}

package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.consultas.AppoimentRepository;

public class DoctorUnvailableValidation {
    private AppoimentRepository repository;

    public void validation(AppoimentData data) {
        var doctorUnvailable = repository.existsByDoctorIdAndData(data.doctorId(), data.data());
        if (doctorUnvailable) {
            throw new ValidacaoException("O Médico está indisponível!");
        }
    }
}

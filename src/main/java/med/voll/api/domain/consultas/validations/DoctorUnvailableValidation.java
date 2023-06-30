package med.voll.api.domain.consultas.validations;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.consultas.AppoimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorUnvailableValidation implements ValidationInterface {
    @Autowired
    private AppoimentRepository repository;

    public void validation(AppoimentData data) {
        var doctorUnvailable = repository.existsByDoctorIdAndData(data.doctorId(), data.data());
        if (doctorUnvailable) {
            throw new ValidacaoException("O Médico está indisponível!");
        }
    }
}

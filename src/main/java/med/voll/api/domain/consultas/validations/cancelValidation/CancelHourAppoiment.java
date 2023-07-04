package med.voll.api.domain.consultas.validations.cancelValidation;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consultas.AppoimentRepository;
import med.voll.api.domain.consultas.CancelAppoimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class CancelHourAppoiment implements CancelValidations {

    @Autowired
    private AppoimentRepository repository;

    @Override
    public void validation (CancelAppoimentData data){
        var appoiment = repository.getReferenceById(data.appoimentId());
        var now = LocalDateTime.now();
        var differenceInHours = Duration.between(now, appoiment.getData()).toHours();

        if (differenceInHours < 24){
            throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}

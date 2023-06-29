package med.voll.api.domain.consultas;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.medico.Doctor;
import med.voll.api.domain.medico.DoctorRepository;
import med.voll.api.domain.pacientes.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppoimentSchedule {
    @Autowired
    private AppoimentRepository appoimentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PacientRepository pacientRepository;
    public void appoiment(AppoimentData data) {
        if(!pacientRepository.existsById(data.pacientId())){
            throw new ValidacaoException("Id do paciente não existe.");
        }

        if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
            throw new ValidacaoException("Id do médico não existe.");
        }

        var paciente = pacientRepository.getReferenceById(data.pacientId());
        var medico = chooseDoctor(data);
        var appoiment = new Appoiment(null, medico, paciente, data.data(), null);
        appoimentRepository.save(appoiment);
    }

    private Doctor chooseDoctor(AppoimentData data) {
        if(data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if(data.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
        }

        return doctorRepository.chooseRandomDoctor(data.especialidade(), data.data());
    }

    public void cancel (CancelAppoimentData data){
        if(!appoimentRepository.existsById(data.appoimentId())){
            throw new ValidacaoException("Id da consulta inválido!");
        }

        var appoiment = appoimentRepository.getReferenceById(data.appoimentId());
        appoiment.cancel(data.reason());
    }
}

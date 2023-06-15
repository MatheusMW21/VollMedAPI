package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DetailsDoctorData(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, Endereco endereco) {

    public DetailsDoctorData(Doctor doctor){
        this(doctor.getId(), doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getTelefone(), doctor.getEspecialidade(), doctor.getEndereco());
    }
}

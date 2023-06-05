package med.voll.api.pacientes;

import med.voll.api.endereco.AdressData;

public record PatientRegistrationData(
        String nome,
        String email,
        String telefone,
        String cpf,
        AdressData endereco
) {

}

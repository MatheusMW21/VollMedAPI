package med.voll.api.medico;

import med.voll.api.endereco.AdressData;

public record MedicalRegistrationData(String nome, String email, String crm, Specialty specialty, AdressData endereco) {

}

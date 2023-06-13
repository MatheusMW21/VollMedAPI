package med.voll.api.pacientes;

import med.voll.api.endereco.Endereco;

public record PacientDetailData(String nome, String email, String cpf, String telefone, Endereco endereco ) {
    public PacientDetailData(Pacient pacient) {
        this(pacient.getNome(), pacient.getEmail(), pacient.getTelefone(), pacient.getCpf(), pacient.getEndereco());
    }
}

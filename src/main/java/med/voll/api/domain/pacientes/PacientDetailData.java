package med.voll.api.domain.pacientes;

import med.voll.api.domain.endereco.Endereco;

public record PacientDetailData(String nome, String email, String cpf, String telefone, Endereco endereco ) {
    public PacientDetailData(Pacient pacient) {
        this(pacient.getNome(), pacient.getEmail(), pacient.getTelefone(), pacient.getCpf(), pacient.getEndereco());
    }
}

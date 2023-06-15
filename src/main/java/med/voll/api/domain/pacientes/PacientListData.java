package med.voll.api.domain.pacientes;

public record PacientListData(Long id, String nome, String email, String cpf) {
    public PacientListData(Pacient pacient) {
        this(pacient.getId(), pacient.getNome(), pacient.getEmail(), pacient.getCpf());
    }
}
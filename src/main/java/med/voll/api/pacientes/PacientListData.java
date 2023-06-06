package med.voll.api.pacientes;

public record PacientListData(String nome, String email, String cpf) {
    public PacientListData(Pacient pacient) {
        this(pacient.getNome(), pacient.getEmail(), pacient.getCpf());
    }
}
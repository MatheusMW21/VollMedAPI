package med.voll.api.medico;

public record DoctorListData(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DoctorListData(Doctor doctor) {
        this(doctor.getId(),doctor.getNome(), doctor.getEmail(), doctor.getCrm(), doctor.getEspecialidade());
    }

}

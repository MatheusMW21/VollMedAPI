package med.voll.api.pacientes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pacient {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String cpf;
    private String telefone;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Pacient(PatientRegistrationData data){
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.cpf = data.cpf();
        this.telefone = data.telefone();
        this.endereco = new Endereco(data.endereco());
    }

    public void updateInfo(PacientUpdateData data) {
        if (data.nome() != null)
            this.nome = data.nome();

        if (data.telefone() != null)
            this.telefone = data.telefone();

        if (data.endereco() != null)
            endereco.uptadeInfo(data.endereco());
    }

    public void inativar() {
        this.ativo = false;
    }

}

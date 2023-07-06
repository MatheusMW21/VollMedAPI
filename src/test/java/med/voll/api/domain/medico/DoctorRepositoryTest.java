package med.voll.api.domain.medico;

import med.voll.api.domain.consultas.Appoiment;
import med.voll.api.domain.endereco.AdressData;
import med.voll.api.domain.pacientes.Pacient;
import med.voll.api.domain.pacientes.PacientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Cenário onde deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
    void chooseRandomDoctor1() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);
        var pacient = registerPacient("Paciente", "paciente@gmail.com", "12345678910");
        registerAppoiment(doctor, pacient, nextMondayAt10);

        var freeDoctor = doctorRepository.chooseRandomDoctor(Especialidade.CARDIOLOGIA, nextMondayAt10);
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("Cenário onde deveria devolver medico quando ele estiver disponivel na data")
    void chooseRandomDoctor2() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = registerDoctor("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

        var freeDoctor = doctorRepository.chooseRandomDoctor(Especialidade.CARDIOLOGIA, nextMondayAt10);
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private void registerAppoiment(Doctor doctor, Pacient pacient, LocalDateTime data){
        em.persist(new Appoiment(null, doctor, pacient, data, null));
    }

    private Doctor registerDoctor(String nome, String email, String crm, Especialidade especialidade) {
        var doctor = new Doctor(doctorData(nome, email, crm, especialidade));
        em.persist(doctor);
        return doctor;
    }

    private Pacient registerPacient(String nome, String email,String cpf) {
        var pacient = new Pacient(pacientData(nome, email, cpf));
        em.persist(pacient);
        return pacient;
    }

    private MedicalRegistrationData doctorData(String nome, String email, String crm, Especialidade especialidade) {
        return new MedicalRegistrationData(
                nome,
                email,
                "11999999999",
                crm,
                especialidade,
                adressData()
        );
    }

    private PacientRegistrationData pacientData(String nome, String email, String cpf) {
        return new PacientRegistrationData(
                nome,
                email,
                "11999999999",
                cpf,
                adressData()
        );
    }

    private AdressData adressData(){
        return new AdressData(
          "rua xpto",
          "bairro",
          "00000000",
          "São Paulo",
          "SP",
          null,
          null
        );
    }
}
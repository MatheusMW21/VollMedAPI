package med.voll.api.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppoimentRepository extends JpaRepository<Appoiment, Long> {
    boolean existsByPacientAndDataBetween(Long pacientId, LocalDateTime firstAppoiment, LocalDateTime lastAppoiment );
    boolean existsByDoctorIdAndData(Long doctorId, LocalDateTime data);
}

package med.voll.api.domain.consultas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppoimentRepository extends JpaRepository<Appoiment, Long> {
    @Query("select case when count(a)> 0 then true else false end from Appoiment as a where a.pacient.id =?1 and a.data between ?2 and ?3")
    boolean existsByPacientAndDataBetween(Long pacientId, LocalDateTime firstAppoiment, LocalDateTime lastAppoiment );
    boolean existsByDoctorIdAndData(Long doctorId, LocalDateTime data);
}

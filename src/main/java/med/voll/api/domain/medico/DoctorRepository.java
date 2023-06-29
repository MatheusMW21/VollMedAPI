package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByAtivoTrue (Pageable page);

    @Query("""
                select m from Doctor m
                where
                m.ativo =true
                and
                m.especialidade = :especialidade
                and
                m.id not in(
                        select c.doctor.id from Appoiment c
                        where
                        c.data = :data
                )
                order by rand()
                limit 1
                """)
    Doctor chooseRandomDoctor(Especialidade especialidade, LocalDateTime data);
    @Query("""
            select m.ativo
            from Doctor m 
            where 
            m.id = :idDoctor
            """)
    Boolean findAtivoById(Long idDoctor);
}

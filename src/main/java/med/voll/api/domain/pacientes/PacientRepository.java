package med.voll.api.domain.pacientes;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<Pacient, Long> {
    Page<Pacient> findAllByAtivoTrue(Pageable page);

    @Query("""
            select
            p.ativo
            from Pacient p 
            where
            p.id = :id
            """)

    boolean findActiveById(
            Long id
    );
}

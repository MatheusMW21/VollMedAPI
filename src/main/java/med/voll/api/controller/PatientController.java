package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class PatientController  {
    @Autowired
    private PacientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientRegistrationData  data){
        repository.save(new Pacient(data));
    }

    @GetMapping
    public Page<PacientListData> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        return repository.findAllByAtivoTrue(page).map(PacientListData::new);
    }

    @PutMapping
    @Transactional
    public void update (@RequestBody @Valid PacientUpdateData data){
        var pacient = repository.getReferenceById(data.id());
        pacient.updateInfo(data);
    }

    @DeleteMapping
    @Transactional
    public void delete(@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);
        pacient.inativar();
    }
}

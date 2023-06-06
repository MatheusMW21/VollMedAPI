package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.pacientes.Pacient;
import med.voll.api.pacientes.PacientListData;
import med.voll.api.pacientes.PacientRepository;
import med.voll.api.pacientes.PatientRegistrationData;
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
        return repository.findAll(page).map(PacientListData::new);

    }
}

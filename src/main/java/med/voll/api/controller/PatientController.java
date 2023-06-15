package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.pacientes.*;
import med.voll.api.domain.pacientes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PatientController  {
    @Autowired
    private PacientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegistrationData data, UriComponentsBuilder uriBuilder){
        var pacient = new Pacient(data);
        repository.save(pacient);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(pacient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PacientDetailData(pacient));
    }

    @GetMapping
    public ResponseEntity <Page<PacientListData>> list(@PageableDefault(size = 10, sort = {"nome"}) Pageable page){
        var paginacao = repository.findAllByAtivoTrue(page).map(PacientListData::new);

        return ResponseEntity.ok(paginacao);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update (@RequestBody @Valid PacientUpdateData data){
        var pacient = repository.getReferenceById(data.id());
        pacient.updateInfo(data);

        return ResponseEntity.ok(new PacientDetailData(pacient));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var pacient = repository.getReferenceById(id);
        pacient.inativar();

        return ResponseEntity.noContent().build(); // HTTP code 204
    }

    @GetMapping("/{id}") // Provide a unique path for this endpoint
    public ResponseEntity details(@PathVariable ("id") Long id) {
        var pacient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PacientDetailData(pacient)); // HTTP code 201
    }
}

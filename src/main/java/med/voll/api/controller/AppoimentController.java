package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.Appoiment;
import med.voll.api.domain.consultas.AppoimentData;
import med.voll.api.domain.consultas.AppoimentSchedule;
import med.voll.api.domain.consultas.DetailAppoimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class AppoimentController {
    @Autowired
    private AppoimentSchedule appoimentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity schedule(@RequestBody @Valid AppoimentData data) {
        appoimentSchedule.appoiment(data);
        return ResponseEntity.ok(new DetailAppoimentData(null, null, null, null));
    }
}

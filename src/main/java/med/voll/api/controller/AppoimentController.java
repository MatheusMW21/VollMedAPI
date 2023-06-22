package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consultas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid CancelAppoimentData data) {
        appoimentSchedule.cancel(data);
        return ResponseEntity.noContent().build();
    }
}

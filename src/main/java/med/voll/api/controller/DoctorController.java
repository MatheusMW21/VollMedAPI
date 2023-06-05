package med.voll.api.controller;

import med.voll.api.medico.MedicalRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class DoctorController {

    @PostMapping
    public void register(@RequestBody MedicalRegistrationData data) {
        System.out.println(data);
    }

}

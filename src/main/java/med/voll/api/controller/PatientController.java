package med.voll.api.controller;

import med.voll.api.pacientes.PatientRegistrationData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PatientController  {

    @PostMapping
    public void register(@RequestBody PatientRegistrationData  data){
        System.out.println("Dados recebidos: " + data);
    }
}

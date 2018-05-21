package ua.phones.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.phones.db.models.Smartphone;
import ua.phones.db.services.interfaces.PhonesTableService;

import java.util.List;

@RestController
public class PhonesRest {

    private PhonesTableService phonesTableService;

    @Autowired
    private void setPhonesTableService(PhonesTableService phonesTableService) {
        this.phonesTableService = phonesTableService;
    }

    @RequestMapping(value = "/phones", method = RequestMethod.GET)
    public ResponseEntity<List<Smartphone>> listAllSmartphones() {
        List<Smartphone> phones = phonesTableService.getAllSmartphones();
        if(phones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Smartphone>>(phones, HttpStatus.OK);
    }
}

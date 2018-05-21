package ua.phones.db.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/phone", method = RequestMethod.GET)
    public ResponseEntity<List<Smartphone>> listAllSmartphones() {
        List<Smartphone> phones = phonesTableService.getAllSmartphones();
        if(phones.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Smartphone>>(phones, HttpStatus.OK);
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePhone(@PathVariable(required = false) int id) {
        if (id != 0) {
            System.out.println("deleted " + id);
            phonesTableService.deleteSmartPhone(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/phone", method = RequestMethod.POST)
    public ResponseEntity<Void> createSmartphon(@RequestBody Smartphone smartphone) {
        if (smartphone != null) {
            phonesTableService.addSmartPhone(smartphone);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateSmartphone(@RequestBody Smartphone smartphone, @PathVariable int id) {
        if (smartphone != null) {
            phonesTableService.updateSmartPhone(smartphone);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

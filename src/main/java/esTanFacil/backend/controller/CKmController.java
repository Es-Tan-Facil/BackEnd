package esTanFacil.backend.controller;

import esTanFacil.backend.model.CKm;
import esTanFacil.backend.service.CServiceKm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public class CKmController {
    @Autowired
    private CServiceKm cServiceKm;


    @GetMapping("")
    public List<CKm> readKm(){
        return cServiceKm.readKm();

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<CKm> readKmId(@PathVariable("id") Long id){
        return cServiceKm.readKmId(id);

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void createKm(@RequestBody CKm kms){
        cServiceKm.createKm(kms);

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateKm(@RequestBody CKm kms,@PathVariable("id")Long id){
        cServiceKm.updateKm(kms,id);

    }



}

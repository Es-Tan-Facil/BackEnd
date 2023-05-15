package esTanFacil.backend.controller;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.service.CServiceDonation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/donations")
public class CDonationsController {

    @Autowired
    private CServiceDonation cServiceDonation;


    @GetMapping("")
    public List<CDonations> readDonation(){
        return cServiceDonation.readDonation();

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<CDonations> readDonationId(@PathVariable("id") Long id){
        return cServiceDonation.readDonationId(id);

    }

    @PostMapping("")
    @PreAuthorize("hasRole('ADMIN')")
    public void createDonation(@RequestBody CDonations donations){
        cServiceDonation.createDonation(donations);
        cServiceDonation.updateTotalKmDonated();

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updateDonation(@RequestBody CDonations donations,@PathVariable("id")Long id){
        cServiceDonation.updateDonation(donations,id);
        cServiceDonation.updateTotalKmDonated();

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDonation(@PathVariable("id")Long id){
        cServiceDonation.deleteDonation(id);
        cServiceDonation.updateTotalKmDonated();


    }

    @GetMapping("/total-km-donated")
    public int getTotalKmDonated() {
        return cServiceDonation.sumKmDonated();
    }






}

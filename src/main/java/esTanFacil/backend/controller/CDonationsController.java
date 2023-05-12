package esTanFacil.backend.controller;


import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.model.CKm;
import esTanFacil.backend.service.CServiceDonation;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/donations")
public class CDonationsController {

    @Autowired
    private CServiceDonation donationService;


    @GetMapping("")
    public List<CDonations> getAllDonations() {
        return donationService.getAllDonations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CDonations> getDonationById(@PathVariable(value = "id") Long id) {
        Optional<CDonations> donation = donationService.getDonationById(id);
        if (donation.isPresent()) {
            return new ResponseEntity<>(donation.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<CDonations> createDonation(@RequestBody CDonations donation) {
        CDonations createdDonation = donationService.createDonation(donation);
        return new ResponseEntity<>(createdDonation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CDonations> updateDonation(@PathVariable(value = "id") Long id, @RequestBody CDonations donation) {
        CDonations updatedDonation = donationService.updateDonation(id, donation);
        if (updatedDonation != null) {
            return new ResponseEntity<>(updatedDonation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonation(@PathVariable(value = "id") Long id) {
        try {
            donationService.deleteDonation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/total-km-donated")
    public int getTotalKmDonated() {
        return donationService.getTotalKmDonated();
    }

    @PutMapping("/updateTotalKm")
    public ResponseEntity<CKm> updateTotalKm() {
        CKm updatedKm = donationService.updateTotalKm();
        return new ResponseEntity<>(updatedKm, HttpStatus.OK);
    }

}

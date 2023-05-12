package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.model.CKm;
import esTanFacil.backend.repositories.IDonations;
import esTanFacil.backend.repositories.IKm;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CServiceDonation {

    @Autowired
    private IDonations donationsRepository;
    @Autowired
    private IKm kmRepository;

    public List<CDonations> getAllDonations() {
        return donationsRepository.findAll();
    }

    public Optional<CDonations> getDonationById(Long id) {
        return donationsRepository.findById(id);
    }

    public CDonations createDonation(CDonations donation) {
        return donationsRepository.save(donation);
    }

    public CDonations updateDonation(Long id, CDonations donation) {
        Optional<CDonations> existingDonation = donationsRepository.findById(id);
        if (existingDonation.isPresent()) {
            CDonations updatedDonation = existingDonation.get();
            updatedDonation.setName(donation.getName());
            updatedDonation.setKmDonated(donation.getKmDonated());
            return donationsRepository.save(updatedDonation);
        } else {
            return null;
        }
    }

    public void deleteDonation(Long id) {
        Optional<CDonations> donation = donationsRepository.findById(id);
        if (donation.isPresent()) {
            donationsRepository.delete(donation.get());
        } else {
            throw new EntityNotFoundException("Donation not found with id " + id);
        }
    }

    public int getTotalKmDonated() {
        List<CDonations> allDonations = donationsRepository.findAll();
        int totalKmDonated = 0;
        for (CDonations donation : allDonations) {
            totalKmDonated += donation.getKmDonated();
        }
        return totalKmDonated;
    }


    public CKm updateTotalKm() {
        int totalKm = getTotalKmDonated();
        Optional<CKm> existingKm = kmRepository.findById(1L);
        if (existingKm.isPresent()) {
            CKm updatedKm = existingKm.get();
            updatedKm.setTotalKm(updatedKm.getTotalKm() + totalKm);
            CKm savedKm = kmRepository.save(updatedKm);
            return savedKm;
        } else {
            throw new EntityNotFoundException("CKm with id 1 not found");
        }
    }


}

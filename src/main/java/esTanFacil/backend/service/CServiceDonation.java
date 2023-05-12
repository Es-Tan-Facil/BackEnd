package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.repositories.IDonations;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CServiceDonation {

    @Autowired
    private IDonations donationsRepository;

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

}

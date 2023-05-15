package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.repositories.IDonations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CServiceDonation {

    @Autowired
    private IDonations iDonations;


    public void createDonation(CDonations donations) {
      iDonations.save(donations);

    }

    public List<CDonations> readDonation() {
        List<CDonations> donations = new ArrayList<CDonations>(iDonations.findAll());
        return donations;

    }

    public Optional<CDonations> readDonationId(Long id) {
        Optional<CDonations> donations = iDonations.findById(id);
        return donations;
    }

    public void updateDonation(CDonations donations, Long id) {
        donations.setId(id);
        iDonations.save(donations);

    }

    public void deleteDonation(Long id) {
        iDonations.deleteById(id);


    }



}

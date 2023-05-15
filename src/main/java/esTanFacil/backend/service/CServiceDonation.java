package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.model.CKm;
import esTanFacil.backend.repositories.IDonations;
import esTanFacil.backend.repositories.IKm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CServiceDonation {

    @Autowired
    private  IDonations iDonations;

    @Autowired
    private IKm iKm;


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

    public int sumKmDonated() {
        List<CDonations> donations = readDonation();
        int totalKmDonated = 0;
        for (CDonations donation : donations) {
            totalKmDonated += donation.getKmDonated();
        }
        return totalKmDonated;
    }

    public void updateTotalKmDonated() {
        int totalKmDonated = sumKmDonated();
        List<CKm> totalKmList = iKm.findAll();
        if (totalKmList.isEmpty()) {
            CKm newTotalKm = new CKm(1L, totalKmDonated);
            iKm.save(newTotalKm);
        } else {
            CKm totalKm = totalKmList.get(0);
            totalKm.setTotalKm(totalKmDonated);
            iKm.save(totalKm);
        }
    }





}

package esTanFacil.backend.service;

import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.model.CKm;
import esTanFacil.backend.repositories.IKm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CServiceKm {
    @Autowired
    private IKm iKm;

   @Autowired
   private CServiceDonation cServiceDonation;


    public void updateTotalKmDonated() {
        int totalKmDonated = cServiceDonation.sumKmDonated();
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

    public int readTotalKms() {
  Optional<CKm> cKm = iKm.findById(1L);
        return cKm.get().getTotalKm();
    }


}

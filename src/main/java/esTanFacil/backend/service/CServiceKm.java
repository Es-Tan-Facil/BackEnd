package esTanFacil.backend.service;


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


    public void createKm(CKm kms) {
        iKm.save(kms);

    }

    public List<CKm> readKm() {
        List<CKm> kms = new ArrayList<CKm>(iKm.findAll());
        return kms;

    }

    public Optional<CKm> readKmId(Long id) {
        Optional<CKm> kms = iKm.findById(id);
        return kms;
    }

    public void updateKm(CKm kms, Long id) {
        kms.setId(id);
        iKm.save(kms);

    }





}

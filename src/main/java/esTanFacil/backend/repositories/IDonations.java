package esTanFacil.backend.repositories;


import esTanFacil.backend.model.CDonations;
import esTanFacil.backend.model.CKm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IDonations extends JpaRepository<CDonations, Long> {

}
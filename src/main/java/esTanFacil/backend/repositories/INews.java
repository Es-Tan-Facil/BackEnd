package esTanFacil.backend.repositories;

import esTanFacil.backend.model.CNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INews extends JpaRepository <CNews, Long> {
}

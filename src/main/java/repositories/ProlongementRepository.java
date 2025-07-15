package repositories;

import models.Prolongement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByPret_Adherant_IdAdherant(Integer adherantId);
    List<Prolongement> findByStatus_Libelle(String libelle);
}
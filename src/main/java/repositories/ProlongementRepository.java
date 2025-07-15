package repositories;

import models.Prolongement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
    List<Prolongement> findByPret_Adherant_IdAdherant(Integer adherantId);
    List<Prolongement> findByStatus_Libelle(String libelle);
    @Query("SELECT p FROM Prolongement p " +
       "JOIN FETCH p.pret pret " +
       "JOIN FETCH pret.adherant " +
       "JOIN FETCH pret.exemplaire e " +
       "JOIN FETCH e.livre " +
       "WHERE p.status.libelle = 'EN_ATTENTE'")
    List<Prolongement> findDemandesEnAttenteWithAssociations();
}
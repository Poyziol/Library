package repositories;

import models.Pret;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> 
{
    @Query("SELECT COUNT(p) FROM Pret p " +
           "WHERE p.adherant.idAdherant = :idAdherant " +
           "AND p.status.idStatus = 1") 
    int countCurrentPretsByAdherant(@Param("idAdherant") Integer idAdherant);

    @Query("SELECT p FROM Pret p WHERE p.dateRetourReel IS NOT NULL " +
           "AND (:dateMin IS NULL OR p.dateRetourReel >= :dateMin) " +
           "AND (:dateMax IS NULL OR p.dateRetourReel <= :dateMax)")
    List<Pret> findHistoriqueRetours(
        @Param("dateMin") LocalDate dateMin, 
        @Param("dateMax") LocalDate dateMax);

 @Query("SELECT p FROM Pret p WHERE p.adherant.idAdherant = :adherantId AND p.status.libelle = :status")
    List<Pret> findByAdherantIdAdherantAndStatus_Libelle(
        @Param("adherantId") Integer adherantId, 
        @Param("status") String status);
}

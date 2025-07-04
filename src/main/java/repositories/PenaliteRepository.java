package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import models.Penalite;

@Repository
public interface PenaliteRepository extends JpaRepository<Penalite, Integer> {
    // Solution 1 : Utilisation de @Query explicite
    @Query("SELECT COUNT(p) FROM Penalite p WHERE p.adherant.idAdherant = :idAdherant AND p.estReglee = false")
    int countActivePenalitesForAdherant(@Param("idAdherant") Integer idAdherant);

    @SuppressWarnings("null")
    @EntityGraph(attributePaths = {"adherant"})
    @Override
    List<Penalite> findAll();

    // Trouver les pénalités par adhérent
    List<Penalite> findByAdherantIdAdherant(Integer idAdherant);
    
    // Trouver les pénalités non réglées
    List<Penalite> findByEstRegleeFalse();
    
    // Solution alternative avec convention de nommage
    // int countByAdherantIdAdherantAndEstRegleeFalse(Integer idAdherant);
}
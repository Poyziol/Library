package repositories;

import java.util.List;
import java.util.Optional;

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
    
    /**
     * Compte le nombre de pénalités non réglées pour un adhérent donné.
     */
    int countByAdherant_IdAdherantAndEstRegleeFalse(Integer idAdherant);

    /**
     * Retourne toutes les pénalités (réglées ou non) d’un adhérent.
     */
    List<Penalite> findByAdherant_IdAdherant(Integer idAdherant);

    Optional<Penalite> findFirstByAdherant_IdAdherantAndEstRegleeFalseOrderByDateDebutPenaliteDesc(Integer idAdherant);


}
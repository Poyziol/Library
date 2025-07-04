package repositories;

import models.Abonnement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

    @Query("SELECT a FROM Abonnement a JOIN FETCH a.adherant")
    List<Abonnement> findAllWithAdherant();

    @Query("SELECT a FROM Abonnement a JOIN FETCH a.adherant WHERE a.adherant.id = :idAdherant")
    Optional<Abonnement> findByAdherant_IdWithAdherant(@Param("idAdherant") Integer idAdherant);

}


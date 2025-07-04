package repositories;

import models.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PretRepository extends JpaRepository<Pret, Integer> 
{
    @Query("SELECT COUNT(p) FROM Pret p WHERE p.adherant.idAdherant = :idAdherant AND p.idStatus = 1")
    int countCurrentPretsByAdherant(@Param("idAdherant") Integer idAdherant);
}
package repositories;

import models.ReserverExemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserverExemplaireRepository extends JpaRepository<ReserverExemplaire, Integer> {
}
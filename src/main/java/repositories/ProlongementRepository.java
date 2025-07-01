package repositories;

import models.Prolongement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProlongementRepository extends JpaRepository<Prolongement, Integer> {
}
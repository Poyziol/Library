package repositories;

import models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Integer> 
{
  // toutes les méthodes CRUD sont là automatiquement
}

package repositories;

import models.Eleve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EleveRepository extends JpaRepository<Eleve, Integer> 
{
  // toutes les méthodes CRUD sont là automatiquement
}
